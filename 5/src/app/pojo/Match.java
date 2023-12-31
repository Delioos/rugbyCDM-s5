package app.pojo;

import java.util.List;

public class Match {
    private int id;
    private String date;
    private int nbSpectateurs;
    private List<Arbitre> arbitres;
    private Stade stade;
    private Equipe equipeDomicile;
    private Equipe equipeExterieure;
    private String codeMatch;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbSpectateurs() {
        return nbSpectateurs;
    }

    public void setNbSpectateurs(int nbSpectateurs) {
        this.nbSpectateurs = nbSpectateurs;
    }

    public List<Arbitre> getArbitres() {
        return arbitres;
    }

    public void setArbitres(List<Arbitre> arbitres) {
        this.arbitres = arbitres;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Equipe getEquipeDomicile() {
        return equipeDomicile;
    }

    public void setEquipeDomicile(Equipe equipeDomicile) {
        this.equipeDomicile = equipeDomicile;
    }

    public Equipe getEquipeExterieure() {
        return equipeExterieure;
    }

    public void setEquipeExterieure(Equipe equipeVisiteur) {
        this.equipeExterieure = equipeVisiteur;
    }

    public void setCodeMatch(String codeMatch) {
        this.codeMatch = codeMatch;
    }

    public String getNatioD() {
        return this.equipeDomicile.getNationalite();
    }

    public String getNatioE() {
        return this.equipeExterieure.getNationalite();
    }


    // Ajouter les getters et setters pour tous les attributs
}
