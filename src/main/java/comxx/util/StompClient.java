package comxx.util;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;


// not need use stomp client  ....def tcp mq driver is more easy
public class StompClient {

	public static void main(String[] args) {
		WebSocketClient client = new StandardWebSocketClient();
		 
		WebSocketStompClient stompClient = new WebSocketStompClient(client);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		 
		StompSessionHandler sessionHandler = new MyStompSessionHandler();

	}

}
