package com.revature.BootPie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.BootPie.exceptions.ResourceNotFoundException;
import com.revature.BootPie.models.Pie;
import com.revature.BootPie.repositories.PieRepository;

@Service
public class PieService {
    
    // No longer need this with refactoring with JPA. See new functionality below.
    // private List<Pie> pieList = new ArrayList<>();

    // {
    //     Pie pie1 = new Pie("Cherry", 800, 6);
    //     Pie pie2 = new Pie("Apple", 700, 3);
    //     Pie pie3 = new Pie("BootPie", 100000, 8);

    //     pieList.add(pie1);
    //     pieList.add(pie2);
    //     pieList.add(pie3);
    // }

    private PieRepository pieRepository;

    @Autowired
    public PieService(PieRepository pieRepository) {
        this.pieRepository = pieRepository;
    }

    // public Pie getRandomPie() {

    //     int random = new Random().nextInt(pieList.size());

    //     return pieList.get(random);
    // }

    public List<Pie> getPieList() {

        return (List<Pie>) pieRepository.findAll();
    }

    public Pie findPie(String pieName) throws ResourceNotFoundException {
        // This returns an Optional so we can use orElseThrow to handle the case where the pie is not found.
        return pieRepository.findById(pieName)
            .orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name or try another pie."));

        // No longer need to loop through to get a pieName, we can just return the pie name.
        // for (Pie pie : pieList) {
        //     if (pie.getPieName().equals(pieName)) {
        //         return pie;
        //     }
        // }
        // throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another pie.");
    }

    // TODO: Create custom query.
    public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException {

        List<Pie> caloriePieList = new ArrayList<>();

        // for (Pie pie : pieList) {
        //     if (pie.getCalories() <= limit) {
        //         caloriePieList.add(pie);
        //     }
        // }

        //     if (caloriePieList.isEmpty()) {
        //         throw new ResourceNotFoundException("No pies exist with calories equal to or lower than " + limit);
        //     } else {
                return caloriePieList;
            
        }

    public void deletePie(String pieName) {
        pieRepository.deleteById(pieName);

        // pieList.removeIf(pie -> pie.getPieName().equals(pieName));
    }

    public void patchPie(String pieName, int calories, int slicesAvailable) throws ResourceNotFoundException {

        Pie pie = pieRepository.findById(pieName)
            .orElseThrow(() -> new ResourceNotFoundException(pieName + " was not found. Please check name or try another pie."));
        if (calories > 0) pie.setCalories(calories);
        if (slicesAvailable > 0) pie.setSlicesAvailable(slicesAvailable);
        pieRepository.save(pie);

        // for (Pie pie : pieList) {
        //     if (pie.getPieName().equals(pieName)) {
        //         if (calories > 0) pie.setCalories(calories);
        //         if (slicesAvailable > 0) pie.setSlicesAvailable(slicesAvailable);
        //         return;
        //     }
        // }
        // throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another.");
    }

    public void updatePie(Pie updatedPie) throws ResourceNotFoundException {

        Pie pie = pieRepository.findById(updatedPie.getPieName())
            .orElseThrow(() -> new ResourceNotFoundException(updatedPie.getPieName() + " was not found. Please check name or try another pie."));
        pieRepository.save(pie);

        // if (pieList.removeIf(pie -> pie.getPieName().equals(updatedPie.getPieName()))) {
        //     pieList.add(updatedPie);
        //     return;
        // }
        // throw new ResourceNotFoundException(updatedPie.getPieName() + " was not found. Please check name or try another.");
    }

    public void addNewPie(Pie newPie) {

        pieRepository.save(newPie);

        // pieList.add(newPie);
    }
}
