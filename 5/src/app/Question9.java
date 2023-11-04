package app;

import app.pojo.Match;
import app.pojo.StatistiquesJoueur;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

/*
Afficher tous les joueurs de l’équipe E qui ont joué tous les matchs de leur
équipe
 */
public class Question9 {
    private MongoCollection<BsonDocument> data;

    public Question9(MongoCollection<BsonDocument> data) {
        this.data = data;
        Utils util = new Utils();
        String codeEquip = util.demanderEquipe(data);

        this.afficherJoueursToutLesMatchs(codeEquip);
    }

    private void afficherJoueursToutLesMatchs(String codeEquip) {
        // On récupère tous les matchs de l'équipe
        Bson filter = or(eq("equipeDomicile.codeEquip", codeEquip), eq("equipeExterieure.codeEquip", codeEquip));
        List<BsonDocument> matchs = data.find(filter).into(new ArrayList<>());
        Utils utils = new Utils();
        Map<String, StatistiquesJoueur> statistiquesJoueurs = utils.getStats(codeEquip, this.data);

        // Pour chaque joueur, on regarde si le nombre de matchs joués est égal au nombre de matchs de l'équipe
        for (Map.Entry<String, StatistiquesJoueur> entry : statistiquesJoueurs.entrySet()) {
            String joueur = entry.getKey();
            StatistiquesJoueur statistiques = entry.getValue();
            if (statistiques.getNombreMatchs() == matchs.size()) {
                System.out.println("Joueur ayant joué tous les matchs de l'équipe : " + joueur);
            }
        }
    }
}
