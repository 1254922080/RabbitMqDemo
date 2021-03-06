package Topic;

import Units.RabbitMqConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class TopicSend {
    private static final String EXCHANGE_NAME = "Test_TopicExchange";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionFactory.geyConnection();
        //1 通过connection创建一个Channel
        Channel channel = connection.createChannel();
        //2.申明交换机  topic exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //3 通过Channel发送数据
        for (int i = 0; i < 5; i++) {
            String msg = "TopicQuery!  " + i;
            Thread.sleep(1000);
            //1.交换机   2.RoutingKey  3. 是否持久化消息
            //String routingKey = "error";
            String routingKey = "log.error";
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
            System.out.println(msg);
        }
        //4 记得要关闭相关的连接
        channel.close();
        connection.close();
    }
}
