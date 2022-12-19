package platform.persistence;

import platform.businesslayer.Snippet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;


@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Integer> {
    Collection<Snippet> findTop10ByTimeAndViewsOrderByDateDesc(Integer time, Integer views);
    Snippet findByUuid(UUID uuid);
}
