import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PTPSend {
    //�����˺�
    private String userName = "";
    //��������
    private String password = "";
    //���ӵ�ַ
    private String brokerURL = "tcp://127.0.0.1:61616";
    //connection�Ĺ���
    private ConnectionFactory factory;
    //���Ӷ���
    private Connection connection;
    //һ�������Ự
    private Session session;
    //Ŀ�ĵأ���ʵ�������ӵ��ĸ����У�����ǵ�Ե㣬��ô����ʵ����Queue������Ƕ���ģʽ��������ʵ����Topic
    private Destination destination;
    //�����ߣ����ǲ������ݵĶ���
    private MessageProducer producer;
    
    public static void main(String[] args) {
        PTPSend send = new PTPSend();
        send.start();
    }
    
    public void start(){
        try {
            //�����û��������룬url����һ�����ӹ���
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            //�ӹ����л�ȡһ������
            connection = factory.createConnection();
            //���Թ�������費дҲ�ǿ��Եģ��������ϵĸ����ĵ���д��
            connection.start();
            //����һ��session
            //��һ������:�Ƿ�֧���������Ϊtrue�������Եڶ�����������jms����������ΪSESSION_TRANSACTED
            //�ڶ�������Ϊfalseʱ��paramB��ֵ��ΪSession.AUTO_ACKNOWLEDGE��Session.CLIENT_ACKNOWLEDGE��DUPS_OK_ACKNOWLEDGE����һ����
            //Session.AUTO_ACKNOWLEDGEΪ�Զ�ȷ�ϣ��ͻ��˷��ͺͽ�����Ϣ����Ҫ������Ĺ����������ǽ��ն˷����쳣��Ҳ�ᱻ�����������ͳɹ���
            //Session.CLIENT_ACKNOWLEDGEΪ�ͻ���ȷ�ϡ��ͻ��˽��յ���Ϣ�󣬱������javax.jms.Message��acknowledge������jms�������Żᵱ�����ͳɹ�����ɾ����Ϣ��
            //DUPS_OK_ACKNOWLEDGE��������ȷ��ģʽ��һ�����շ�Ӧ�ó���ķ������ôӴ�����Ϣ�����أ��Ự����ͻ�ȷ����Ϣ�Ľ��գ����������ظ�ȷ�ϡ�
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //����һ�������Ŀ�ĵأ���ʵ��һ�¾�֪���ˣ�activemq������ͬʱֻ����һ�����аɣ��������������һ����Ϊ"text-msg"�Ķ��У�����Ự���ᵽ������У���Ȼ�����������в����ڣ����ᱻ����
            destination = session.createQueue("text-msg");
            //��session�У���ȡһ����Ϣ������
            producer = session.createProducer(destination);
            //���������ߵ�ģʽ�������ֿ�ѡ
            //DeliveryMode.PERSISTENT ��activemq�رյ�ʱ�򣬶������ݽ��ᱻ����
            //DeliveryMode.NON_PERSISTENT ��activemq�رյ�ʱ�򣬶�����������ݽ��ᱻ���
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
           
            for(int i = 0 ; i < 100 ; i ++){
                //����һ����Ϣ
            //	producer.
            	 //����һ����Ϣ����Ȼ����Ϣ�������кܶ࣬�����֣��ֽڣ������,����ͨ��session.create..��������������
                TextMessage textMsg = session.createTextMessage("�Ǻ�wwwwwwwww"+String.valueOf(i));
            	
                producer.send(textMsg);
               
                
            }
            
            System.out.println("������Ϣ�ɹ�");
            //���������ߵĶ���ر��ˣ�����������Ŷ
            producer.close();
           
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}