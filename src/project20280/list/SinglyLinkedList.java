package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        Node<E> current = head;
        for (int i = 0; i < position; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }


    @Override
    public void add(int position, E e) {
        if (position == 0) {
            addFirst(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.getNext();
            }
            Node<E> newNode = new Node<>(e, current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    @Override
    public void addFirst(E e) {
        head =  new Node<E>(e, head);
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null); // node will eventually be the tail
        Node<E> last = head;
        if(last == null) {
            head = newest;
        }
        else {
            while (last.getNext() != null) {// advance to the last node
                    last = last.getNext();
            }
            last.setNext(newest); // new node after existing tail
        }
        size++;
    }

    @Override
    public E remove(int position) {
        if (position == 0) {
            removeFirst();
        }
        Node<E> current = head;
        Node<E> previous = null;
        for (int i = 0; i < position; i++) {
            previous = current;
            current = current.getNext();
        }
        E removedNode = current.getElement();
        previous.setNext(current.getNext());
        size--;
        return removedNode;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E removedElement = head.getElement();
        head = head.getNext();
        size--;
        return removedElement;
    }


    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        Node<E> current = head;
        Node<E> previous = null;
        while (current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        E removedElement = current.getElement();
        previous.setNext(null);
        size--;
        return removedElement;
    }


    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
    
    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> other) {
        // Initialize pointers to traverse both lists
        Node<E> a = head;  // Pointer for the current node in the original list
        Node<E> b = other.head;  // Pointer for the current node in the other list
    
        // Create a new SinglyLinkedList to store the merged result
        SinglyLinkedList<E> result = new SinglyLinkedList<>();
    
        // Loop through both lists
        while (true) {
            // If we have reached the end of list 'a'
            if (a == null) {
                // Add all remaining elements from list 'b' to the result list
                while (b != null) {
                    result.addLast(b.getElement());  // Add the element of 'b' to the result list
                    b = b.getNext();  // Move to the next node in list 'b'
                }
                break;  // Exit the loop when 'a' is fully traversed
            }

            // If we have reached the end of list 'b'
            else if (b == null) {
                // Add all remaining elements from list 'a' to the result list
                while (a != null) {
                    result.addLast(a.getElement());  // Add the element of 'a' to the result list
                    a = a.getNext();  // Move to the next node in list 'a'
                }
                break;  // Exit the loop when 'b' is fully traversed
            }

            // If both lists still have elements
            else {
                // Compare the elements at the current positions of 'a' and 'b'
                if (((Comparable<E>) a.getElement()).compareTo(b.getElement()) <= 0) {
                    // If the element in 'a' is less than or equal to the element in 'b', add the element of 'a' to the result list
                    result.addLast(a.getElement());
                    a = a.getNext(); 
                } else {
                    // If the element in 'b' is less than the element in 'a', // add the element of 'b' to the result list
                    result.addLast(b.getElement());
                    b = b.getNext();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        //System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
    
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
    
        //System.out.println(ll);
        ll.remove(5);
        //System.out.println(ll);
    
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        
        // Adding elements to the first list
        list1.addLast(1);
        list1.addLast(3);
        list1.addLast(5);
        list1.addLast(7);
        
        // Adding elements to the second list
        list2.addLast(2);
        list2.addLast(4);
        list2.addLast(6);
        list2.addLast(8);
        
        // Printing the first list
        System.out.println("List 1: " + list1);
        
        // Printing the second list
        System.out.println("List 2: " + list2);
        
        // Merging list1 and list2 into result
        SinglyLinkedList<Integer> result = list1.sortedMerge(list2);
        System.out.println("Merged List: " + result);
    }    

}
