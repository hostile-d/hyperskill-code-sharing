package businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.SnippetRepository;

@Service
public class SnippetService {
    private SnippetRepository repository;

    @Autowired
    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
        repository.store(new Snippet(0,"public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}"));
    }

    public Snippet getById(Integer id) {
        return repository.getById(id);
    }
}
