package ink.bonismo.notebook.arithmetic;

import java.util.HashMap;

/**
 * @Author: Created by bonismo@hotmail.com on 2019/7/23 11:57 AM
 * @Description:
 * @Version: 1.0
 * @Link: https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195947&idx=1&sn=2954871ed1195dd3ebab0c9691e674b4&chksm=8c99fc71bbee7567c29169a86b4a2133bf87492ce5d2b7c9fdaf7740d2fc01084670b75e976a&scene=21#wechat_redirect
 */
public class LeastRecentlyUsed {

    private Node head;
    private Node end;

    private long limit;

    private HashMap<String, Node> hashMap;

    public LeastRecentlyUsed(long limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    /**
     * 获取某一节点值
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if (null == node) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    /**
     * 存储、更新节点
     */
    public synchronized void put(String key, String value) {
        Node node = hashMap.get(key);
        if (null == node) {
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    /**
     * 移除节点
     */
    public synchronized void remove(String key) {
        Node node = hashMap.get(key);
        refreshNode(node);
        hashMap.remove(key);

    }

    /**
     * 刷新节点
     */
    private void refreshNode(Node node) {
        if (end == node) {
            return;
        }
        removeNode(node);
        addNode(node);
    }

    /**
     * 移除节点
     *
     * @param node 节点
     * @return 节点的 key
     */
    private String removeNode(Node node) {
        if (end == node) {
            end = end.pre;
        } else if (head == node) {
            head = node.next;
        } else {
            // 移除节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 添加节点到双向链表尾部
     *
     * @param node 节点
     */
    private void addNode(Node node) {
        if (null != end) {
            end.next = node;
            end.pre = end;
            node.next = null;
        }
        end = node;
        if (null == head) {
            head = node;
        }
    }

    class Node {

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }

        private Node pre;
        private Node next;
        private String key;
        private String value;
    }

    public static void main(String[] args) {
        LeastRecentlyUsed lru = new LeastRecentlyUsed(5);
        lru.put("001", "用户1信息");
        lru.put("002", "用户2信息");
        lru.put("003", "用户3信息");
        lru.put("004", "用户4信息");

        String p2 = lru.get("002");
        System.out.println("P2 : " + p2);

        lru.put("005", "用户5信息");
        lru.put("006", "用户6信息");
        lru.put("007", "用户7信息");

        String p2Copy = lru.get("002");
        System.out.println("P2 : " + p2Copy);

        HashMap<String, Node> hashMap = lru.hashMap;
        for (String s : hashMap.keySet()) {
            System.out.println("Cache : " + s);
        }
    }
}
