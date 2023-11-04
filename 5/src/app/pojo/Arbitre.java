package app.pojo;

public class Arbitre {
    private int idArbitre;
    private String prenomArbitre;

    public int getIdArbitre() {
        return idArbitre;
    }

    public void setIdArbitre(int idArbitre) {
        this.idArbitre = idArbitre;
    }

    public String getPrenomArbitre() {
        return prenomArbitre;
    }

    public void setPrenomArbitre(String prenomArbitre) {
        this.prenomArbitre = prenomArbitre;
    }

    public String getNomArbitre() {
        return nomArbitre;
    }

    public void setNomArbitre(String nomArbitre) {
        this.nomArbitre = nomArbitre;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    private String nomArbitre;
    private String nationalite;

    // Ajoute les getters et setters pour tous les attributs
}
