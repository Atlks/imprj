package comxx.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import static com.mongodb.client.model.Aggregates.*;

public class MongodbUtil {

	public static void main(String[] args) {
		// String exp="db.aggregateSumDemo.aggregate([ {$group: { _id:
		// null,\"TotalCount总数量\": {$sum:1 } } } ])";

		String coll = "db1.coll1";
		String aggrtTitleFld = "count总条数";
		String agrgtField = "aggrtTitleFld";
		String aggrtMethod = "sum";
		Document sum = aggrt(coll, "count总条数", "anyfld", "count");
		sum = aggrt(coll, "sum总和", "body", "sum");
		sum = aggrt(coll,   "avg","body","avg平均数");
		// Document{{_id=null, body=100}}
		System.out.println(sum);

	}

	public static Document aggrt(String coll,  String aggrtMethod, String agrgtField, String asTitle) {
		String[] a = coll.split("\\.");
		String databaseName = a[0];
		String collectionName = a[1];
		MongoClient mongoClient = mongodbConn.iniMongodb();
		MongoDatabase db = mongoClient.getDatabase(databaseName);

		MongoCollection<Document> collection = db.getCollection(collectionName);
		// DBCollection collection = db.getCollection("firstCollection");
		// import static com.mongodb.client.model.Aggregates.*
		Document sum = null;
		if (aggrtMethod == "count") {
			sum = collection.aggregate(Arrays.asList(group(null, Accumulators.sum(asTitle, 1)))).first();// count
		}

		if (aggrtMethod == "sum") {
			sum = collection.aggregate(Arrays.asList(group(null, Accumulators.sum(asTitle, "$" + agrgtField))))
					.first();// sum
		}
		// jeig  asList(  null must yao youle ,beir err...
		if (aggrtMethod == "avg") {
			sum = collection.aggregate(Arrays.asList(  null, group( Accumulators.avg(asTitle, "$" + agrgtField),Accumulators.sum("asSum", "$" + agrgtField) )   ))
					.first();// sum
		}
		// Document{{_id=null, body=8}}
		
		//grp aggrt  
		// group("$region", Accumulators.sum("tally", 1)),
		return sum;
	}

	public static ArrayList<Object> query(String databaseName, String collectionName, String json, String Sort,
			Integer limit, int skip) {
		MongoClient mongoClient = mongodbConn.iniMongodb();
		MongoDatabase db = mongoClient.getDatabase(databaseName);

		MongoCollection<Document> collection = db.getCollection(collectionName);
		// DBCollection collection = db.getCollection("firstCollection");

		BasicDBObject query = BasicDBObject.parse(json);

		FindIterable<Document> docs = collection.find(query).sort(BasicDBObject.parse(Sort)).limit(limit).skip(skip);
		ArrayList<Object> li = Lists.newArrayList();
		for (Document document : docs) {
			li.add(document);
			// System.out.println(document);
		}
		return li;
	}

	@RequestMapping(value = "/mongodbQuery")
	public
	static Object query(String querystr, String Sort, Integer limit, int skip) {

		if (Sort == null)
			Sort = "{}";

		if (limit == null)
			limit = 1000;// from mybsql query def query limt
		/// db.friends.find({age: {$gt: 18}})
		String[] a = querystr.split("\\.");
		String databaseName = a[0];
		String collectionName = a[1];
		int stt = querystr.indexOf("find(");
		String findjson = querystr.substring(stt + 5, querystr.length() - 1);

		MongoClient mongoClient = mongodbConn.iniMongodb();
		MongoDatabase db = mongoClient.getDatabase(databaseName);
		// MongoDB //cant use some err
		/*
		 * MongoQueryParser parser = new MongoQueryParser(); MongoQuery mongoQuery =
		 * parser.parse(querystr, new HashMap()); BasicDBList results =
		 * mongoQuery.execute((DB) db);
		 */

		// String json
		return MongodbUtil.query(databaseName, collectionName, findjson, Sort, limit, skip);
	}

	public static Object mongodbQueryCount(String querystr) {
		/// db.friends.find({age: {$gt: 18}})
				String[] a = querystr.split("\\.");
				String databaseName = a[0];
				String collectionName = a[1];
				int stt = querystr.indexOf("find(");
				String findjson = querystr.substring(stt + 5, querystr.length() - 1);

				MongoClient mongoClient = mongodbConn.iniMongodb();
				MongoDatabase db = mongoClient.getDatabase(databaseName);
				MongoCollection<Document> collection = db.getCollection(collectionName);

				BasicDBObject query = BasicDBObject.parse(findjson);
				// collection.countDocuments()
				return collection.countDocuments(query);
				// MongoDB //cant use some err
				/*
				 * MongoQueryParser parser = new MongoQueryParser(); MongoQuery mongoQuery =
				 * parser.parse(querystr, new HashMap()); BasicDBList results =
				 * mongoQuery.execute((DB) db);
				 */

				// String json
	}
//	/**
//	private static Object query(String querystr, String Sort, Integer limit, int skip) {
//		if (Sort == null)
//			Sort = "{}";
//
//		if (limit == null)
//			limit = 1000;// from mybsql query def query limt
//		/// db.friends.find({age: {$gt: 18}})
//		String[] a = querystr.split("\\.");
//		String databaseName = a[0];
//		String collectionName = a[1];
//		int stt = querystr.indexOf("find(");
//		String findjson = querystr.substring(stt + 5, querystr.length() - 1);
//
//		MongoClient mongoClient = mongodbConn.iniMongodb();
//		MongoDatabase db = mongoClient.getDatabase(databaseName);
//		// MongoDB //cant use some err
//		/*
//		 * MongoQueryParser parser = new MongoQueryParser(); MongoQuery mongoQuery =
//		 * parser.parse(querystr, new HashMap()); BasicDBList results =
//		 * mongoQuery.execute((DB) db);
//		 */
//
//		// String json
////		return MongodbUtil.query(databaseName, collectionName, findjson, Sort, limit, skip);
////	}
//
//   */
}
