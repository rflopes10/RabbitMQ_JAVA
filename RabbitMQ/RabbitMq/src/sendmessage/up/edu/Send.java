package sendmessage.up.edu;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;

public class Send {

  private final static String QUEUE_NAME = "teste";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    
    String mensagem;
    Scanner entrada = new Scanner(System.in);
    
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println("Digite sua mensagem:\n");
    mensagem = entrada.nextLine();
    //String mensagem = "E ai fei!";
    channel.basicPublish("", QUEUE_NAME, null, mensagem.getBytes("UTF-8"));
    System.out.println(" [x] Enviado '" + mensagem + "'");

    channel.close();
    connection.close();
  }
}