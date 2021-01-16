package pl.probka.glosujonline.Menagers;

import org.springframework.stereotype.Service;
import pl.probka.glosujonline.Entities.Voters;
import pl.probka.glosujonline.RepoInterfaces.VotersRepo;

import java.util.Optional;

@Service
public class VotersMenager {

    private VotersRepo votersRepo;

    public VotersMenager(VotersRepo votersRepo){
        this.votersRepo=votersRepo;
    }

    public Optional<Voters> find(String nrdowodu ){
        return votersRepo.findById(nrdowodu);
    }
    public Iterable<Voters> findall(){
        return votersRepo.findAll();
    }
    public void save(Voters voters){
        votersRepo.save(voters);
    }
    public void deletebyid(String nrdowodu){
         votersRepo.deleteById(nrdowodu);
    }

    public void setVoted() {
    }

}
