package com.way.pjatk.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SandwichRepository extends JpaRepository<Sandwich, Integer> {

    @Query(value = "select s from Sandwich s where s.id <= 5")
    Collection<Sandwich> getTopFiveSandwiches();

    @Modifying
    @Query("Update Sandwich  s set  s.name = :name where s.id = :id")
    void modifyMe(String name, Integer id);
}
