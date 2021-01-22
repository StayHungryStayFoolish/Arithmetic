package ink.bonismo.notebook.arithmetic.data_structure;

/**
 * 双向链表
 * 1. 操作链表需要注意边界条件
 * 1.1 操作头节点，需要设置 prev 为 null
 * 1.2 操作中间节点，需要将当前节点的前驱节点的 next 指针和后驱节点的 prev 指针
 * 1.3 操作尾节点，需要设置 next 为 null
 * 1.4 注意 GC 性能，如果不设置为 null，当有另外一个遍历持有该元素时，恰巧在当前 GC 时，不会移除该元素，因为 GC Roots 还对该元素有可达性
 * @param <E>
 */
public class DoublyLinkedList<E> {

    public static void main(String[] args) {
        DoublyLinkedList<String> doublyList = new DoublyLinkedList<>();
//        doublyList.addAtIndex(0,"a");
//        doublyList.addAtIndex(1,"b");
//        doublyList.addAtIndex(2,"c");
//        doublyList.addAtIndex(3,"d");
//        System.out.println(doublyList.getFirst());
//        System.out.println(doublyList.getLast());
//        System.out.println(doublyList.size);

        doublyList.addFirst("a");
        doublyList.addFirst("b");
        doublyList.addFirst("c");
        doublyList.addFirst("d");
        System.out.println(doublyList.size);
        System.out.println(doublyList.getFirst());
        System.out.println(doublyList.getLast());
        System.out.println(doublyList.deleteAtIndex(3));
    }

    private int size;
    private ListNode<E> first;
    private ListNode<E> last;

    public E getFirst() {
        return first.item;
    }

    public E getLast() {
        return last.item;
    }

    public void addFirst(E item) {
        addAtIndex(0, item);
    }

    public void addLast(E item) {
        addAtIndex(size, item);
    }

    public boolean deleteAtIndex(int index) {
        if (!checkElementIndex(index)) {
            return false;
        }
        if (index == 0) {
            ListNode<E> f = first;
            // fist 为空，则是空链表，直接返回
            if (first == null) {
                return true;
                // 链表不为空
            } else {
                first = null; // help GC
                // 将第二个节点赋值到 first，并前驱指针设置为null
                first = f.next;
                first.prev = null;
                size--;
                return true;
            }
        } else {
            ListNode<E> n = findAtIndex(index);
            ListNode<E> prev = n.prev;
            ListNode<E> next = n.next;
            n = null; // help GC
            // 不是尾节点，需要将 prev 后去节点指向 next 节点，next 节点的前驱指针指向 prev
            if (next != null) {
                next.prev = prev;
                prev.next = next;
                size--;
                return true;
                // 尾节点只设置后驱节点为null
            } else {
                last = null; // help GC
                last = prev;
                last.next = null;
                size--;
                return true;
            }
        }
    }

    public void addAtIndex(int index, E item) {
        if (!checkPositionIndex(index)) {
            return;
        }
        // 头部插入节点
        if (index == 0) {
            ListNode<E> f = first;
            ListNode<E> newNode = new ListNode<>(null, item, f);
            first = newNode;
            // 判断 first 节点是否为空，如果是，则是空列表
            if (null == f) {
                last = first;
            } else {
                // 将原 first 节点的前驱指针指向当前新的 first 节点
                f.prev = first;
            }
            // 中间插入节点
        } else if (index < size) {
            ListNode<E> succ = findAtIndex(index);
            if (succ == null) {
                return;
            }
            ListNode<E> pred = succ.prev;
            ListNode<E> newNode = new ListNode<>(pred, item, succ);
            succ.prev = newNode;
            pred.next = newNode;
            // 尾部插入节点
        } else if (index == size && size != 0) {
            ListNode<E> succ = last;
            ListNode<E> newNode = new ListNode<>(succ, item, null);
            last = newNode;
            succ.next = newNode;
        }
        size++;
    }

    private ListNode<E> findAtIndex(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }


//        for (int i = 0; i < size; i++) {
//            node = node.next;
//        }
//        return node;

        if (index < (size >> 1)) {
            ListNode<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            ListNode<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private boolean checkElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean checkPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private static class ListNode<E> {
        private E item;
        private ListNode<E> prev;
        private ListNode<E> next;

        public ListNode(ListNode<E> prev, E item, ListNode<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
