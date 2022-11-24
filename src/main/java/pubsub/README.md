## Publish -subscribe design pattern
The publish-subscribe (or pub/sub) messaging pattern is a design pattern that provides a framework for exchanging messages that allows for loose coupling and scaling between the sender of messages (publishers) and receivers (subscribers) on topics they subscribe to.

Messages are sent (pushed) from a publisher to subscribers as they become available. The host (publisher) publishes messages (events) to channels (topics). Subscribers can sign up for the topics they are interested in.

## Components of a pub-sub pattern
There are three components in pub-sub pattern:
1. Publisher: sends messages to the PubSub Service tagged with specific topic, without any knowledge of message subscribers
2. Subscriber: will only get the messages for which topics they are registered with the PubSubService.
3. PubSubService: acts as a middle-man between publisher and subscriber. publisher tags each message with topic and send it to pub-sub service. Subscriber registers itself with PubSubService and tells in what topic it's interested in.

## How is it different from Observer pattern?
The basic difference is in Observer pattern observer and observables are aware of each other and are highly coupled. In pub-sub publisher and subscriber are not aware of each other and are connected using a central broker. Hence in pub-sub the publisher and subscribers are loosely coupled.

## Coding problem to understand this pattern
Q: Implement a message queuing system

**Functional requirements**
1. Create your own queue that will hold messages in form of JSON. Standard library queues were not allowed. -> done
2. There was one publisher that can generate messages. => done
3. There are multiple subscribers that will listen messages satisfying a particular regex. -> done, instead of regex used topic instead, but can be easily extended to use regex.
4. Subscribers should not be tightly coupled to system and can be added or removed at runtime. -> done
5. When a subscriber is added to the system, it registers callback function along with it. And this callback function will be invoked in case some message arrives. -> done
6. There must a retry mechanism for handling error cases when some exception occurs in listening/ processing messages, that must be retried. -> will implement later



