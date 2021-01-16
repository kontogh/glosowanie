package pl.probka.glosujonline.RepoInterfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.probka.glosujonline.Entities.Voters;

@Repository
public interface VotersRepo extends CrudRepository<Voters, String> {

    @Query(value = "SELECT COUNT(*) FROM VOTERS GROUP BY CANDIDATE HAVING CANDIDATE = ?1", nativeQuery = true)
    long countResults(String candidate);

    @Query(value = "SELECT COUNT(*) FROM VOTERS  NATURAL JOIN CITIZEN WHERE OKWFK_ID = ?2 GROUP BY CANDIDATE HAVING CANDIDATE = ?1", nativeQuery = true)
    long countForOkw(String candidate, long comisionId);
}
