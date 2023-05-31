/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.document;

import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
public class Voiture {
    private String marqueId;
    private String immat;
    private String dateImmat;
    private double prix;
    private List<String> type;
    private Caracteristique caracteristiques;
    private String couleur;

    public Voiture(String marqueId, String immat, String dateImmat, double prix, List<String> type, Caracteristique caracteristiques, String couleur) {
        this.marqueId = marqueId;
        this.immat = immat;
        this.dateImmat = dateImmat;
        this.prix = prix;
        this.type = type;
        this.caracteristiques = caracteristiques;
        this.couleur = couleur;
    }

    public String getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(String marqueId) {
        this.marqueId = marqueId;
    }

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public String getDateImmat() {
        return dateImmat;
    }

    public void setDateImmat(String dateImmat) {
        this.dateImmat = dateImmat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Caracteristique getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(Caracteristique caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}