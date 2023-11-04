package app;

import app.pojo.Match;
import com.mongodb.client.MongoCollection;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;



public class Question4 {

    private MongoCollection<BsonDocument> data;

    public Question4(MongoCollection<BsonDocument> data) {
        this.data = data;
        Scanner sc = new Scanner(System.in);
        Utils utils = new Utils();
        List<String> teams = utils.recupererCodesEquipes(this.data);
        teams.forEach(System.out::println);

        // on scanne les noms des équipes E1 et E2
        System.out.println("Veuillez saisir le nom de l'équipe E1 : ");
        String equipeE1 = sc.nextLine();

        System.out.println("Veuillez saisir le nom de l'équipe E2 : ");
        String equipeE2 = sc.nextLine();

        // on scanne la date D
        System.out.println("Veuillez saisir la date D : ");
        String dateD = sc.nextLine();

        // on utilise la méthode qui va nous permettre d'afficher les joueurs
        this.rechercherJoueursParEquipeEtDate(equipeE1, equipeE2, dateD);
    }

    private void rechercherJoueursParEquipeEtDate(String equipeE1, String equipeE2, String date) {
        // Filtre pour récupérer le match correspondant à l'équipe E1, l'équipe E2 et la date spécifiée
        Bson filter = and(eq("equipeDomicile.codeEquip", equipeE1), eq("equipeExterieure.codeEquip", equipeE2), eq("date", date));

        // Projection pour récupérer uniquement les joueurs de l'équipe E1 qui ont débuté le match
        Bson projection = fields(include("equipeDomicile.joueurs"), excludeId());

        // Récupérer le match correspondant aux critères spécifiés
        BsonDocument match = data.find(filter).projection(projection).first();

        // Vérifier si le match a été trouvé
        if (match != null) {
            // Récupérer les joueurs de l'équipe E1 qui ont débuté le match
            List<BsonDocument> joueurs = new ArrayList<>();
            BsonArray joueursArray = match.getDocument("equipeDomicile").getArray("joueurs");
            for (BsonValue joueurValue : joueursArray) {
                if (joueurValue instanceof BsonDocument) {
                    BsonDocument joueurDocument = (BsonDocument) joueurValue;
                    joueurs.add(joueurDocument);
                }
            }
            // Afficher les informations des joueurs
            for (BsonDocument joueur : joueurs) {
                String nomJoueur = joueur.getString("nomJoueur").getValue();
                int numeroJoueur = joueur.getInt32("numPost").getValue();
                boolean titulaire = joueur.getBoolean("estTitulaire").getValue();
                if (titulaire)
                    System.out.println("Joueur : " + nomJoueur + " (numéro " + numeroJoueur + ")");
            }
        } else {
            System.out.println("Aucun match trouvé pour l'équipe " + equipeE1 + " contre l'équipe " + equipeE2 + " à la date " + date);
        }
    }


}
