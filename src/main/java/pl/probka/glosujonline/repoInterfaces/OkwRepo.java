package pl.probka.glosujonline.repoInterfaces;

import org.springframework.data.repository.CrudRepository;
import pl.probka.glosujonline.entities.OKW;

public interface OkwRepo extends CrudRepository<OKW, Long> {
    OKW getByUsername(String username);

}
