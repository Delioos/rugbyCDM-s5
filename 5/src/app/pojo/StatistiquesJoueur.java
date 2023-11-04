package app.pojo;

public class StatistiquesJoueur {
    private int nombreMatchs;
    private int nombrePoints;
    private int nombreEssais;

    public StatistiquesJoueur() {
        this.nombreMatchs = 0;
        this.nombrePoints = 0;
        this.nombreEssais = 0;
    }

    public int getNombreMatchs() {
        return nombreMatchs;
    }

    public void setNombreMatchs(int nombreMatchs) {
        this.nombreMatchs = nombreMatchs;
    }

    public int getNombrePoints() {
        return nombrePoints;
    }

    public void setNombrePoints(int nombrePoints) {
        this.nombrePoints = nombrePoints;
    }

    public int getNombreEssais() {
        return nombreEssais;
    }

    public void setNombreEssais(int nombreEssais) {
        this.nombreEssais = nombreEssais;
    }

    public void incrementNombreMatchs() {
        this.nombreMatchs++;
    }
}
