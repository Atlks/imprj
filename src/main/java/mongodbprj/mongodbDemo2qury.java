package mongodbprj;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

public class mongodbDemo2qury {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// To connect to mongodb server
		MongoClient mongoClient = new MongoClient("localhost", 27017);

		// Now connect to your databases
		MongoDatabase mgdb = mongoClient.getDatabase("db2");

		System.out.println("Connect to database successfully!");
		System.out.println("MongoDatabase inof is : " + mgdb.getName());

		MongoCollection<Document> collection = mgdb.getCollection("col2");

		FindIterable<Document> FindIterable1 = collection.find();
		FindIterable1.forEach(new Consumer() {

			public void accept(Object t) {
			  System.out.println( com.alibaba.fastjson.JSON.toJSONString(t));
				
			}
		});

		/**
		 * Cursor cursor = collection.find(queryObject);
		 * 
		 * while(cursor.hasNext()){
		 * 
		 * DBObject obj = cursor.next();
		 * 
		 * System.out.println(obj.toString());
		 * 
		 * }
		 */

		// List<Document> documents = new ArrayList<Document>();
		// documents.add(document);
		// collection.insert
		// collection.insertMany(documents);
		System.out.println("文档插入成功");
		// BasicDBObject
		// Criteria
		// Query

		// collection.find

		// If MongoDB in secure mode, authentication is required.
		// boolean auth = db.authenticate(myUserName, myPassword);
		// System.out.println("Authentication: "+auth);
	}

}
