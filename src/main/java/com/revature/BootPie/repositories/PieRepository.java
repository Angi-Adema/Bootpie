package com.revature.BootPie.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.BootPie.models.Pie;

@Repository
public interface PieRepository extends CrudRepository<Pie, String> {
    
    List<Pie> findByCaloriesLessThan(int limit);
}
