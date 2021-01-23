package ink.bonismo.notebook.arithmetic.data_structure;

/**
 * 双向链表
 * 1. 操作链表需要注意边界条件
 * 1.1 操作头节点，需要设置 prev 为 null
 * 1.2 操作中间节点，需要设置当前节点的前驱节点的 next 指针和后驱节点的 prev 指针
 * 1.3 操作尾节点，需要设置 next 为 null
 * 1.4 注意 GC 性能，如果不设置为 null，当有另外一个遍历持有该元素时，恰巧在当前 GC 时，不会移除该元素，因为 GC Roots 还对该元素有可达性
 *
 * 2. 添加元素 addAtIndex(index,item)
 * 2.1 index = 0 操作头节点，新建节点（已经构造了当前节点的 next 指针，所以需要额外操作原节点的 prev 指针），
 *              首先要判断 first 是否为空，
 *                  如果为空，last 也是新添加节点。
 *                  不为空，设置原节点 prev 指向现在的 first 节点
 *                      ListNode<E> f = first;
 *                      ListNode<E> newNode = new ListNode<>(null,item,f);
 *                      first = newNode;
 *                      f.prev = first;
 * 2.2 index < size 操作中间节点，引查找索引节点，新建节点并将新节点的 next 指针设置为索引节点。操作索引节点的 prev 节点的 next 指针和索引节点的 prev 指针。
 *              ListNode<E> succ = findAtIndex(index);
 *              ListNode<E> pred = succ.prev;
 *              ListNode<E> newNode = ListNode<>(prev,item,succ);
 *              pred.next = newNode;
 *              succ.prev = newNode;
 * 2.3 index == size && size > 0 操作尾部节点，新建节点（已经构造了当前节点的 prev 指针，所以需要额外操作原节点的 next 指针），设置原节点的 next 指向现在的 last 节点
 *              ListNode<E> succ = last;
 *              ListNode<E> newNode = new List<>(succ,item,null);
 *              last = newNode;
 *              succ.next = newNode;
 * 3. 删除元素 deleteAtIndex(index)
 * 3.1 index = 0 删除头结点，
 *              判断 first 是否为空，
 *                      如果为空直接返回。
 *                      first 不为空，使用 next 节点作为 first 节点，并且将 prev 设置为 null
 *                      ListNode<E> succ = first;
 *                      first = null;
 *                      first = succ.next;
 *                      first.prev = null;
 * 3.2 index !=0 删除中间节点或尾节点，查找索引节点，
 *              判断索引节点的 next 是否为空，
 *                      不为空则为中间节点，将索引节点设置为 null，并将索引节点的前驱节点的 next 指针指向索引节点的后驱节点，将后驱节点的 prev 指针指向前驱节点；
 *                          ListNode<E> succ = findAtIndex(index);
 *                          ListNode<E> pred = succ.prev;
 *                          ListNode<E> next = succ.next;
 *                          succ = null;
 *                          prev.next = next;
 *                          next.prev = pred;
 *               为空，则为尾节点，将索引节点的前驱节点的 next 指针设置为 null
 *                          ListNode<E> succ = last;
 *                          ListNode<E> pred = succ.prev;
 *                          last = null;
 *                          last = pred;
 *                          pred.next = null;
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
        System.out.println(doublyList.deleteAtIndex(0));
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
            ListNode<E> succ = findAtIndex(index);
            ListNode<E> pred = succ.prev;
            ListNode<E> next = succ.next;
            succ = null; // help GC
            // 不是尾节点，需要将 prev 后去节点指向 next 节点，next 节点的前驱指针指向 prev
            if (next != null) {
                next.prev = pred;
                pred.next = next;
                size--;
                return true;
                // 尾节点只设置后驱节点为null
            } else {
                last = null; // help GC
                last = pred;
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
