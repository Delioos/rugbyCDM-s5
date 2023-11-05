package app;

import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;

/*
Rechercher les équipes qui recevaient et qui ont été arbitrés par un arbitre A
 */
public class Question3 {

    private MongoCollection<BsonDocument> data;
    public Question3(MongoCollection<BsonDocument> data) {
        this.data = data;

        boolean exit = false;
        int idArbitreA = 0;
        while (!exit) {
            // on scanne l'ID de l'arbitre A
            System.out.println("Veuillez saisir l'ID de l'arbitre A : (1-20) - 0 pour quitter");
            Scanner sc = new Scanner(System.in);
            idArbitreA = sc.nextInt();
            if (idArbitreA == 0) {
                exit = true;
                break;
            }
            // on verifie que l'ID est valide
            if (idArbitreA < 1 || idArbitreA > 20) {
                System.out.println("L'ID de l'arbitre doit être compris entre 1 et 20");
                continue;
            } else {
                exit = true;
            }
        }

        // on utilise la méthode qui va nous permettre d'afficher les équipes
        this.rechercherEquipesParArbitre(idArbitreA);
    }

    private void rechercherEquipesParArbitre(int idArbitre) {
        // Filtre pour récupérer les matchs arbitrés par l'arbitre spécifié
        Bson filter = eq("arbitres.idArbitre", idArbitre);

        // Projection pour récupérer uniquement les équipes qui recevaient
        Bson projection = fields(include("equipeDomicile.codeEquip","id"), excludeId());

        // Récupérer les matchs correspondants à l'arbitre spécifié
        List<BsonDocument> matchs = data.find(filter).projection(projection).into(new ArrayList<>());

        // Parcourir les matchs et afficher les équipes qui recevaient
        for (BsonDocument match : matchs) {
            String codeEquipeRecevante = match.getDocument("equipeDomicile").getString("codeEquip").getValue();
            int idMatch = match.getInt32("id").getValue();
            System.out.println("Équipe recevante : " + codeEquipeRecevante + " (match #" + idMatch + ")");
        }
    }

}
