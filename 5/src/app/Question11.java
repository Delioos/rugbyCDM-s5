package app;

import app.pojo.Arbitre;
import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

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
        System.out.println("Arbitre : " + a.getNom() + " " + a.getPrenom() + " est de nationalité " + a.getNationalite() + " et le match est entre " + m.getNatioD() + " et " + m.getNatioE());
        if (m.getNatioD().equals(a.getNationalite()) || m.getNatioE().equals(a.getNationalite())) {
            System.out.println("L'arbitre ne peut pas être de la même nationalité qu'une des équipes");
        }
        else {
            System.out.println("L'arbitre peut être ajouté");
            // on l'ajoute au match directement dans la base
            this.data.updateOne(
                    // TODO 
                    new BsonDocument("idMatch", m.getId()),
                    new BsonDocument("$set", new BsonDocument("arbitre", new BsonDocument("nom", a.getNom()).append("prenom", a.getPrenom()).append("nationalite", a.getNationalite())))
            );
        }
    }
}
