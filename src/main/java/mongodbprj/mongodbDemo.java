package mongodbprj;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongodbDemo {
	
	public static void main(String[] args) {
		
		 
		  // To connect to mongodb server
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Now connect to your databases
        MongoDatabase mgdb = mongoClient.getDatabase("db2");

        System.out.println("Connect to database successfully!");
        System.out.println("MongoDatabase inof is : "+mgdb.getName());
        
        MongoCollection<Document> collection = mgdb.getCollection("col2");
        
        
        //---------------------add doc
        Document document = new Document("title", "MongoDB").  
                append("description", "database").  
                append("likes", 100).  
                append("by", "Fly");  
        collection.insertOne(document);
        
        Document document2 = new Document("title", "MongoDB").  
                append("description", "database").  
                append("likes", 200).  
                append("by", "Fly");  
        collection.insertOne(document2);
//                List<Document> documents = new ArrayList<Document>();  
//                documents.add(document);  
           //     collection.insert
        //        collection.insertMany(documents);  
                System.out.println("文档插入成功");  
        //        BasicDBObject
           //     Criteria
       //         Query
           
                
         //       collection.find
         
        
        // If MongoDB in secure mode, authentication is required.
        // boolean auth = db.authenticate(myUserName, myPassword);
        // System.out.println("Authentication: "+auth);
	}

}
