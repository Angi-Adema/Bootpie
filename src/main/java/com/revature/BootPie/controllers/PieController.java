package com.revature.BootPie.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.BootPie.models.Pie;
import com.revature.BootPie.services.PieService;

@Controller
@RequestMapping("pie")
public class PieController {
    
    private PieService pieService;

    public PieController(PieService pieService) {
        this.pieService = pieService;
    }

    // GET list of pies. (http://localhost:8080/pie)
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Pie> getPieList() {
        return pieService.getPieList();
    }

    // GET pie by name. (http://localhost:8080/pie?pieName=Cherry)
    @GetMapping(params = "pieName")
    public @ResponseBody ResponseEntity<Pie> findPie(@RequestParam String pieName) {
        return new ResponseEntity<>(pieService.findPie(pieName), HttpStatus.ACCEPTED);
    }

    // GET pie by calorie limit. (http://localhost:8080/pie/calories/800)
    @GetMapping("calories/{limit}")
    public @ResponseBody ResponseEntity<List<Pie>> findPiesByCalories(@PathVariable int limit) {
        List<Pie> caloriePieList = pieService.getPiesByCalories(limit);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                            .body(caloriePieList);
    }

    // DELETE pie by name. (http://localhost:8080/pie/delete/Cherry)
    @DeleteMapping("delete/{pieName}")
    public @ResponseBody ResponseEntity<String> deletePie(@PathVariable String pieName) {
        pieService.deletePie(pieName);
        return ResponseEntity.accepted()
                            .body("Successfully deleted.");
    }

    // PATCH pie by name. (http://localhost:8080/pie/patch?pieName=Apple&calories=100&slicesAvailable=4)
    @PatchMapping("/patch")
    public @ResponseBody ResponseEntity<String> patchPie(@RequestParam String pieName,
                                                        @RequestParam(defaultValue = "0", required = false) int calories,
                                                        @RequestParam(defaultValue = "0", required = false) int slicesAvailable) {
        pieService.patchPie(pieName, calories, slicesAvailable);
        return ResponseEntity.ok().body("Pie successfully updated.");
    }

    // PUT pie by name. (http://localhost:8080/pie/update)
    @PutMapping("update")
    public @ResponseBody ResponseEntity<String> updatePie(@RequestBody Pie updatedPie) {
        pieService.updatePie(updatedPie);
        return ResponseEntity.ok()
                            .body("Pie successfully updated.");
    }

    // POST create a new pie. (http://localhost:8080/pie/create)
    @PostMapping("create")
    public @ResponseBody ResponseEntity<Pie> createPie(@RequestBody Pie newPie) {
        pieService.addNewPie(newPie);
        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(newPie);
    }
}
