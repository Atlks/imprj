package comxx.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.XML;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;

public class Map2Xml2json {
	  // private static XmlMapper xmlMapper = new XmlMapper();
	    private static ObjectMapper objectMapper = new ObjectMapper();

		public static void main(String[] args) throws Exception {
		//xmppMsgNoNest
		String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsgNoNest.xml";
		String xml=FileUtils.readFileToString(new File(f));
		
		
		  
	   //jeig must bind bean obj...
	 //	  Object stu = (Object)xstream.fromXML(t);
		  
		  System.out.println(xml);
		  
		  
		  Object stu = xml2jsonByXstream(xml);
		  System.out.println(JSON.toJSONString(stu,true));

//	    XmlMapper xmlMapper = new XmlMapper();
//        JSONObject param = xmlMapper.readValue(xml, JSONObject.class);
//        
        
//		Map  m=Maps.newConcurrentMap();
//		m.put("k1", "arg1");
//		  XStream xstream =new XStream();
////	        xstream.processAnnotations(poClass);
////	        xstream.processAnnotations(getInnerClasses(poClass));
//	        return xstream.toXML(pojo);
		//xmppMsgNoNest
	}
		
		
		/**
		 * {
	"empty":false
}

		 * @param YOUR_XML_STRING
		 * @return
		 */
		 public static Object xml2jsonByJsonbobject(String YOUR_XML_STRING) {
		        
		            JSONObject xmlJSONObj = XML.toJSONObject(YOUR_XML_STRING);
		       return xmlJSONObj;
		    }

		private static Object xml2jsonByXstream(String xml) throws Exception {
			
			  StringReader reader = new StringReader(xml);
			  XStream	  xstream = new XStream();
			//  xstream.registerConverter(new MapEntryConverter());
			  //jeig ok...but cant nest tag..
			  ObjectInputStream     in = xstream.createObjectInputStream(reader);
			  Object stu = (Object) in.readObject();
			return stu;
		}

		//cant use ..but not err,,output  {"empty":false}
		private static Object xml2jsonByOrgjson(String xml) {
			Object stu =  JSONML.toJSONObject(xml);
			return stu;
		}
	
	   /**
     * xml�ַ���ת��JSON��ʽ�ַ���
     * 
     * @param xmlStr
     * @return
     */
//    public static String convertXmlToJson(String xml) {
//
//
//        StringWriter w = new StringWriter();
//        try {
//            JsonParser jp = xmlMapper.getFactory().createParser(xml);
//            JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
//            while (jp.nextToken() != null) {
//                jg.copyCurrentEvent(jp);
//            }
//            jp.close();
//            jg.close();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return w.toString();
//    }

}
