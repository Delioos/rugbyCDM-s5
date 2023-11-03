package app;

import org.bson.*;
import com.mongodb.client.*;

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
        this.client.close();
    }
}
