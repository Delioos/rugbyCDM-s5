package app;

import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;


/*
    Rechercher tous les matchs qui se sont déroulés à une date D et dans lesquels
le score d'une des équipes a dépassé un nombre P de points.
 */
public class Question2 {
    private MongoCollection<BsonDocument> data;

    public Question2(MongoCollection<BsonDocument> data) {
        this.data = data;

        // on scanne la date D
        System.out.println("Veuillez saisir la date D : ");
        Scanner sc = new Scanner(System.in);
        String dateD = sc.nextLine();
        // on scan le nombre de points P
        System.out.println("Veuillez saisir le nombre de points P : ");
        int nombrePointsP = sc.nextInt();

        // on utilise la methode qui va nous permettre de print les matchs
        this.rechercherMatchsParScore(dateD, nombrePointsP);
    }

    private void rechercherMatchsParScore(String date, int nombrePoints) {
        // Filtre pour récupérer les matchs à la date spécifiée
        Bson filter = eq("date", date);

        // Récupérer les matchs correspondants à la date spécifiée
        List<BsonDocument> matchs = data.find(filter).into(new ArrayList<>());

        // Parcourir les matchs et vérifier si le score d'une des équipes dépasse le nombre de points spécifié
        for (BsonDocument match : matchs) {
            int scoreEquipeDomicile = match.getDocument("equipeDomicile").getInt32("pointsMarques").getValue();
            int scoreEquipeVisiteur = match.getDocument("equipeExterieure").getInt32("pointsMarques").getValue();

            if (scoreEquipeDomicile > nombrePoints || scoreEquipeVisiteur > nombrePoints) {
                int idMatch = match.getInt32("id").getValue();
                String dateMatch = match.getString("date").getValue();
                int nbSpectateurs = match.getInt32("nbSpectateurs").getValue();
                String teamsCodes = match.getDocument("equipeDomicile").getString("codeEquip").getValue() + " - " + match.getDocument("equipeExterieure").getString("codeEquip").getValue();
                String teamsScores = scoreEquipeDomicile + " - " + scoreEquipeVisiteur;

                System.out.println("Match #" + idMatch + " (" + dateMatch + ") : " + teamsCodes + " (" + teamsScores + ") - " + nbSpectateurs + " spectateurs");
            }
        }
    }

}
