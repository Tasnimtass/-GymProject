package com.gymProject.demo.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,//utiliser pour resoudre le prob de boucle infini et generator c'est pour utiliser une de properties comme identifiant unique qui est le id de user
        property = "id"
)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //l 'attribut doit etre la meme que celle dans la table de base

    @NotNull(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String nom;

    @NotNull(message = "lastname is required")
    @Size(min = 3, max = 50, message = "lastname must be between 3 and 50 characters")
    private String prenom;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "mot_de_passe") // le column de table utilisateur de base mot_de_passe ici on utilise mdp au lieu
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String mdp ;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotNull(message = "Sexe is required")
    @Column(length = 10) // max 10 caractères (ex : Homme / Femme)
    private String sexe;

    @NotNull(message = "Poids is required")
    private Double poids;

    @Column(length = 100)
    private String objectif;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//fetch le role de utilisateur est donne a le momnet de creation dun user
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id") //pointe vers le id dans la table roles
    )
    private Set<Role> roles = new HashSet<>();






}
