package app;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            DBConexion db = new DBConexion();

            MongoCollection<BsonDocument> data = db.getData();

            Question1 q1 = new Question1(data);
        }
        catch (Exception e) {
            System.out.println("Error connecting to database");
            throw e;
        }
    }
}
