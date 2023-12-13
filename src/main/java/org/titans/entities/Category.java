package org.titans.entities;

public class Category {

    private String nom;

    public Category( String nom) {

        this.nom = nom;
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
