package pl.probka.glosujonline.RepoInterfaces;

import org.springframework.data.repository.CrudRepository;
import pl.probka.glosujonline.Entities.OKW;

public interface OkwRepo extends CrudRepository<OKW, Long> {
    OKW getByUsername(String username);

}
