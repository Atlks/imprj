package comxx.controler;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee.dynamicmongoquery.MongoQuery;
import com.ee.dynamicmongoquery.MongoQueryParser;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import comxx.util.MongodbUtil;
import comxx.util.mongodbConn;

@RestController
public class MongodbRestApi {

	public static void main(String[] args) {

		// mongoClient.
		String databaseName = "db1";
		String collectionName = "coll1";
		String json = "{}";
		json = "{100}";

		String querystr = "db1.coll1.find({body:100})";
		// querystr = "db1.coll1.find({}";
		Object li = query(querystr, null, 500, 0);
		System.out.println(li);
		System.out.println("f");
	}

	@RequestMapping(value = "/mongodbQuery")
	private static Object query(String querystr, String Sort, Integer limit, int skip) {

		return MongodbUtil.query(querystr, Sort, limit, skip);
	}



	@RequestMapping(value = "/mongodbQueryCount")
	private static Object query(String querystr) {

		return MongodbUtil.mongodbQueryCount(querystr);

	}

 
	
	@RequestMapping(value = "/mongodbQueryAggrt")
	private static Object mongodbQueryAggrt(String coll,String aggrtFun,String aggrtField,String asShowTitle) {

		/// db.friends.find({age: {$gt: 18}})
		Document	sum = MongodbUtil.aggrt(coll,  aggrtFun,aggrtField,asShowTitle);
		return sum;
		 

	}
	
	

}
