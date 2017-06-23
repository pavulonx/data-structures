package pl.rozen.tree;

import java.util.HashMap;

/**
 * Created by rozen on 23.06.17.
 */
public class Tries {
    private Node root;

    public Tries() {
        root = new Node();
    }

    public void add(String s) {
        if (s != null)
            root.addWord(s.toCharArray(), 0);
    }

    private Node findNode(char[] str) {
        Node tmp = root;
        for (int i = 0; i < str.length && tmp != null; i++) {
            tmp = tmp.getChildNode(str[i]);
        }
        return tmp;
    }

    public int noOfMatches(String str) {
        Node node = findNode(str.toCharArray());
        if (node != null)
            return node.size + (node.isCompleteWord() ? 1 : 0);
        return 0;
    }

    private static class Node {
        char letter;
        HashMap<Character, Node> children;
        boolean completeWord;
        int size = 0;

        public boolean isCompleteWord() {
            return completeWord;
        }

        public void setCompleteWord(boolean completeWord) {
            this.completeWord = completeWord;
        }

        Node() {
            completeWord = false;
            children = new HashMap<>();
        }

        int noOfChildren() {
            return children.size();
        }

        Node getChildNode(char c) {
            return children.getOrDefault(c, null);
        }

        void addWord(char[] s, int charIndex) {
            if (charIndex < s.length) {
                char letter = s[charIndex];
                children.putIfAbsent(letter, new Node());
                size++;
                Node n = children.get(letter);
                n.letter = letter;
                if (charIndex + 1 == s.length)
                    n.completeWord = true;
                else n.addWord(s, charIndex + 1);
            }
        }
    }
}
