package pubsub.queue;

import lombok.Data;
import lombok.NonNull;
import pubsub.model.Message;

@Data
public class Node {
    private Message data;
    private Node nextNode;

    public Node(@NonNull final Message data) {
        this.data = data;
        this.nextNode = null;
    }
}
