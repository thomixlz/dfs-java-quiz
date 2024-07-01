package edu.fbansept.demo.dao;

import edu.fbansept.demo.model.ReponsePossible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponsePossibleDao extends JpaRepository<ReponsePossible, Integer> {

}
