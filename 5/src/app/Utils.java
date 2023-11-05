package app;

import app.pojo.Arbitre;
import app.pojo.Equipe;
import app.pojo.Match;
import app.pojo.StatistiquesJoueur;
import com.mongodb.client.MongoCollection;
import com.sun.tools.jconsole.JConsoleContext;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static java.lang.Integer.parseInt;

public class Utils {
    private MongoCollection<BsonDocument> data;

    public List<String> recupererCodesEquipes(MongoCollection<BsonDocument> data) {
        List<String> codesEquipes = new ArrayList<>();

        // Récupérer tous les matchs
        List<BsonDocument> matchs = data.find().into(new ArrayList<>());

        // Parcourir les matchs et extraire les codes d'équipe uniques
        for (BsonDocument match : matchs) {
            String codeEquipeDomicile = match.getDocument("equipeDomicile").getString("codeEquip").getValue();
            String codeEquipeExterieure = match.getDocument("equipeExterieure").getString("codeEquip").getValue();

            if (!codesEquipes.contains(codeEquipeDomicile)) {
                codesEquipes.add(codeEquipeDomicile);
            }

            if (!codesEquipes.contains(codeEquipeExterieure)) {
                codesEquipes.add(codeEquipeExterieure);
            }
        }

        return codesEquipes;
    }

    public String demanderEquipe(MongoCollection<BsonDocument> data) {
        // TODO : faire une boucle pour que l'utilisateur puisse recommencer ou sortir / faire un miss input et recommencer
        List<String> codesEquipes = recupererCodesEquipes(data);

        Boolean continuer = true;

        // afficher la liste des differents pays
        codesEquipes.forEach(System.out::println);
        // scanner pour récupérer le code de l'équipe
        System.out.println("Veuillez saisir le code de l'équipe : ");
        Scanner sc = new Scanner(System.in);
        String codeEquipe = sc.nextLine();
        // si le code est valide, afficher les joueurs de l'équipe
        if (codesEquipes.contains(codeEquipe)) {
            return codeEquipe;
        } else {
            System.out.println("Code invalide");
        }
        return null;
    }

    public Map<String, StatistiquesJoueur> getStats(String codeEquip, MongoCollection<BsonDocument> data) {
        // Filtre pour récupérer les matchs de l'équipe spécifiée
        Bson filter = or(eq("equipeDomicile.codeEquip", codeEquip), eq("equipeExterieure.codeEquip", codeEquip));

        // Récupérer tous les matchs correspondants au filtre
        List<BsonDocument> matchs = data.find(filter).into(new ArrayList<>());

        // Map pour stocker les statistiques des joueurs
        Map<String, StatistiquesJoueur> statistiquesJoueurs = new HashMap<>();

        // Parcourir les matchs
        for (BsonDocument match : matchs) {
            // Récupérer les joueurs de l'équipe à domicile
            BsonArray joueurs;
            if (match.getDocument("equipeDomicile").getString("codeEquip").getValue().equals(codeEquip)) {
                joueurs = match.getDocument("equipeDomicile").getArray("joueurs");
            } else {
                joueurs = match.getDocument("equipeExterieure").getArray("joueurs");
            }
            for (BsonValue joueurValue : joueurs) {
                if (joueurValue instanceof BsonDocument) {
                    BsonDocument joueur = (BsonDocument) joueurValue;
                    String nomJoueur = joueur.getString("nomJoueur").getValue();
                    String prenomJoueur = joueur.getString("prenomJoueur").getValue();

                    // Vérifier si le joueur existe déjà dans la map
                    if (statistiquesJoueurs.containsKey(nomJoueur + " " + prenomJoueur)) {
                        // Récupérer les statistiques du joueur
                        StatistiquesJoueur statistiques = statistiquesJoueurs.get(nomJoueur + " " + prenomJoueur);

                        // Incrémenter le nombre de matchs joués
                        if (joueur.getInt32("dureeDeJeu").getValue() > 0)
                            statistiques.setNombreMatchs(statistiques.getNombreMatchs() + 1);

                        // Ajouter les points marqués et les essais marqués
                        statistiques.setNombrePoints(statistiques.getNombrePoints() + joueur.getInt32("pointsMarques").getValue());
                        statistiques.setNombreEssais(statistiques.getNombreEssais() + joueur.getInt32("nbEssais").getValue());
                    } else {
                        // Créer de nouvelles statistiques pour le joueur
                        StatistiquesJoueur statistiques = new StatistiquesJoueur();
                        if (joueur.getInt32("dureeDeJeu").getValue() > 0) {
                            statistiques.setNombreMatchs(1);
                        } else {
                            statistiques.setNombreMatchs(0);
                        }
                        statistiques.setNombrePoints(joueur.getInt32("pointsMarques").getValue());
                        statistiques.setNombreEssais(joueur.getInt32("nbEssais").getValue());

                        // Ajouter le joueur et ses statistiques à la map
                        statistiquesJoueurs.put(nomJoueur + " " + prenomJoueur, statistiques);
                    }
                }
            }
        }
        return statistiquesJoueurs;
    }

