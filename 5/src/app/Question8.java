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


/*
Afficher les joueurs de l'équipe E qui n'ont joué aucun match
 */
public class Question8 {
    private MongoCollection<BsonDocument> data;

    public Question8(MongoCollection<BsonDocument> data) {
        this.data = data;

        Utils util = new Utils();
        String codeEquip = util.demanderEquipe(data);

        this.afficherJoueursSansMatch(codeEquip);
    }


    private void afficherJoueursSansMatch(String codeEquip) {
        // Recuperer Stats
        Utils utils = new Utils();
        Map<String, StatistiquesJoueur> statistiquesJoueurs = utils.getStats(codeEquip, this.data);

        for (Map.Entry<String, StatistiquesJoueur> entry : statistiquesJoueurs.entrySet()) {
            String joueur = entry.getKey();
            StatistiquesJoueur statistiques = entry.getValue();
            if (statistiques.getNombreMatchs() == 0)
                System.out.println(joueur + " n'as pas disputé de match");
        }
    }
}
