package project20280.stacksqueues;

import project20280.interfaces.Queue;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedCircularQueue<Integer> q = new LinkedCircularQueue<>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
        System.out.println(q);
        q.rotate();
        System.out.println(q);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void enqueue(E e) {
        // TODO Auto-generated method stub

    }

    @Override
    public E first() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E dequeue() {
        // TODO Auto-generated method stub
        return null;
    }

    public void rotate() {

    }

}
