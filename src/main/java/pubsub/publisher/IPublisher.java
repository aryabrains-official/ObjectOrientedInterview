package pubsub.publisher;

import lombok.NonNull;
import pubsub.model.Message;

public interface IPublisher {
    void publishMessage(@NonNull final Message messageJson);
}
