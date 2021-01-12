package ink.bonismo.notebook.arithmetic.data_structure;

import org.w3c.dom.Node;

public class SingleLinkedList<E> {

    private int size;
    private Node<E> first;

    public SingleLinkedList() {
    }

    public void add(E val) {
        addTail(val);
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            Node<E> result = findNodeAtIndex(i);
            if (result != null) {
                if (o == result.data) {
                    deleteAtIndex(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void addFirst(E val) {
        addAtIndex(0, val);
    }

    public void addTail(E val) {
        addAtIndex(size, val);
    }

    public void deleteAtIndex(int index) {
        if (!rangeCheck(index)) {
            return;
        }
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> prev = findNodeAtIndex(index - 1);
            if (prev == null) {
                return;
            }
            prev.next = prev.next.next;
            size--;
        }
    }

    private void addAtIndex(int index, E val) {
        if (index <= 0) {
            first = new Node<>(val, first);
        } else {
            Node prev = findNodeAtIndex(index - 1);
            if (prev == null) {
                return;
            }
            prev.next = new Node<>(val, prev.next);
        }
        size++;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                node = first;
            } else {
                node = node.next;
            }
            if (i != size - 1) {
                result.append(node.data).append(",");
            } else {
                result.append(node.data);
            }
        }
        return result.toString();
    }

    private boolean rangeCheck(int index) {
        return !(index < 0 || index > size - 1);
    }


    private Node<E> findNodeAtIndex(int index) {
        if (!rangeCheck(index)) {
            return null;
        }
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }


    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node next) {
            this(data);
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.addTail("a");
        list.addTail("f");
        list.addTail("b");
        list.addTail("f");
        list.addTail("e");

        System.out.println(list.toString());
        list.remove("f");
        System.out.println(list.toString());

        SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();
        linkedList.addTail(1);
        linkedList.addTail(2);
        linkedList.addTail(3);
        linkedList.addTail(4);
        linkedList.addTail(5);

        System.out.println(linkedList.toString());
    }
}
