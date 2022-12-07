package persistence;

import businesslayer.Snippet;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SnippetRepository implements ObjectRepository<Snippet> {
    private Map<Integer, Snippet> repository;

    public SnippetRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void store(Snippet snippet) {
        repository.put(snippet.getId(), snippet);
    }

    @Override
    public Snippet getById(int id) {
        return repository.get(id);
    }

    @Override
    public Snippet search(String code) {
        Collection<Snippet> snippets = repository.values();
        for (Snippet snippet : snippets) {
            if (snippet.getCode().equalsIgnoreCase(code))
                return snippet;
        }
        return null;
    }

    @Override
    public Snippet delete(int id) {
        Snippet snippet = repository.get(id);
        this.repository.remove(id);
        return snippet;
    }

    public void update(int id, Snippet snippet) {
        repository.replace(id, snippet);
    }

    public List<Snippet> getLatest(Integer amount) {
        List<Snippet> snippets = new ArrayList<>(repository.values());
        snippets.sort(Collections.reverseOrder());
        return snippets.stream().limit(amount).collect(Collectors.toList());
    }

    public Integer size() {
        return repository.size();
    }
}
