package pubsub.service;

import lombok.NonNull;
import pubsub.model.Message;
import pubsub.subscriber.SubscriberQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService {

    private Map<String, List<SubscriberQueue>> topicToSubscriberQueuesMap = new HashMap<>();
    private static NotificationService instance;
    public static NotificationService getInstance() {
        if(instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void registerToNotificationServiceTopic(@NonNull final SubscriberQueue subscriberQueue,
                                                   @NonNull final String topic) {
        List<SubscriberQueue> subscriberQueueList;
        if(topicToSubscriberQueuesMap.containsKey(topic)) {
            subscriberQueueList = topicToSubscriberQueuesMap.get(topic);
        } else {
            subscriberQueueList = new ArrayList<>();
        }
        subscriberQueueList.add(subscriberQueue);
        topicToSubscriberQueuesMap.put(topic, subscriberQueueList);
    }

    public void publish(@NonNull final String topic, @NonNull final Message messageJson) {
        List<SubscriberQueue> subscriberQueueList = topicToSubscriberQueuesMap.get(topic);
        subscriberQueueList.forEach(subscriberQueue -> {
            subscriberQueue.receive(messageJson);
        });
    }
}
