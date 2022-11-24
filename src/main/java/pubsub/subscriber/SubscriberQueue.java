package pubsub.subscriber;

import lombok.NonNull;
import pubsub.model.Message;
import pubsub.queue.CustomQueue;
import pubsub.service.NotificationService;

public class SubscriberQueue {
    private CustomQueue customQueue;
    private String topicToSubscribe;

    private NotificationService notificationService;

    public SubscriberQueue(@NonNull final String topic) {
        this.customQueue = new CustomQueue();
        this.notificationService = NotificationService.getInstance();
        this.topicToSubscribe = topic;
        this.notificationService.registerToNotificationServiceTopic(this, topic);
    }
    public void receive(@NonNull final Message messageJson) {
        customQueue.enqueue(messageJson);
    }

    public Message getLatestMessageFromQueue() {
        return customQueue.deQueue();
    }
}
