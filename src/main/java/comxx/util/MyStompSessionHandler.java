package comxx.util;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

public class MyStompSessionHandler implements StompSessionHandler {
	
	static Logger logger = Logger.getLogger(MyStompSessionHandler.class.getName());

	public Type getPayloadType(StompHeaders arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void handleFrame(StompHeaders arg0, Object payload) {
		// Message msg = (Message) payload;
		 //   logger.info("Received : " + msg.getText()+ " from : " + msg.getFrom());

	}

	public void afterConnected(StompSession session, StompHeaders arg1) {
		 session.subscribe("/topic/messages", this);
		    session.send("/app/chat", "ttttttttttttt");

	}

	public void handleException(StompSession arg0, StompCommand arg1, StompHeaders arg2, byte[] arg3, Throwable arg4) {
		// TODO Auto-generated method stub

	}

	public void handleTransportError(StompSession arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

}
