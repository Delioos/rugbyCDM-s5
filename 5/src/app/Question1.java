package app;
import org.bson.*;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;

import static javax.management.Query.eq;
import static com.mongodb.client.model.Projections.*;

/*
    * Question 1
    * Afficher pour tous les joueurs de l'équipe E : leur temps de jeu, le nombre
d'essais marqués, le nombre de points marqués, et le coefficient (nombre de
points/durée de jeu). On classera les joueurs par ordre décroissant du
coefficient.
 */
public class Question1 {

    MongoCollection<BsonDocument> data;

    public Question1(MongoCollection<BsonDocument> data) {
        this.data = data;

        // faire la liste des equipes

        // parcourir tout les matchs

        // si notre equipe, on enregistre tout les joueurs ou on met a jour les stats dans une liste d objets joueuurs

        // une fois qu on a tout les joueurs, on les affiche par ordre decroissant de coeff

        // check the first row id property
        for (BsonDocument doc : data.find(eq("id", 1))) {
            System.out.println(doc);
        }
    }
}
