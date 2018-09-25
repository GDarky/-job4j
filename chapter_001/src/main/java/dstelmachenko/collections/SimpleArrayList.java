package dstelmachenko.collections;

public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;
/**
 * Метод вставляет в начало списка данные.
 */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

/**
 * Реализовать метод удаления первого элемент в списке.
 */
    public E delete() {
        this.first = this.first.next;
        size--;
        return (this.first != null ? this.first.date : null);
    }

/**
 * Метод получения элемента по индексу.
 */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

/**
 * Метод получения размера коллекции.
 */
    public int getSize() {
        return this.size;
    }

/**
 * Класс предназначен для хранения данных.
 */
    private static class Node<E> {
        E date;
        Node<E> next;
        Node(E date) {
            this.date = date;
        }
    }
}

