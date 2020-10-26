package comxx.util;

import org.jivesoftware.smack.packet.Message;

import com.alibaba.fastjson.JSON;

public class Xmpp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Message m=new Message();
		m.setFrom("me");
		m.setTo("toto");
		m.setBody("bodyyyyy");
		m.setType(Message.Type.chat);
//		m.setPacketID(packetID);
		m.addBody("en", "en_body");
		System.out.println(JSON.toJSONString(m));
		

	}

}
