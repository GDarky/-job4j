package dstelmachenko.collections;

public class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T i) {
        value = i;
        next = null;
    }

    public void setNext(Node<T> node) {
        next = node;
    }

    public boolean hasCycle(Node first) {
        Node currentNode1 = first;
        Node currentNode2 = first;
        boolean result = false;
        if (first != null && first.next != null) {
            while (currentNode1.next != null && currentNode2.next != null) {
                currentNode1 = currentNode1.next;
                currentNode2 = currentNode2.next;
                currentNode2 = currentNode2.next;
                if  (currentNode1.equals(currentNode2)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
