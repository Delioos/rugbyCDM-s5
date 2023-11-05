package app;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // on se connecte a la bd
            DBConexion db = new DBConexion();

            // on recup les données
            MongoCollection<BsonDocument> data = db.getData();
            // on initialise nos variables utilitaires
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            // on boucle tant que l'utilisateur ne veut pas quitter
            while (!exit) {
                System.out.println("Quelle question voulez vous executer ? (1-11) - 0 pour quitter");
                int question = scanner.nextInt();
                switch (question) {
                    case 0:
                        db.close();
                        exit = true;
                        break;
                    case 1:
                        Question1 q1 = new Question1(data);
                        break;
                    case 2:
                        Question2 q2 = new Question2(data);
                        break;
                    case 3:
                        Question3 q3 = new Question3(data);
                        break;
                    case 4:
                        Question4 q4 = new Question4(data);
                        break;
                    case 5:
                        Question5 q5 = new Question5(data);
                        break;
                    case 6:
                        Question6 q6 = new Question6(data);
                        break;
                    case 7:
                        Question7 q7 = new Question7(data);
                        break;
                    case 8:
                        Question8 q8 = new Question8(data);
                        break;
                    case 9:
                        Question9 q9 = new Question9(data);
                        break;
                    case 10:
                        Question10 q10 = new Question10(data);
                        break;
                    case 11:
                        Question11 q11 = new Question11(data);
                        break;
                    default:
                        System.out.println("Question inconnue");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error connecting to database");
            throw e;
        }
    }
}
