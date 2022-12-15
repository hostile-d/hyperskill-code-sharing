package platform.persistence;

import platform.businesslayer.Snippet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface SnippetRepository extends CrudRepository<Snippet, Integer> {
    Collection<Snippet> findTop10ByOrderByDateDesc();
}
