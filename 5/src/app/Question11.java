package app;

import app.pojo.Arbitre;
import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

public class Question11 {
    private MongoCollection<BsonDocument> data;

    public Question11(MongoCollection<BsonDocument> data) {
        this.data = data;
        Utils util = new Utils();
        Arbitre arbitre = util.demanderArbitre(data);
        Match match = util.demanderMatch(data);

        // inserer un arbitre en checkant la nationalité
        this.insererArbitre(match, arbitre);
    }

    public void insererArbitre(Match m, Arbitre a) {
        // On vérifie que l'arbitre n'est de la même nationalité qu'une des équipes
        System.out.println("Arbitre : " + a.getNomArbitre() + " " + a.getPrenomArbitre() + " est de nationalité " + a.getNationalite() + " et le match est entre " + m.getNatioD() + " et " + m.getNatioE());
        if (m.getNatioD().equals(a.getNationalite()) || m.getNatioE().equals(a.getNationalite())) {
            System.out.println("L'arbitre ne peut pas être de la même nationalité qu'une des équipes");
        } else {
            System.out.println("L'arbitre peut être ajouté");
            // verification que l arbitre n est pas deja dans le match
            for (Arbitre arbitre : m.getArbitres()) {
                if (arbitre.getNomArbitre().equals(a.getNomArbitre()) && arbitre.getPrenomArbitre().equals(a.getPrenomArbitre())) {
                    System.out.println("L'arbitre est déjà dans le match");
                    return;
                }
            }
            System.out.println("traitement en cours...");
            // On l'ajoute au match directement dans la base
            int id = m.getId();
            BsonValue idValue = new BsonInt32(id);
            BsonValue nameValue = new BsonString(a.getNomArbitre());
            BsonValue prenomValue = new BsonString(a.getPrenomArbitre());
            BsonValue nationaliteValue = new BsonString(a.getNationalite());

            this.data.updateOne(

            new BsonDocument("id", idValue), // Filtre pour trouver le match
            new BsonDocument("$push", new BsonDocument("arbitres", new BsonDocument("idArbitre", idValue).append("nomArbitre", nameValue).append("prenomArbitre", prenomValue).append("nationalite", nationaliteValue))) // Ajout de l'arbitre
            );
            System.out.println("Arbitre ajouté");
        }

    }
}
