/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.source.model;

/**
 *
 * @author tdasilvamendonca
 */
public class Caracteristique {
    private int puissance;
    private int poids;
    private int longueur;
    private int largeur;
    private String carburant;

    public Caracteristique(int puissance, int poids, int longueur, int largeur, String carburant) {
        this.puissance = puissance;
        this.poids = poids;
        this.longueur = longueur;
        this.largeur = largeur;
        this.carburant = carburant;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }
}
