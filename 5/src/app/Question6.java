package app;

import app.pojo.Match;
import app.pojo.StatistiquesJoueur;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;



public class Question6 {
    private MongoCollection<BsonDocument> data;

    public Question6(MongoCollection<BsonDocument> data) {
        this.data = data;

        Utils util = new Utils();

        String codeEquip = util.demanderEquipe(this.data);
        this.afficherStatistiquesJoueursEquipeE(codeEquip);
    }

    private void afficherStatistiquesJoueursEquipeE(String equipeE) {

        // Recuperer Stats
        Utils utils = new Utils();
        Map<String, StatistiquesJoueur> statistiquesJoueurs = utils.getStats(equipeE, this.data);

        // Afficher les statistiques des joueurs
        for (Map.Entry<String, StatistiquesJoueur> entry : statistiquesJoueurs.entrySet()) {
            String joueur = entry.getKey();
            StatistiquesJoueur statistiques = entry.getValue();
            System.out.println(joueur + " a joué " + statistiques.getNombreMatchs() + " match(s), a marqué " + statistiques.getNombrePoints() + " point(s) et a marqué " + statistiques.getNombreEssais() + " essai(s).");
        }
    }

}
