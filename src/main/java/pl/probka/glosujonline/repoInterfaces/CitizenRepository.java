package pl.probka.glosujonline.repoInterfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.probka.glosujonline.entities.Citizen;


@Repository
public interface CitizenRepository extends CrudRepository <Citizen, String> {

   //Citizen findbyImie(String s);

}
