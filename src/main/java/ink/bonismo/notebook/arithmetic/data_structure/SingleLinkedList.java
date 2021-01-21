package ink.bonismo.notebook.arithmetic.data_structure;

/**
 * 1. 定义一个单项链表，包含长度,节点
 * 2. 定义一个节点，包含元素、下一个节点
 * 3. 实现 add、addFirst、addLast、delete、remove、sort、reverse 方法
 * 单向节点特点：
 * 1. 添加节点，需要根据初始节点和非初始节点两种情况操作
 * 2. 删除节点，操作 prev node，设置 prev.next = null，并且将 prev next 指针修改为删除节点的下一个指针
 * 3. 查找节点，根据初始节点一次遍历
 * @param <E>
 */
public class SingleLinkedList<E> {

    private int size;
    private Node<E> first;

    public SingleLinkedList() {
    }

    public void add(E val) {
        addTail(val);
    }

    /**
     * 时间复杂度优化为：O(n)
     * 避免循环每次 i 都调用 findByIndex 循环整个 index 范围，O(n2)
     */
    public boolean delete(E item) {
        Node<E> prev = first;
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                if (item == first.item) {
                    first = null; // help GC
                    first = node.next;
                    size--;
                    return true;
                }
            } else {
                if (node == null) {
                    return false;
                }
                if (item == node.item) {
                    Node<E> temp = prev.next;
                    prev.next = null; // help GC
                    prev.next = temp.next;
                    size--;
                    return true;
                }
            }
            // 记录当前节点
            prev = node;
            // 指针下移
            node = node.next;
        }
        return false;
    }

    /**
     * 时间复杂度：O(n2)
     */
    public boolean remove(E item) {
        for (int i = 0; i < size; i++) {
            Node<E> result = findByIndex(i);
            if (result != null) {
                if (item == result.item) {
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
            Node<E> next = first.next;
            first = null; // help GC
            first = next;
        } else {
            Node<E> prev = findByIndex(index - 1);
            if (prev == null || prev.next == null) {
                return;
            }
            Node<E> next = prev.next;
            prev.next = null; // help GC
            prev.next = next.next;
        }
        size--;
    }

    private void addAtIndex(int index, E val) {
        if(!rangeCheck(index)){
            return;
        }
        // 当 index 为负数或零时，添加到头部
        if (index <= 0) {
            first = new Node<>(val, first);
        } else {
            // 当索引大于零时，查到当前索引的上一个节点
            Node<E> prev = findByIndex(index - 1);
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
                result.append(node.item).append(",");
            } else {
                result.append(node.item);
            }
        }
        return result.toString();
    }

    // 合法索引校验
    private boolean rangeCheck(int index) {
        return !(index < 0 || index > size );
    }


    // 循环遍历 index 所在节点
    private Node<E> findByIndex(int index) {
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
        private E item;
        private Node<E> next;

        /**
         * 初始化头节点
         */
        public Node(E item) {
            this.item = item;
        }

        /**
         * 初始化非头部节点
         */
        public Node(E item, Node<E> next) {
            this(item);
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.addTail("a");
        list.addTail("b");
        list.addTail("c");
        list.addTail("d");
        list.addTail("e");
        list.addTail("f");

        System.out.println(list.toString());
        System.out.println("--- Method remove ---");
        System.out.println(list.remove("b"));
        System.out.println(list.size);
        System.out.println(list.toString());
        System.out.println("--- Method delete ---");
        System.out.println(list.delete("f"));
        System.out.println(list.size);
        System.out.println(list.toString());
        list.deleteAtIndex(list.size);
        System.out.println(list.toString());
    }
}
