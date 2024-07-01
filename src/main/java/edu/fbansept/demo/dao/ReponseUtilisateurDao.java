package edu.fbansept.demo.dao;

import edu.fbansept.demo.model.ReponseUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseUtilisateurDao extends JpaRepository<ReponseUtilisateur, Integer> {

}