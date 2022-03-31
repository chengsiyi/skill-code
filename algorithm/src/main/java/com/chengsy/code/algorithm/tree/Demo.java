package com.chengsy.code.algorithm.tree;

import java.util.ArrayList;

/**
 * @author chengsiyi
 * @date 2021/6/29 13:40
 */
public class Demo {

    public String serialize(Node root) {
        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Node> curLayer = new ArrayList<>();
        if (root != null) {
            curLayer.add(root);
        }
        while (!curLayer.isEmpty()) {
            ArrayList<Node> nextLayer = new ArrayList<>();
            for (Node node : curLayer) {
                result.add(node);
                if (node != null) {
                    nextLayer.add(node.left);
                    nextLayer.add(node.right);
                }
            }
            curLayer = nextLayer;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : result) {
            stringBuilder.append(node == null ? null : node.val).append(",");
        }
        return stringBuilder.toString();
    }

    public Node unserialize(String stringTree) {
        if (stringTree.equals("")) {
            return null;
        }
        String[] nodes = stringTree.split(",");
        Node root = new Node(Integer.parseInt(nodes[0]));
        ArrayList<Node> curLayer = new ArrayList<>();
        curLayer.add(root);
        int index = 1;
        while (!curLayer.isEmpty()) {
            ArrayList<Node> nextLayer = new ArrayList<>();
            for (Node node : curLayer) {
                Node leftNode = parseNode(nodes, index);
                if (leftNode != null) {
                    node.left = leftNode;
                    nextLayer.add(leftNode);
                }
                index++;
                Node rightNode = parseNode(nodes, index);
                if (rightNode != null) {
                    node.right = rightNode;
                    nextLayer.add(rightNode);
                }
                index++;
            }
            curLayer = nextLayer;
        }
        return root;
    }

    private Node parseNode(String[] nodes, int index) {
        return nodes[index].equals("null") ? null : new Node(Integer.parseInt(nodes[index]));
    }

    public static void main(String[] args) {
        Node tree1 = new Node(1);
        tree1.left = new Node(2);
        tree1.right = new Node(3);
        tree1.left.right = new Node(4);
        Demo app = new Demo();

        String StringTree = app.serialize(tree1);
        System.out.println(StringTree);
        Node tree2 = app.unserialize(StringTree);
        System.out.println(equals(tree1, tree2));
    }

    public static boolean equals(Node tree1, Node tree2) {
        if (tree1 == tree2) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.val != tree2.val) {
            return false;
        }
        return equals(tree1.left, tree2.left) && equals(tree1.right, tree2.right);

    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }
}
