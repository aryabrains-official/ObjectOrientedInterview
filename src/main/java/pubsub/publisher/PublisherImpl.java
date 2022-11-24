package pubsub.publisher;

import lombok.NonNull;
import pubsub.model.Message;
import pubsub.service.NotificationService;

public class PublisherImpl implements IPublisher {
    private NotificationService notificationService;
    private String topic;

    public PublisherImpl(final String topic) {
        this.notificationService = NotificationService.getInstance();
        this.topic = topic;
    }

    @Override
    public void publishMessage(@NonNull Message messageJson) {
        this.notificationService.publish(this.topic, messageJson);
    }
}
