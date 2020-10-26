package comxx.util;
 

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.openjpa.enhance.RuntimeUnenhancedClassesModes;

import com.google.common.collect.Maps;
//import com.kok.sport.utils.UrlUtil;
//import com.mysql.cj.core.conf.url.ConnectionUrlParser;

public class JpaUtil {

	public static EntityManagerFactory getFac() {
		 
		return JpaUtil_getFac();
	}
	
	private static EntityManagerFactory JpaUtil_getFac() {
		String url2;// = "jdbc:mysql://182.16.50.115:3306/dev_kok_sport?user=root&password=cjds1023123&allowMultiQueries=true";
	
	//	url2=MybatisUtil. getMysqlUrl();
//		System.out.println(url2);
//				String mysqlConnUrl =url2;
//		ConnectionUrlParser connStringParser = ConnectionUrlParser.parseConnectionString(mysqlConnUrl);
//		System.out.println(connStringParser);
//
//		Object url = mysqlConnUrl;
//		Object usr = UrlUtil.parse4Q(connStringParser.getQuery()).get("user");
//		Object pwd = UrlUtil.parse4Q(connStringParser.getQuery()).get("password");
 		Map properties = Maps.newLinkedHashMap();
//		properties.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
//		properties.put("javax.persistence.jdbc.url",url);
//		properties.put("javax.persistence.jdbc.user", usr.toString()); // if needed
//		properties.put("javax.persistence.jdbc.password", pwd.toString()); // if needed
 		properties.put("openjpa.RuntimeUnenhancedClasses", RuntimeUnenhancedClassesModes.SUPPORTED); // if needed
	 //	properties.put("openjpa.RuntimeUnenhancedClasses", "supported"); // if needed
		
	  
		
	//	System.out.println(properties);
		// Create a new EntityManagerFactory using the System properties.
		// The "hellojpa" name will be used to configure based on the
		// corresponding name in the META-INF/persistence.xml file
 	EntityManagerFactory factory = Persistence.createEntityManagerFactory("wmsPersisteUnitName",properties);
 
		return factory;
	}

	public static Object querySql(String sql) {
		EntityManagerFactory factory = JpaUtil.getFac();

		// Create a new EntityManager from the EntityManagerFactory. The
		// EntityManager is the main object in the persistence API, and is
		// used to create, delete, and query objects, as well as access
		// the current transaction
		EntityManager em = factory.createEntityManager();

		// Perform a simple query for all the Message entities
		//select * from sys_tab
		//mlt sql Map.class only ret first rs
		
	//	Query q = em.createNativeQuery("select 1;select 1;", Map.class);
		/*em.createNativeQuery("select 1 as age,'ati' name;select 2;" );
		 * [[1,"ati"]]   ;select 2;
		 * */
		//cant use multi sql
		Query q = em.createNativeQuery(sql,Map.class );
		List li = q.getResultList();
		return li;
	}

}
