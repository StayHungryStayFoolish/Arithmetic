package ink.bonismo.notebook.arithmetic.data_structure;

import org.w3c.dom.Node;

import java.util.LinkedList;

public class SingleLinkedList {

    private int size;
    private Node first;

    public SingleLinkedList() {
    }

    public  void addFirst(Object val) {
        addIndex(0, val);
    }

    public  void addTail(Object val) {
        addIndex(size, val);
    }

    private void addIndex(int index, Object val) {
        if (index <= 0) {
            first = new Node(val, first);
        }
    }

    private boolean checkIndex(int index) {
        return !(index < 0 || index > size - 1);
    }


    private class Node{
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this(data);
            this.next = next;
        }
    }
}
