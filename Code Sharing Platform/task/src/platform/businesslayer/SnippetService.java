package platform.businesslayer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.persistence.SnippetRepository;
import platform.presentation.NewSnippetRequestDTO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.UUID;

@Service
@Data
public class SnippetService {
    private final SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public Snippet findByUUID(UUID uuid) {
        var snippet = repository.findByUuid(uuid);
        if (snippet == null) {
            return null;
        }
        var isAvaliable = handleRestrictions(snippet);
        if (snippet.getTimeRestriction()) {
            snippet.setTime(getTimeLeft(snippet));
        }
        if (isAvaliable) {
            return snippet;
        }
        return null;
    }

    public Collection<Snippet> getLatest() {
        return repository.findTop10ByTimeAndViewsOrderByDateDesc(0, 0);
    }

    public String store(NewSnippetRequestDTO snippetDTO) {
        var snippet = new Snippet();
        snippet.setDate(LocalDateTime.now());
        snippet.setCode(snippetDTO.getCode());
        snippet.setUuid(snippet.getUuid());
        snippet.setTime(snippetDTO.getTime());
        snippet.setViews(snippetDTO.getViews());
        snippet.setTimeRestriction(snippetDTO.getTime() != 0);
        snippet.setViewsRestriction(snippetDTO.getViews() != 0);
        repository.save(snippet);
        return snippet.getUuid().toString();
    }

    private boolean handleRestrictions(Snippet snippet) {
        if (!snippet.getTimeRestriction() && !snippet.getViewsRestriction()) {
            return true;
        }

        if (snippet.getTimeRestriction()) {
            var timeDiff = getTimeLeft(snippet);

            if (timeDiff <= 0) {
                repository.deleteById(snippet.getId());
                return false;
            }
        }

        if (snippet.getViewsRestriction()) {
            snippet.setViews(snippet.getViews() - 1);
            if (snippet.getViews() == 0) {
                repository.deleteById(snippet.getId());
                return true;
            }
            if (snippet.getViews() > 0) {
                repository.save(snippet);
            }
        }

        return true;
    }

    private Integer getTimeLeft(Snippet snippet) {
        var expirationTime = snippet.getDate().plusSeconds(snippet.getTime());
        Long timeDiff = ChronoUnit.SECONDS.between(LocalDateTime.now(), expirationTime);
        return timeDiff.intValue();
    }
}
