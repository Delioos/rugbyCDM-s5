package app;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            DBConexion db = new DBConexion();

            MongoCollection<BsonDocument> data = db.getData();
            // TODO : ofaire un switch pour avoir un jolie cli
            //Question1 q1 = new Question1(data);
            //Question2 q2 = new Question2(data);
            //Question3 q3 = new Question3(data);
            //Question4 q4 = new Question4(data);
            //Question5 q5 = new Question5(data);
            //Question6 q6 = new Question6(data);
            //Question7 q7 = new Question7(data);
            //Question8 q8 = new Question8(data);
            //Question9 q9 = new Question9(data);
            //Question10 q10 = new Question10(data);
            Question11 q11 = new Question11(data);
        }
        catch (Exception e) {
            System.out.println("Error connecting to database");
            throw e;
        }
    }
}
