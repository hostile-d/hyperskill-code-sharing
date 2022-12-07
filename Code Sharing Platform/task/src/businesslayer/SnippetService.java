package businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.SnippetRepository;
import presentation.NewSnippetRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SnippetService {
    private final SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public Snippet getById(Integer id) {
        return repository.getById(id);
    }

    public List<Snippet> getLatest(Integer amount) {
        return repository.getLatest(amount);
    }

    public Integer store(NewSnippetRequestDTO snippetDTO) {
        var id = repository.size() > 0 ? repository.size() + 1 : 1;
        var snippet = new Snippet(id, snippetDTO.getCode(), LocalDateTime.now());
        repository.store(snippet);
        return id;
    }

    public void update(Snippet snippet) {
        repository.update(snippet.getId(), snippet);
    }
}
