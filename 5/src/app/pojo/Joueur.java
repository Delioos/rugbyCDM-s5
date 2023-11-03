package app.pojo;

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
