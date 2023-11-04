package app.pojo;

import static java.lang.Float.NaN;

public class Joueur {
    private int idJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private int numPost;
    private String libellePost;
    private int dureeDeJeu;
    private boolean estTitulaire;
    private int nbEssais;
    private int nbCoupDePied;
    private int pointsMarques;
    private float coef;

    public Joueur(int idJoueur, String nomJoueur, String prenomJoueur, int numPost, String libellePost, int dureeDeJeu, boolean estTitulaire, int nbEssais, int nbCoupDePied, int pointsMarques) {
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.numPost = numPost;
        this.libellePost = libellePost;
        this.dureeDeJeu = dureeDeJeu;
        this.estTitulaire = estTitulaire;
        this.nbEssais = nbEssais;
        this.nbCoupDePied = nbCoupDePied;
        this.pointsMarques = pointsMarques;
        this.coef = this.calculCoef();
    }

    public void cumulerDonneesJoueur(Joueur joueur) {
        this.dureeDeJeu += joueur.getDureeDeJeu();
        this.nbEssais += joueur.getNbEssais();
        this.nbCoupDePied += joueur.getNbCoupDePied();
    }


    public float calculCoef() {
        return (float) this.pointsMarques / this.dureeDeJeu;
    }
    public float getCoef() {
        if (this.dureeDeJeu == 0)
            return 0;
        return this.coef;
    }

    public void setCoef(float coef) {
        this.coef = coef;
    }


    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public int getNumPost() {
        return numPost;
    }

    public void setNumPost(int numPost) {
        this.numPost = numPost;
    }

    public String getLibellePost() {
        return libellePost;
    }

    public void setLibellePost(String libellePost) {
        this.libellePost = libellePost;
    }

    public int getDureeDeJeu() {
        return dureeDeJeu;
    }

    public void setDureeDeJeu(int dureeDeJeu) {
        this.dureeDeJeu = dureeDeJeu;
    }

    public boolean isEstTitulaire() {
        return estTitulaire;
    }

    public void setEstTitulaire(boolean estTitulaire) {
        this.estTitulaire = estTitulaire;
    }

    public int getNbEssais() {
        return nbEssais;
    }

    public void setNbEssais(int nbEssais) {
        this.nbEssais = nbEssais;
    }

    public int getNbCoupDePied() {
        return nbCoupDePied;
    }

    public void setNbCoupDePied(int nbCoupDePied) {
        this.nbCoupDePied = nbCoupDePied;
    }

    public int getPointsMarques() {
        return pointsMarques;
    }

    public void setPointsMarques(int pointsMarques) {
        this.pointsMarques = pointsMarques;
    }

    // Ajoute les getters et setters pour tous les attributs
}
