package com.studygit.first;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: LRUCache.java
 * Author:   machao
 * Date:     2019/11/19 08:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 */

public class LRUCache<k, v> {
    //容量
    private int capacity;
    //当前有多少节点的统计
    private int count;
    //缓存节点
    private Map<k, Node<k, v>> nodeMap;//节点容器
    private Node<k, v> head;//头节点
    private Node<k, v> tail;//尾节点

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.valueOf(capacity));
        }
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        //初始化头节点和尾节点，利用哨兵模式减少判断头结点和尾节点为空的代码
        Node<k, v> headNode = new Node<>(null, null);
        Node<k, v> tailNode = new Node<>(null, null);
        headNode.next = tailNode;
        tailNode.pre = headNode;
        this.head = headNode;
        this.tail = tailNode;
    }

    public void put(k key, v value) {
        Node<k, v> node = nodeMap.get(key);
        if (node == null) {
            if (count >= capacity) {
                //先移除一个节点
                removeNode();
            }
            node = new Node<>(key, value);
            //添加节点
            addNode(node);
        } else {
            //移动节点到头节点
            moveNodeToHead(node);
        }
    }

    public Node<k, v> get(k key) {
        Node<k, v> node = nodeMap.get(key);
        if (node != null) {
            moveNodeToHead(node);
        }
        return node;
    }

    private void removeNode() {
        Node<k, v> node = tail.pre;
        //从链表里面移除
        removeFromList(node);
        nodeMap.remove(node.key);
        count--;
    }

    private void removeFromList(Node<k, v> node) {
        Node<k, v> pre = node.pre;
        Node<k, v> next = node.next;

        pre.next = next;
        next.pre = pre;

        node.next = null;
        node.pre = null;
    }

    private void addNode(Node<k, v> node) {
        //添加节点到头部
        addToHead(node);
        nodeMap.put(node.key, node);
        count++;
    }

    private void addToHead(Node<k, v> node) {
        Node<k, v> next = head.next;
        next.pre = node;
        node.next = next;
        node.pre = head;
        head.next = node;
    }

    public void moveNodeToHead(Node<k, v> node) {
        //从链表里面移除
        removeFromList(node);
        //添加节点到头部
        addToHead(node);
    }

}

class Node<k, v> {
	
    k key;
    v value;
    Node<k, v> pre;
    Node<k, v> next;

    public Node(k key, v value) {
        this.key = key;
        this.value = value;
    }
}
