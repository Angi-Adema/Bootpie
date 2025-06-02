package com.revature.BootPie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.BootPie.models.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String>{
    
}
