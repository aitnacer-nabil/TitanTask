package org.titans.entities;

import org.titans.util.Utils;

public class Category {

    private String nom;

    public String getId() {
        return id;
    }

    private String id;

    public Category( String nom) {
        this.id = Utils.GenerateId();
        this.nom = nom;
    }

    public Category( String id ,String nom) {
        this.nom = nom;
        this.id = id;
    }
    public Category( ) {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Category{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
