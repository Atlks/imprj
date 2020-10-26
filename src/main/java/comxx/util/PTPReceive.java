package comxx.util;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class PTPReceive {
    //�����˺�
    private String userName = "";
    //��������
    private String password = "";
    //���ӵ�ַ
    private String brokerURL = "tcp://localhost:61616";
    //connection�Ĺ���
    private ConnectionFactory factory;
    //���Ӷ���
    private Connection connection;
    //һ�������Ự
    private Session session;
    //Ŀ�ĵأ���ʵ�������ӵ��ĸ����У�����ǵ�Ե㣬��ô����ʵ����Queue������Ƕ���ģʽ��������ʵ����Topic
    private Destination destination;
    //�����ߣ����ǽ������ݵĶ���
    private MessageConsumer consumer;
    public static void main(String[] args) throws Exception {
        PTPReceive receive = new PTPReceive();
        receive.start();
        System.out.print("fff");
    }
    
    public void start() throws Exception{
      
            //�����û��������룬url����һ�����ӹ���
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            //�ӹ����л�ȡһ������
            connection = factory.createConnection();
            //���Թ�������費дҲ�ǿ��Եģ��������ϵĸ����ĵ���д��?
            connection.start();
            //����һ��session
            //��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
            //�ڶ�������Ϊfalseʱ��paramB��ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
            //Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���?
            //Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
            //DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //����һ�������Ŀ�ĵأ���ʵ��һ�¾�֪���ˣ�activemq������ͬʱֻ����һ�����аɣ��������������һ�����?"text-msg"�Ķ��У�
            //����Ự���ᵽ������У���Ȼ�����������в����ڣ����ᱻ����
        //    Destination   destination = session.createQueue("text-msg");
            ///topic/chat.general
            Destination   destination = session.createTopic("chat.general");
            //����session������һ�������߶���
            consumer = session.createConsumer(destination);
            
            
            //ʵ��һ����Ϣ�ļ�����
            //ʵ��������������Ժ�ֻҪ����Ϣ���ͻ�ͨ��������������յ�
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                    	   System.out.println("receed----"+message);
                    	//   message.get
                        //��ȡ�����յ�����
                    	   
                    	   if (message instanceof ActiveMQTextMessage) {
                    		    ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
                    		   // mqDelegate.execute(params, amqMessage.getText());
                    		    String text = ((TextMessage)message).getText();
                                System.out.println("receed"+text);
                    		} else {
                    		    BytesMessage bm = (BytesMessage) message;
                    		    byte data[] = new byte[(int) bm.getBodyLength()];
                    		    bm.readBytes(data);
                    		    String msg = new String(data);
                    		    JSONObject jo=   JSONObject.parseObject(msg );//JSON�ַ���ת����
                    		    MongoClient mongoClient =mongodbConn. iniMongodb();
                    			MongoDatabase database = mongoClient.getDatabase("db1");
                    			MongoCollection<Document> collection = database.getCollection("coll1");
                    			
                    			
                    			
                    		    Document document2 = new Document("from", jo.getString("from")).  
                    	                append("to", jo.getString("to")).  
                    	                append("body", jo.getString("body")).append("msgori", msg); 
                    	        collection.insertOne(document2);
                    		    System.out.println(msg);
                    		}
                       
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            //�رս��նˣ�Ҳ������ֹ����Ŷ
//            consumer.close();
     
    }
}
