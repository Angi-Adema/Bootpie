package com.revature.BootPie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.revature.BootPie.exceptions.ResourceNotFoundException;
import com.revature.BootPie.models.Pie;

@Service
public class PieService {
    
    private List<Pie> pieList = new ArrayList<>();

    {
        Pie pie1 = new Pie("Cherry", 800, 6);
        Pie pie2 = new Pie("Apple", 700, 3);
        Pie pie3 = new Pie("BootPie", 100000, 8);

        pieList.add(pie1);
        pieList.add(pie2);
        pieList.add(pie3);
    }

    public Pie getRandomPie() {

        int random = new Random().nextInt(pieList.size());

        return pieList.get(random);
    }

    public List<Pie> getPieList() {

        return pieList;
    }

    public Pie findPie(String pieName) throws ResourceNotFoundException {

        for (Pie pie : pieList) {
            if (pie.getPieName().equals(pieName)) {
                return pie;
            }
        }
        throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another pie.");
    }

    public List<Pie> getPiesByCalories(int limit) throws ResourceNotFoundException {

        List<Pie> caloriePieList = new ArrayList<>();

        for (Pie pie : pieList) {
            if (pie.getCalories() <= limit) {
                caloriePieList.add(pie);
            }
        }

            if (caloriePieList.isEmpty()) {
                throw new ResourceNotFoundException("No pies exist with calories equal to or lower than " + limit);
            }
            return caloriePieList;
        }

    public void deletePie(String pieName) {

        pieList.removeIf(pie -> pie.getPieName().equals(pieName));
    }

    public void patchPie(String pieName, int calories, int slicesAvailable) throws ResourceNotFoundException {

        for (Pie pie : pieList) {
            if (pie.getPieName().equals(pieName)) {
                if (calories > 0) pie.setCalories(calories);
                if (slicesAvailable > 0) pie.setSlicesAvailable(slicesAvailable);
                return;
            }
        }
        throw new ResourceNotFoundException(pieName + " was not found. Please check name or try another.");
    }

    public void updatePie(Pie updatedPie) throws ResourceNotFoundException {

        if (pieList.removeIf(pie -> pie.getPieName().equals(updatedPie.getPieName()))) {
            pieList.add(updatedPie);
            return;
        }
        throw new ResourceNotFoundException(updatedPie.getPieName() + " was not found. Please check name or try another.");
    }

    public void addNewPie(Pie newPie) {

        pieList.add(newPie);
    }
}