    public Arbitre demanderArbitre(MongoCollection<BsonDocument> data) {
        List<Arbitre> arbitres = recupererArbitres(data);

        Boolean continuer = true;
        // afficher la liste des differents pays
        arbitres.forEach(arb -> {
            System.out.println(arb.getIdArbitre() + " " + arb.getNomArbitre() + " " + arb.getPrenomArbitre() + "  -- " + arb.getNationalite());
        });
        // scanner pour récupérer le code de l'équipe
        System.out.println("Veuillez saisir l'id de l'arbitre : ");
        Scanner sc = new Scanner(System.in);
        int idArbitre = parseInt(sc.nextLine());
        // si le code est valide, afficher les joueurs de l'équipe
        for (Arbitre arb : arbitres) {
            if (arb.getIdArbitre() == idArbitre) {
                return arb;
            }
        }
        System.out.println("Code invalide");
        return null;
    }

    public List<Arbitre> recupererArbitres(MongoCollection<BsonDocument> data) {
        List<Arbitre> arbitres = new ArrayList<>();

        // Récupérer tous les matchs
        List<BsonDocument> matchs = data.find().into(new ArrayList<>());

        // parcourir les matchs et recuperer les arbitres
        for (BsonDocument match : matchs) {
            BsonArray arbitresMatch = match.getArray("arbitres");


            for (BsonValue arbitreValue : arbitresMatch) {
                if (arbitreValue instanceof BsonDocument) {
                    BsonDocument arbitre = (BsonDocument) arbitreValue;
                    Arbitre a = new Arbitre();
                    int id = (arbitre.getInt32(arbitre.getFirstKey()).getValue());
                    a.setIdArbitre(id);
                    a.setNomArbitre(arbitre.getString("nomArbitre").getValue());
                    a.setPrenomArbitre(arbitre.getString("prenomArbitre").getValue());
                    a.setNationalite(arbitre.getString("nationalite").getValue());
                    if (!arbitres.contains(a)) {
                        arbitres.add(a);
                    }

                }
            }
        }
        // on classe les arbitres par id
        arbitres.sort(Comparator.comparingInt(Arbitre::getIdArbitre));
        return arbitres;
    }

    public Match demanderMatch(MongoCollection<BsonDocument> data) {
        List<String> matchsCodes = recupererMatchs(data);

        // on recupere les matchs de la bd
        List<BsonDocument> matchs = data.find().into(new ArrayList<>());


        Boolean continuer = true;
        // afficher la liste des differents pays
        matchsCodes.forEach(System.out::println);
        // scanner pour récupérer le code de l'équipe
        System.out.println("Veuillez saisir l'id du match : ");
        Scanner sc = new Scanner(System.in);
        int idMatch = parseInt(sc.nextLine());
        // si matchscodes contient bien l id comment premier caractere d une des string
        boolean matchExiste = false;
        if (data.find(eq("id", idMatch)).first() != null) {
            matchExiste = true;
        }
        if (matchExiste) {
            // on recupere le match grâce a l id
            BsonDocument match = data.find(eq("id", idMatch)).first();
            Match m = new Match();
            m.setId(idMatch);
            // on ne setup que les infos dont on a besoin
            Equipe equipeDomicile = new Equipe();
            equipeDomicile.setPays(match.getDocument("equipeDomicile").getString("pays").getValue());

            Equipe equipeExterieure = new Equipe();
            equipeExterieure.setPays(match.getDocument("equipeExterieure").getString("pays").getValue());
            m.setEquipeDomicile(equipeDomicile);
            m.setEquipeExterieure(equipeExterieure);
            // on recupere les arbitres et on les transforme en objet arbitre
            List<Arbitre> arbitres = new ArrayList<>();
            BsonArray arbitresMatch = match.getArray("arbitres");
            for (BsonValue arbitreValue : arbitresMatch) {
                if (arbitreValue instanceof BsonDocument) {
                    BsonDocument arbitre = (BsonDocument) arbitreValue;
                    Arbitre a = new Arbitre();
                    a.setIdArbitre(arbitre.getInt32("idArbitre").getValue());
                    a.setNomArbitre(arbitre.getString("nomArbitre").getValue());
                    a.setPrenomArbitre(arbitre.getString("prenomArbitre").getValue());
                    a.setNationalite(arbitre.getString("nationalite").getValue());

                    if (!arbitres.contains(a)) {
                        arbitres.add(a);
                    }
                }
            }
            m.setArbitres(arbitres);
            return m;
        } else {
            System.out.println("Code invalide");
        }
        return null;
    }

    public List<String> recupererMatchs(MongoCollection<BsonDocument> data) {
        List<String> matchs = new ArrayList<>();

        // Récupérer tous les matchs
        List<BsonDocument> matchsBson = data.find().into(new ArrayList<>());
        int id = 1;
        // Parcourir les matchs et extraire les codes d'équipe uniques
        for (BsonDocument match : matchsBson) {
            // on construit la variable codeMatch a partir du codeEquip de l equipe a domicile et de l equipe a l exterieur
            String codeMatch = id + " " + match.getDocument("equipeDomicile").getString("codeEquip").getValue() + " - " + match.getDocument("equipeExterieure").getString("codeEquip").getValue();
            if (!matchs.contains(codeMatch)) {
                matchs.add(codeMatch);
                id++;
            }
        }

        return matchs;
    }
}
