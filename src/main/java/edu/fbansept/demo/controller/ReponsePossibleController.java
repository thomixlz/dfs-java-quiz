package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.QuestionDao;
import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.model.Question;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.security.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reponsepossible")
public class ReponsePossibleController {
    @Autowired
    QuestionDao questionDao;

    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @IsAdmin
    @PostMapping("")
    public ResponseEntity<ReponsePossible> addReponsePossibleToQuestion(
            @RequestBody @Valid ReponsePossible reponsePossible) {

        reponsePossible.setId(null);

        Optional<Question> optionalQuestion = questionDao.findById(reponsePossible.getQuestion().getId());

        if (optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponsePossible.setQuestion(optionalQuestion.get());
        reponsePossibleDao.save(reponsePossible);

        return new ResponseEntity<>(reponsePossible, HttpStatus.CREATED);
    }

    @IsAdmin
    @PutMapping("/{id}")
    public ResponseEntity<ReponsePossible> updateReponsePossible(
            @PathVariable Integer id,
            @RequestBody @Valid ReponsePossible reponsePossible) {

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);
        Optional<Question> optionalQuestion = questionDao.findById(reponsePossible.getQuestion().getId());

        if (optionalReponsePossible.isEmpty() || optionalQuestion.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponsePossible.setId(id);
        reponsePossibleDao.save(reponsePossible);

        return new ResponseEntity<>(reponsePossible, HttpStatus.OK);
    }

    @IsAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<ReponsePossible> deleteReponsePossible(
            @PathVariable Integer id) {

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);

        if (optionalReponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        reponsePossibleDao.deleteById(id);

        return new ResponseEntity<>(optionalReponsePossible.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReponsePossible> getReponsePossible(
            @PathVariable Integer id) {

        Optional<ReponsePossible> optionalReponsePossible = reponsePossibleDao.findById(id);

        if (optionalReponsePossible.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalReponsePossible.get(), HttpStatus.OK);
    }

    @GetMapping("/liste")
    public ResponseEntity<Iterable<ReponsePossible>> listeReponsePossible() {
        return new ResponseEntity<>(reponsePossibleDao.findAll(), HttpStatus.OK);
    }
}
