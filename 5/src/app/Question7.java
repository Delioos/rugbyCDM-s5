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
Afficher le nom des joueurs de l'équipe E1 qui ont joué à la fois contre les
équipes E2 et E3.
 */
public class Question7 {
    private MongoCollection<BsonDocument> data;

    public Question7(MongoCollection<BsonDocument> data) {
        this.data = data;

        // on propose toutes les équipes
        List<String> codesEquipes = recupererCodesEquipes();

        Boolean continuer = true;

        // afficher la liste des differents pays
        codesEquipes.forEach(System.out::println);

        // scanner pour récupérer le code de l'équipe
        System.out.println("Veuillez saisir le code de l'équipe E1 : ");
        Scanner sc = new Scanner(System.in);
        String codeEquipeE1 = sc.nextLine();
        System.out.println("Veuillez saisir le code de l'équipe E2 : ");
        String codeEquipeE2 = sc.nextLine();
        System.out.println("Veuillez saisir le code de l'équipe E3 : ");
        String codeEquipeE3 = sc.nextLine();


        this.afficherJoueursEquipeEContreEquipes("E1", "E2", "E3");

    }

    private void afficherJoueursEquipeEContreEquipes(String equipeE1, String equipeE2, String equipeE3) {
        // Filtre pour récupérer les matchs de l'équipe E1 contre les équipes E2 et E3
        Bson filter = and(
                eq("equipeDomicile.codeEquip", equipeE1),
                or(eq("equipeExterieure.codeEquip", equipeE2), eq("equipeExterieure.codeEquip", equipeE3))
        );

        // Récupérer tous les matchs correspondants au filtre
        List<BsonDocument> matchs = data.find(filter).into(new ArrayList<>());

        // Set pour stocker les joueurs de l'équipe E1
        Set<String> joueursEquipeE1 = new HashSet<>();

        // Parcourir les matchs
        for (BsonDocument match : matchs) {
            // Récupérer les joueurs de l'équipe E1
            BsonArray joueursEquipeDomicile = match.getDocument("equipeDomicile").getArray("joueurs");
            for (BsonValue joueurValue : joueursEquipeDomicile) {
                if (joueurValue instanceof BsonDocument) {
                    BsonDocument joueur = (BsonDocument) joueurValue;
                    String nomJoueur = joueur.getString("nomJoueur").getValue();
                    String prenomJoueur = joueur.getString("prenomJoueur").getValue();
                    joueursEquipeE1.add(nomJoueur + " " + prenomJoueur);
                }
            }
        }

        // Afficher les joueurs de l'équipe E1
        for (String joueur : joueursEquipeE1) {
            System.out.println("Joueur : " + joueur);
        }
    }


    private List<String> recupererCodesEquipes() {
        List<String> codesEquipes = new ArrayList<>();

        // Récupérer tous les matchs
        List<BsonDocument> matchs = data.find().into(new ArrayList<>());

        // Parcourir les matchs et extraire les codes d'équipe uniques
        for (BsonDocument match : matchs) {
            String codeEquipe = match.getDocument("equipeDomicile").getString("codeEquip").getValue();
            if (!codesEquipes.contains(codeEquipe)) {
                codesEquipes.add(codeEquipe);
            }
        }

        return codesEquipes;
    }
}