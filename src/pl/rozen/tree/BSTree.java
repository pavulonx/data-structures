package pl.rozen.tree;

import java.util.StringJoiner;

public class BSTree<K extends Comparable<K>, V> {
    private static class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {
        K key;
        V value;
        Node<K, V> left = null, right = null;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void insert(Node<K, V> toInsert) {
            int thisCompareToThat = this.compareTo(toInsert);
            if (thisCompareToThat == 0) {
                this.value = toInsert.value;
            } else if (thisCompareToThat < 0) {
                if (right == null)
                    this.right = toInsert;
                else
                    this.right.insert(toInsert);
            } else if (thisCompareToThat > 0) {
                if (left == null)
                    this.left = toInsert;
                else
                    this.left.insert(toInsert);
            }
        }

        @Override
        public int compareTo(Node<K, V> kvNode) {
            return this.key.compareTo(kvNode.key);
        }

        V get(K key) {
            int thisKeyCompareToThatKey = this.key.compareTo(key);
            if (thisKeyCompareToThatKey == 0)
                return value;
            else {
                if (thisKeyCompareToThatKey > 0 && left != null)
                    return left.get(key);
                else if (right != null)
                    return right.get(key);
                return null;
            }
        }
    }

    private Node<K, V> root;

    public void insert(K data, V value) {
        if (data == null)
            return;
        Node<K, V> toInsert = new Node<>(data, value);
        if (root == null)
            root = toInsert;
        else {
            root.insert(toInsert);
        }
    }

    public V get(K key) {
        if (key == null || root == null)
            return null;
        else return root.get(key);
    }

    public void inOrderTraversal() {
        inOrderTraversalPrint(root);
    }

    public void clear() {
        root = null;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        inOrderTraversal(root, joiner);
        return joiner.toString();

    }

    private void inOrderTraversal(Node<K, V> node, StringJoiner joiner) {
        if (node == null)
            return;
        if (node.left != null)
            inOrderTraversal(node.left, joiner);

        String s = ("<" + node.key + ", " + node.value + ">");
        joiner.add(s);

        if (node.right != null)
            inOrderTraversal(node.right, joiner);
    }

    private void inOrderTraversalPrint(Node<K, V> node) {
        if (node == null)
            return;
        if (node.left != null)
            inOrderTraversalPrint(node.left);

        System.out.println("<" + node.key + ", " + node.value + ">");

        if (node.right != null)
            inOrderTraversalPrint(node.right);
    }

    public void delete(K key) {
        if (key != null)
            root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> root, K key) {
        if (root == null)
            return null;
        int comparison = root.key.compareTo(key);
        if (comparison > 0)
            root.left = delete(root.left, key);
        else if (comparison < 0)
            root.right = delete(root.right, key);
        else {
            if (root.left != null && root.right != null) {
                Node<K, V> left = root.left;
                Node<K, V> right = root.right;

                root = removeMin(root.right, root);

                Node<K, V> minRight = root.right;
                root.left = left;
                root.right = right;

                if (root.right.key == right.key)
                    root.right = minRight;

            } else if (root.left == null && root.right == null)
                root = null;
            else if (root.left != null)
                root = root.left;
            else
                root = root.right;
        }
        return root;
    }


    private Node<K, V> removeMin(Node<K, V> root, Node<K, V> parent) {
        if (root == null)
            return null;
        if (root.left == null) {
            if (parent != root)
                parent.left = root.right;
            return root;
        }
        return removeMin(root.left, root);
    }

}
