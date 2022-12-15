package platform.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.persistence.SnippetRepository;
import platform.presentation.NewSnippetRequestDTO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class SnippetService {
    private final SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public Optional<Snippet> findById(Integer id) {
        return repository.findById(id);
    }

    public Collection<Snippet> getLatest() {
        return repository.findTop10ByOrderByDateDesc();
    }

    public Integer store(NewSnippetRequestDTO snippetDTO) {
        var snippet = new Snippet();
        snippet.setDate(LocalDateTime.now());
        snippet.setCode(snippetDTO.getCode());
        repository.save(snippet);
        return snippet.getId();
    }
}
