package comxx.util;

import org.bson.Document;

import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongodbConn {
	
	//+srv

	public static void main(String[] args) {
		MongoClient mongoClient = iniMongodb();
		MongoDatabase database = mongoClient.getDatabase("db1");
		MongoCollection<Document> collection = database.getCollection("coll1");
		
		
		
//	    Document document2 = new Document("from", "MongoDB").  
//                append("to", "database").  
//                append("body", 100); 
//        collection.insertOne(document2);
		// DBCollection collection= database.getCollection("col1");
		FindIterable<Document> docs = collection.find();
		 
		for (Document document : docs) {
			System.out.println(document);
		}
		System.out.println("f");

	}

	public static MongoClient iniMongodb() {
		MongoClient mongoClient = MongoClients
				.create("mongodb+srv://user1:aaa000...@cluster0.f6mfg.mongodb.net/db1?retryWrites=true&w=majority");
		return mongoClient;
	}

}
