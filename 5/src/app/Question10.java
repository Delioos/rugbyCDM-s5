package app;

import app.pojo.StatistiquesJoueur;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/*
Afficher quel est le joueur de la coupe du monde qui a marqué le plus d'essais,
et celui qui a marqué le plus de points.
 */
public class Question10 {
    private MongoCollection<BsonDocument> data;

    public Question10(MongoCollection<BsonDocument> data) {
        this.data = data;

        // on recup toutes les equipes
        Utils util = new Utils();
        List<String> teams = util.recupererCodesEquipes(this.data);

        // on initialise nos variables de retour
        AtomicReference<String> maxTryPlayer = new AtomicReference<>("");
        AtomicInteger maxTryValue = new AtomicInteger(-1);
        AtomicReference<String> maxPointsPlayer = new AtomicReference<>("");

        AtomicInteger maxPointsValue = new AtomicInteger(-1);
        AtomicReference<String> tryTeam= new AtomicReference<>("");
        AtomicReference<String> pointsTeam= new AtomicReference<>("");


        // pour chaque equipe on stock les stats des joueurs
        teams.forEach(team -> {
            Map<String, StatistiquesJoueur> statistiquesJoueurs = util.getStats(team, this.data);

            for (Map.Entry<String, StatistiquesJoueur> entry : statistiquesJoueurs.entrySet()) {
                String joueur = entry.getKey();
                StatistiquesJoueur statistiques = entry.getValue();
                // on check si c est le meilleur d une cate
                int trys = statistiques.getNombreEssais();
                int points = statistiques.getNombrePoints();

                if (trys > maxTryValue.get()) {
                    maxTryPlayer.set(joueur);
                    maxTryValue.set(trys);
                    tryTeam.set(team);
                }
                if (points > maxPointsValue.get()) {
                    maxPointsPlayer.set(joueur);
                    maxPointsValue.set(points);
                    pointsTeam.set(team);
                }
            }
        });

        //todo : eventuellement recuperer le pays lies plutot que le code mais si j ai vrmt du temps en trop


        // on print
        System.out.println("le joueur avec le plus de points est : " + maxPointsPlayer.get() + " des " + pointsTeam.get() + " avec " + maxPointsValue.get() + " points");
        System.out.println("le joueur avec le plus d essais est : " + maxTryPlayer.get() + " des " + tryTeam.get() + " avec " + maxTryValue.get() + " essais de marqués");
    }
}
