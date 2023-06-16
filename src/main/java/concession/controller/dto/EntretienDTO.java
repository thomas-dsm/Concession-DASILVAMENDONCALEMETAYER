/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package concession.controller.dto;

import java.util.Date;

/**
 *
 * @author tdasilvamendonca
 */
public class EntretienDTO {
    private String voitureId;
    private Date date;
    private String description;
    private String garage;

    public EntretienDTO(String voitureId, Date date, String description, String garage) {
        this.voitureId = voitureId;
        this.date = date;
        this.description = description;
        this.garage = garage;
    }

    public String getVoitureId() {
        return voitureId;
    }

    public void setVoitureId(String voitureId) {
        this.voitureId = voitureId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }
}