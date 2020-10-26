package comxx.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml2Map2json 
{
	public static void main(String[] args) throws IOException {
		String f="C:\\Users\\u\\OneDrive\\����\\imdoc fix\\xmppMsg.xml";
		String xml=FileUtils.readFileToString(new File(f));
		Map m=getMapFromXML
	}
public static Map<String,Object> getMapFromXML(String xmlString) throws ParserConfigurationException, IOException, SAXException {

      //������Dom�ķ�ʽ�����ذ�������ҪĿ���Ƿ�ֹAPI�����ذ��ֶ�
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
    //  InputStream is =  Util.getStringStream(xmlString);
      Document document = builder.parse(xmlString);

      //��ȡ��document�����ȫ�����
      NodeList allNodes = document.getFirstChild().getChildNodes();
      Node node;
      Map<String, Object> map = new HashMap<String, Object>();
      int i=0;
      while (i < allNodes.getLength()) {
          node = allNodes.item(i);
          if(node instanceof Element){
              map.put(node.getNodeName(),node.getTextContent());
          }
          i++;
      }
      return map;

}
}
