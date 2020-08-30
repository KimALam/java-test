package test.iter;

import java.util.Iterator;

class CustomLinkedList<E> implements Iterable<E> {
    private Node<E> head, tail;

    public void add(E data) {
        Node<E> node = new Node<>(data, null);
        if (head == null) {
            tail = head = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    private class Node<E> {
        E data;
        Node<E> next;
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public E getData() { return data; }
        public void setData(E data) { this.data = data; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> next) { this.next = next; }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        Node<E> current;

        ListIterator() {
            current = CustomLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
