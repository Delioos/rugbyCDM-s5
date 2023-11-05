package app;

import org.bson.*;
import com.mongodb.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBConexion {

    private MongoClient client;

    private MongoDatabase database;
    private MongoCollection<BsonDocument> collection;

    public DBConexion() throws Exception {
        try {

        String url = "mongodb://localhost:27017"; // may modify to include user with credentials
        this.client = MongoClients.create(url);
        // may add a script that check if the database exists, if not, create it
        this.database = this.client.getDatabase("coupeDuMonde");
        this.collection = this.database.getCollection("matchs", BsonDocument.class);

        // si on a reussi a se connecter a la bd, on affiche le nombre de documents dans la collectionk
            System.out.println("conneted to coupeDuMonde, " + collection.countDocuments() + " documents in matchs collection");

            if (collection.countDocuments() == 0) {
                System.out.println("No documents in collection, would you like to import data from a json file ? (y/n)");
                Scanner sc = new Scanner(System.in);
                String saisie = sc.nextLine();
                if (saisie.equals("y")) {
                    System.out.println("execute the following command in a terminal at the root (rugbyCDM-s5): mongoimport --db coupeDuMonde --collection matchs --file matchsOk.json --jsonArray");
                    System.out.println("then restart the program");
                    System.exit(0);
                }
                else {
                    System.out.println("No data imported, exiting program");
                    System.exit(0);
                }
            }
        }

        catch (Exception e) {
            System.out.println("Error connecting to database");
            throw e;
        }
    }


    public MongoDatabase getDatabase() {
        return this.database;
    }

    public MongoClient getClient() {
        return this.client;
    }

    public MongoCollection<BsonDocument> getData() {
        return this.collection;
    }


    public void close() {
        System.out.println("closing connection to database - port 27017 free");
        this.client.close();
    }
}
