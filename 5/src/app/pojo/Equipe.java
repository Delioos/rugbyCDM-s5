package app.pojo;

import java.util.List;

public class Equipe {
    private String codeEquip;
    private String pays;
    private String couleurMaillot;
    private String coach;
    private List<Joueur> joueurs;
    private int pointsMarques;

    public String getCodeEquip() {
        return codeEquip;
    }

    public void setCodeEquip(String codeEquip) {
        this.codeEquip = codeEquip;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCouleurMaillot() {
        return couleurMaillot;
    }

    public void setCouleurMaillot(String couleurMaillot) {
        this.couleurMaillot = couleurMaillot;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public int getPointsMarques() {
        return pointsMarques;
    }

    public void setPointsMarques(int pointsMarques) {
        this.pointsMarques = pointsMarques;
    }

    // Ajoute les getters et setters pour tous les attributs
}
