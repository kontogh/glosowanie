package pl.probka.glosujonline.services;

import org.springframework.stereotype.Service;
import pl.probka.glosujonline.entities.ElectionResults;
import pl.probka.glosujonline.entities.OKW;
import pl.probka.glosujonline.repoInterfaces.ResultsRepo;
import pl.probka.glosujonline.repoInterfaces.VotersRepo;

import java.util.List;
import java.util.Optional;

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
            List<ElectionResults> ercheck = repo.getresforokw(okwId);
            if(ercheck.get(0).getKandydat()== ercheck.get(1).getKandydat()){
                if(ercheck.get(0).getglosy()>ercheck.get(1).getglosy()){
                    repo.delete(ercheck.get(1));
                }
                else {
                    repo.delete(ercheck.get(0));
                }
            }
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


