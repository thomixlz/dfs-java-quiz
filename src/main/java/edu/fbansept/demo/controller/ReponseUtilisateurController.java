package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.dao.ReponseUtilisateurDao;
import edu.fbansept.demo.dao.UtilisateurDao;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.model.ReponseUtilisateur;
import edu.fbansept.demo.model.Utilisateur;
import edu.fbansept.demo.security.AppUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reponseutilisateur")
public class ReponseUtilisateurController {
    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @Autowired
    ReponseUtilisateurDao reponseUtilisateurDao;
    @Autowired
    UtilisateurDao utilisateurDao;

    @PostMapping("")
    public ResponseEntity<ReponseUtilisateur> addReponseUtilisateur(
            @RequestBody @Valid ReponseUtilisateur reponseUtilisateur,  @AuthenticationPrincipal AppUserDetails userDetails) {

        reponseUtilisateur.setId(null);
        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(reponseUtilisateur.getReponsePossible().getId());
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(reponseUtilisateur.getUtilisateur().getId());

        if (optionalReponsePossible.isEmpty() || optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponseUtilisateur.setUtilisateur(userDetails.getUtilisateur());
        reponseUtilisateur.setReponsePossible(optionalReponsePossible.get());
        reponseUtilisateurDao.save(reponseUtilisateur);

        return new ResponseEntity<>(reponseUtilisateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> updateReponseUtilisateur(
            @PathVariable Integer id,
            @RequestBody @Valid ReponseUtilisateur reponseUtilisateur, @AuthenticationPrincipal AppUserDetails userDetails) {

        Optional<ReponseUtilisateur> optionalReponseUtilisateur = reponseUtilisateurDao.findById(id);
        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(reponseUtilisateur.getReponsePossible().getId());
        Optional<Utilisateur> optionalUtilisateur = utilisateurDao.findById(reponseUtilisateur.getUtilisateur().getId());


        if (optionalReponseUtilisateur.isEmpty() || optionalReponsePossible.isEmpty() || optionalUtilisateur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponseUtilisateur.setId(id);
        reponseUtilisateur.setUtilisateur(userDetails.getUtilisateur());
        reponseUtilisateur.setReponsePossible(optionalReponsePossible.get());
        reponseUtilisateurDao.save(reponseUtilisateur);

        return new ResponseEntity<>(reponseUtilisateur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> deleteReponseUtilisateur(
            @PathVariable Integer id) {

        reponseUtilisateurDao.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponseUtilisateur> getReponseUtilisateur(
            @PathVariable Integer id) {

        return reponseUtilisateurDao.findById(id)
                .map(reponseUtilisateur -> new ResponseEntity<>(reponseUtilisateur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/liste")
    public List<ReponseUtilisateur> liste() {
        return reponseUtilisateurDao.findAll();
    }
}
