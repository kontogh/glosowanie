package pl.probka.glosujonline.RepoInterfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.probka.glosujonline.Entities.Citizen;


@Repository
public interface CitizenRepository extends CrudRepository <Citizen, String> {

   //Citizen findbyImie(String s);

}
