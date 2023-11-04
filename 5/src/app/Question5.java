package app;

import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;



public class Question5 {

    private MongoCollection<BsonDocument> data;

    public Question5(MongoCollection<BsonDocument> data) {
        this.data = data;
        Utils utils = new Utils();

        // scanner pour récupérer le code de l'équipe
        String codeEquipe = utils.demanderEquipe(this.data);
        // si le code est valide, afficher les joueurs de l'équipe
        this.afficherJoueursEntresEnCoursDeJeu(codeEquipe);
    }

    private void afficherJoueursEntresEnCoursDeJeu(String equipeE) {
        // Filtre pour récupérer les matchs de l'équipe spécifiée
        Bson filter = or(eq("equipeDomicile.codeEquip", equipeE), eq("equipeExterieure.codeEquip", equipeE));

        // Récupérer tous les matchs correspondants au filtre
        List<BsonDocument> matchs = data.find(filter).into(new ArrayList<>());

        List<String> outputs = new ArrayList<>();
        // Parcourir les matchs
        for (BsonDocument match : matchs) {
            System.out.println("---------------------------------------\n\nMatch : " + match.getInt32("id").getValue() + "\n");
            // Récupérer les joueurs
            // si le code de l'équipe est celui de l'équipe à domicile
            BsonArray joueurs;
            if (match.getDocument("equipeDomicile").getString("codeEquip").getValue().equals(equipeE)) {
                joueurs = match.getDocument("equipeDomicile").getArray("joueurs");
            } else {
                joueurs = match.getDocument("equipeExterieure").getArray("joueurs");
            }
            for (BsonValue joueurValue : joueurs) {
                if (joueurValue instanceof BsonDocument) {
                    BsonDocument joueur = (BsonDocument) joueurValue;
                    boolean estTitulaire = joueur.getBoolean("estTitulaire").getValue();
                    int dureeDeJeu = joueur.getInt32("dureeDeJeu").getValue();

                    // Vérifier si le joueur n'est pas titulaire et a un temps de jeu supérieur à 0
                    if (!estTitulaire && dureeDeJeu > 0) {
                        String nomJoueur = joueur.getString("nomJoueur").getValue();
                        String prenomJoueur = joueur.getString("prenomJoueur").getValue();
                        int idMatch = match.getInt32("id").getValue();
                        System.out.println("Joueur entré en cours de jeu : " + nomJoueur + " " + prenomJoueur + " - \ta joué " + dureeDeJeu + " min");
                    }
                }
            }
        }
    }
}