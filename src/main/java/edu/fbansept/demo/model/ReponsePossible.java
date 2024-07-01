package edu.fbansept.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(scope = ReponsePossible.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReponsePossible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String texte;

    protected Boolean estJuste;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    protected Question question;
}
