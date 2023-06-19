/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.controller.dto;

/**
 *
 * @author tdasilvamendonca
 */
public class MarqueDTO {
    private String nom;
    private int anneeCreation;
    private String pays;

    public MarqueDTO(String nom, int anneeCreation, String pays) {
        this.nom = nom;
        this.anneeCreation = anneeCreation;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnneeCreation() {
        return anneeCreation;
    }

    public void setAnneeCreation(int anneeCreation) {
        this.anneeCreation = anneeCreation;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}