package edu.fbansept.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(scope = ReponseUtilisateur.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReponseUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne(optional = false)
    protected Utilisateur utilisateur;

    @ManyToOne(optional = false)
    protected ReponsePossible reponsePossible;
}
