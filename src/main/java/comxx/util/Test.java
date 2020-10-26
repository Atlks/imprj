package comxx.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Test {

    public static void main(String[] args) throws Exception {

        // 1. ����һ��map
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","chris");
        map.put("island","faranga");

        // 2. ����xml����ǩ
        XStream xStream = new XStream();
    //   xStream.registerConverter(new MapEntryConverter());
   //     xStream.alias("root", Map.class);

        // 3. ��mapת����xml��ʽ
        String xml = xStream.toXML(map);
        System.out.println(xml);

        // 4. ����xml�ַ�����ת��ΪMap
    	String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsgNoNest.xml";
		  xml=FileUtils.readFileToString(new File(f));
		  System.out.println(xml);
		  xStream.setMode(XStream.NO_REFERENCES);
		  Object  extractedMap=  (Object ) xStream.fromXML(xml);
        System.out.println(extractedMap);

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
    public static class MapEntryConverter implements Converter {

        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                writer.startNode(entry.getKey().toString());
                Object val = entry.getValue();
                if ( null != val ) {
                    writer.setValue(val.toString());
                }
                writer.endNode();
            }

        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

            Map<String, String> map = new HashMap<String, String>();

            while(reader.hasMoreChildren()) {
                reader.moveDown();

                String key = reader.getNodeName(); // nodeName aka element's name
                String value = reader.getValue();
                map.put(key, value);

                reader.moveUp();
            }

            return map;
        }

    }
}