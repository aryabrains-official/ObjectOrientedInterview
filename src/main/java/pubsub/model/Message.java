package pubsub.model;

import lombok.Data;

@Data
public class Message {
    private String messageId;
    private String messagePayload;
}
