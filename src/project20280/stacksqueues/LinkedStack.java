package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll;

    public LinkedStack() {
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        ll.addFirst(e);
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return ll.first();
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        return ll.removeFirst();
    }

    public String toString() {
        return ll.toString();
    }
}
