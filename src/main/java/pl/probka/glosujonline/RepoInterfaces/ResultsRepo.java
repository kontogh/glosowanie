package pl.probka.glosujonline.RepoInterfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.probka.glosujonline.Entities.ElectionResults;

import java.util.List;
import java.util.Optional;


public interface ResultsRepo extends CrudRepository<ElectionResults, Long> {
    @Query( value = "SELECT COUNT(*) FROM ELECTION_RESULTS GROUP BY OKW_ID HAVING OKW_ID = ?1", nativeQuery = true)
    Optional <Long> isThere(long okwfkid);

    @Query(value = "SELECT * FROM ELECTION_RESULTS WHERE OKW_ID = ?1", nativeQuery = true)
    List <ElectionResults> getresforokw(long okwid);
}
