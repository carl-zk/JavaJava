package utils.common;

/**
 * Created by hero on 17-3-25.
 */
public class Queue<T> {
    public int length;
    private Node head;
    private Node tail;

    public Queue() {
        head = tail = null;
        length = 0;
    }

    public void addLast(T element) {
        Node node = new Node(element, null);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        length++;
    }

    public T removeFirst() {
        T element = head.value;
        head = head.next;
        length--;
        return element;
    }

    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
