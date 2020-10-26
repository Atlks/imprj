package comxx.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class Xml2Json {

	public static void main(String[] args) throws IOException {
		//extracted();
		//extracted1();
		//xmppmsg();
		xmppmsgByOrgjson();
	}
	private static void xmppmsgByOrgjson() throws IOException {
		 
		String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsgNoNest.xml";
		String	  TEST_XML_STRING=FileUtils.readFileToString(new File(f));
	  JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
      String jsonPrettyPrintString = xmlJSONObj.toString();
      System.out.println(jsonPrettyPrintString);
	}
	
	/**   <message from="romUser@xxx.com"></message>
	 *    {"@from":"romUser@xxx.com"}
	 * @throws IOException
	 */
	private static void xmppmsg() throws IOException {
 
		
		String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsgNoNest.xml";
		String	  xml=FileUtils.readFileToString(new File(f));
		  
		  
		XMLSerializer xmlSerializer = new XMLSerializer();  
		JSON json = xmlSerializer.read( xml );  
	   System.out.println(json);
	   //   {"test":"1.2","test2":"123"}
	}

	
	//   {"test":{"@to":"toUser@longbourn.lit/study","#text":"1.2"},"test2":"123"}
	private static void extracted1() throws IOException {
	String xml = "<hello><test to='toUser@longbourn.lit/study' >1.2</test><test2>123</test2></hello>";
		
		 
		  
		  
		XMLSerializer xmlSerializer = new XMLSerializer();  
		JSON json = xmlSerializer.read( xml );  
	   System.out.println(json);
	   //   {"test":"1.2","test2":"123"}
	}

	private static void extracted() {
		String xml = "<hello><test>1.2</test><test2>123</test2></hello>";
		
//		String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsg.xml";
//		  xml=FileUtils.readFileToString(new File(f));
		  
		  
		XMLSerializer xmlSerializer = new XMLSerializer();  
		JSON json = xmlSerializer.read( xml );  
	   System.out.println(json);
	   //   {"test":"1.2","test2":"123"}
	}

}
