package businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.SnippetRepository;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SnippetService {
    private SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
        repository.store(new Snippet(0,"public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}", LocalDateTime.now()));
    }

    public Snippet getById(Integer id) {
        return repository.getById(id);
    }

    public void store(Snippet snippet) {
        snippet.setDate(LocalDateTime.now());
        repository.store(snippet);
    }

    public void update(NewSnippetDTO snippetDTO) {
        var snippet = new Snippet(0, snippetDTO.getCode(), LocalDateTime.now());
        repository.update(snippet.getId(), snippet);
    }
}
