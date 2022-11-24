package pubsub.queue;

import pubsub.model.Message;

public class CustomQueue {
    private Node front;
    private Node rear;

    int size;

    public CustomQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(Message data) {
        Node temp = new Node(data);
        if(front == null) {
            front = temp;
        } else {
            rear.setNextNode(temp);
        }
        size++;
        rear = temp;
    }

    public Message deQueue() {
        if (size == 0) {
            throw new IllegalArgumentException("Cannot dequeue an empty list");
        }
        Node temp = this.front;
        front = front.getNextNode();
        this.size--;
        //if last node was dequeued
        if(front == null) {
            this.rear = null;
        }
        return temp.getData();
    }

    public boolean isEmpty() {
        if(size == 0) return true;
        return false;
    }

}
