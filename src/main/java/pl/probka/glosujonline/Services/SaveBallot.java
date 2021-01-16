package pl.probka.glosujonline.Services;

import org.springframework.stereotype.Service;
import pl.probka.glosujonline.Entities.Citizen;
import pl.probka.glosujonline.Entities.Voters;
import pl.probka.glosujonline.RepoInterfaces.VotersRepo;

@Service
public class SaveBallot {
    VotersRepo votersRepo;

    public SaveBallot(VotersRepo votersRepo) {
        this.votersRepo = votersRepo;
    }
    public void savevote(String idnum, String candidate){
        Voters voterInDB = new Voters();
        voterInDB.senNrdowodu(idnum);
        voterInDB.setCandidate(candidate);
        Citizen ct = new Citizen();
        ct.setNrDowodu(idnum);
        voterInDB.setCitizen(ct);
        votersRepo.save(voterInDB);
    }
    public void saveIsvoted(String idnum){
        Voters voterinDB = new Voters();
        voterinDB.senNrdowodu(idnum);
        Citizen ct = new Citizen();
        ct.setNrDowodu(idnum);
        voterinDB.setCitizen(ct);
        votersRepo.save(voterinDB);
    }
}
