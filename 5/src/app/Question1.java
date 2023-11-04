package app;

import app.pojo.Joueur;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.*;

import java.util.*;

public class Question1 {

    private MongoCollection<BsonDocument> data;

    public Question1(MongoCollection<BsonDocument> data) {
        this.data = data;
        // recuperer la liste des differents pays
        Utils utils = new Utils();
        String codeEquipe = utils.demanderEquipe(data);
        afficherJoueursEquipeE(codeEquipe);
    }





    public void afficherJoueursEquipeE(String codeEquipe) {
        List<Joueur> joueursEquipeE = new ArrayList<>();

        // Filtre pour récupérer les matchs de l'équipe passée en paramètre
        Bson filter = or(eq("equipeDomicile.codeEquip", codeEquipe), eq("equipeExterieure.codeEquip", codeEquipe));

        // Projection pour récupérer les informations nécessaires des joueurs
        Bson projection = fields(include("equipeDomicile.joueurs", "equipeDomicile.codeEquip", "equipeExterieure.joueurs", "equipeExterieure.codeEquip"), excludeId());

        // Récupérer les matchs correspondants à l'équipe E
        List<BsonDocument> matchs = data.find(filter).projection(projection).into(new ArrayList<>());

        for (BsonDocument match : matchs) {
            BsonArray joueursArray;
            if (match.getDocument("equipeDomicile").getString("codeEquip").getValue().equals(codeEquipe)) {
                joueursArray = match.getDocument("equipeDomicile").getArray("joueurs");
            } else {
                joueursArray = match.getDocument("equipeExterieure").getArray("joueurs");
            }

            for (BsonValue joueurValue : joueursArray) {
                BsonDocument joueur = joueurValue.asDocument();
                Joueur j = new Joueur(
                        joueur.getInt32("idJoueur").getValue(),
                        joueur.getString("nomJoueur").getValue(),
                        joueur.getString("prenomJoueur").getValue(),
                        joueur.getInt32("numPost").getValue(),
                        joueur.getString("libellePost").getValue(),
                        joueur.getInt32("dureeDeJeu").getValue(),
                        joueur.getBoolean("estTitulaire").getValue(),
                        joueur.getInt32("nbEssais").getValue(),
                        joueur.getInt32("nbCoupDePied").getValue(),
                        joueur.getInt32("pointsMarques").getValue()
                );
                Joueur joueurExistant = joueursEquipeE.stream()
                        .filter(joueurEx -> joueurEx.getIdJoueur() == j.getIdJoueur())
                        .findFirst()
                        .orElse(null);
                if (joueurExistant != null) {
                    joueurExistant.cumulerDonneesJoueur(j);
                } else {
                    joueursEquipeE.add(j);
                }
            }
        }

        for (Joueur joueur : joueursEquipeE) {
            joueur.calculCoef();
        }

        // Trier les joueurs par ordre décroissant du coefficient
        Collections.sort(joueursEquipeE, Comparator.comparingDouble(Joueur::getCoef).reversed());

        // Afficher les informations des joueurs
        for (Joueur joueur : joueursEquipeE) {
            System.out.println("Joueur : " + joueur.getNomJoueur() + " " + joueur.getPrenomJoueur());
            System.out.println("Temps de jeu : " + joueur.getDureeDeJeu());
            System.out.println("Nombre d'essais marqués : " + joueur.getNbEssais());
            System.out.println("Nombre de points marqués : " + joueur.getPointsMarques());
            System.out.println("Coefficient : " + joueur.getCoef());
            System.out.println("--------------------------------------");
        }
    }
}
