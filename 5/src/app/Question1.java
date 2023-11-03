package app;
import org.bson.*;

import com.mongodb.client.MongoCollection;


/*
    * Question 1
    * Afficher pour tous les joueurs de l'équipe E : leur temps de jeu, le nombre
d'essais marqués, le nombre de points marqués, et le coefficient (nombre de
points/durée de jeu). On classera les joueurs par ordre décroissant du
coefficient.
*
* hello j ai besoin de ton aide en java pour integrer mongodb.
je vais commencer par t expliquer ce qu on doit faire puis t envoyer un echantillion du jeux de donner pour qeu tu coprennes la strcutre>

je suis deja connecte a la bd, j ai la collection etc. maintenantn
on doit Afficher pour tous les joueurs de l'équipe E : leur temps de jeu, le nombre
d'essais marqués, le nombre de points marqués, et le coefficient (nombre de
points/durée de jeu). On classera les joueurs par ordre décroissant du
coefficient.

pour cela on va proceder par etpaes
- faire une lsite des equiipes qui participent au tournois.
- parcourir tout les matchs et check si l equipe que l utilisateur aura chois a l aide d un scanner est dasn le match qu on regarde actuellement,  si oui on prends tout les jouerus, on creer un objee par joueurs ( j aurais deja fais la classe) et on y a sauvergarde / mets a jour le compte des variables qui nous interesse (nb essaias poijntsMarques coeff
- une fois qu on a tles statsa de tout les joueurs de notre equipes, cumules sure tout les matchs aqu on a dans la base, on affiche les joueurs par coeffciant.
 */
public class Question1 {

    MongoCollection<BsonDocument> data;

    public Question1(MongoCollection<BsonDocument> data) {
        this.data = data;

        // faire la liste des equipes

        // parcourir tout les matchs

        // si notre equipe, on enregistre tout les joueurs ou on met a jour les stats dans une liste d objets joueuurs

        // une fois qu on a tout les joueurs, on les affiche par ordre decroissant de coeff

        System.out.println(data.find().first());
    }
}
