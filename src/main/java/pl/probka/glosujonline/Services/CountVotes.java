package pl.probka.glosujonline.Services;

import org.springframework.stereotype.Service;
import pl.probka.glosujonline.Entities.ElectionResults;
import pl.probka.glosujonline.Entities.OKW;
import pl.probka.glosujonline.RepoInterfaces.ResultsRepo;
import pl.probka.glosujonline.RepoInterfaces.VotersRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountVotes {
    private final ResultsRepo repo;
    private final VotersRepo votersRepo;
    List <ElectionResults> wyniki;
    public CountVotes(ResultsRepo repo, VotersRepo votersRepo ) {

        this.repo=repo;
        this.votersRepo=votersRepo;
    }
    public void makeElRes(long okwId, String candidate){
        Optional<Long> isthere;
        isthere = repo.isThere(okwId);
        //if jest potrzebny aby nie tworzyc 2 razy instancji wyniku
        //wersja 15 bylo isEmpty
        if(isthere.isPresent()==false){
        ElectionResults buf = new ElectionResults(candidate, votersRepo.countForOkw(candidate, okwId));
        OKW temp = new OKW();
        temp.setId(okwId);
        buf.setOkw(temp);
        repo.save(buf);}
        else if(isthere.get()<2){
            ElectionResults buf = new ElectionResults(candidate, votersRepo.countForOkw(candidate, okwId));
            OKW temp = new OKW();
            temp.setId(okwId);
            buf.setOkw(temp);
            repo.save(buf);
            wyniki = null;
         }
        }
        public List <ElectionResults> getWyniki(long okwid){
        return wyniki = repo.getresforokw(okwid);
        }
        public boolean exist(long okwId){
            Optional<Long> isthere;
            isthere = repo.isThere(okwId);
            return isthere.get()<2;
        }
    }


