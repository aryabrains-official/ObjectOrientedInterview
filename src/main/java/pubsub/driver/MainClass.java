package pubsub.driver;

import org.junit.jupiter.api.Assertions;
import pubsub.model.Message;
import pubsub.publisher.PublisherImpl;
import pubsub.subscriber.SubscriberQueue;

public class MainClass {

    public static void main(String[] args) {
        PublisherImpl publisher1 = new PublisherImpl("Topic1");
        PublisherImpl publisher2 = new PublisherImpl("Topic2");

        SubscriberQueue subscriberQueue1 = new SubscriberQueue("Topic1");
        SubscriberQueue subscriberQueue2 = new SubscriberQueue("Topic1");
        SubscriberQueue subscriberQueue3 = new SubscriberQueue("Topic2");


        publisher1.publishMessage(getNewMessage("t1:msg1", "This is message1:part1"));
        publisher1.publishMessage(getNewMessage("t1:msg2", "This is message1:part2"));
        publisher2.publishMessage(getNewMessage("t2:msg1", "This is message2"));

        Assertions.assertEquals(subscriberQueue1.getLatestMessageFromQueue().getMessageId(), "t1:msg1");
        Assertions.assertEquals(subscriberQueue1.getLatestMessageFromQueue().getMessageId(), "t1:msg2");
        Assertions.assertEquals(subscriberQueue2.getLatestMessageFromQueue().getMessageId(), "t1:msg1");
        Assertions.assertEquals(subscriberQueue2.getLatestMessageFromQueue().getMessageId(), "t1:msg2");
        Assertions.assertEquals(subscriberQueue3.getLatestMessageFromQueue().getMessageId(), "t2:msg1");
    }

    private static Message getNewMessage(String messageId, String messagePayload) {
        Message message = new Message();
        message.setMessageId(messageId);
        message.setMessagePayload(messagePayload);
        return message;
    }
}
