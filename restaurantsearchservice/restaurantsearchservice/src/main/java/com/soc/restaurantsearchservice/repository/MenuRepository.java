package com.soc.restaurantsearchservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soc.restaurantsearchservice.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>{

}
