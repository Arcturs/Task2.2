package ru.vsu.csf.Sashina;

import java.util.Iterator;

public class LinkedListNum<T> {

    private class Node<T> {
        public T value;
        public Node<T> next;

        public Node (T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int it = 0;
    private double EPS = 1E-9;

    public void addAtTheStart (T value) {
        head = new Node<>(value, head);
        if (it == 0) {
            tail = head;
        }
        it++;
    }

    public void addAtTheEnd (T value) {
        Node<T> newNode = new Node<>(value, null);
        if (it == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        it++;
    }

    public int size () {
        return it;
    }

    public boolean isEmpty () {
        boolean flag = false;
        if (it == 0) {
            flag = true;
        }
        return flag;
    }

    public T getHead () throws Exception {
        if (isEmpty()) {
            throw new Exception("Лист пустой");
        } else {
            return head.value;
        }
    }

    public T getTail () throws Exception {
        if (isEmpty()) {
            throw new Exception("Лист пустой");
        } else {
            return tail.value;
        }
    }

    public T getValue (int index) throws Exception {
        if (index < 0 || index >= it) {
            throw new Exception("Неправильный индекс");
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.value;
        }
    }

    public boolean isSorted () {
        try {
            if (isEmpty()) {
                return false;
            } else {
                boolean flag = true;
                for (int i = 1; i < it; i++) {
                    if ((double) getValue(i) -  (double) getValue(i - 1) < 0) {
                        flag = false;
                        break;
                    }
                }
                return flag;
            }
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean contains (T value) {
        try {
            boolean flag = false;
            Node<T> current = head;
            for (int i = 0; i < it; i++) {
                if (Math.abs((double) value - (double) current.value) <= EPS) {
                    flag = true;
                    break;
                } else {
                    current = current.next;
                }
            }
            return flag;
        } catch (Exception exp) {
            return false;
        }
    }

    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            Node<T> current;

            public LinkedListIterator(Node<T> head) {
                current = head;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }
}
