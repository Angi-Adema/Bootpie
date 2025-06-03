package com.revature.BootPie.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.BootPie.models.Consumer;
import com.revature.BootPie.models.Pie;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String>{
    
    Optional<Consumer> findByUsernameAndPassword(String username, String password);

    //Custom query to find a consumer by their last pie.
    @Query("SELECT c FROM Consumer c WHERE c.lastPie = ?1")
    Collection<Consumer> findAllConsumersByLastPie(Pie pie);
}
