package pl.rozen;

import pl.rozen.list.LinkedList;
import pl.rozen.map.HashMap;
import pl.rozen.tree.BSTree;
import pl.rozen.tree.Tries;
import sun.text.normalizer.Trie;

public class Main {

    public static void main(String[] args) {

        //// Showcase /////
        BSTree<Integer, String> bst = new BSTree<>();
        bst.insert(6, "AAA");
        bst.insert(2, "BBB");
        bst.insert(3, "CCC");
        bst.insert(1, "DDD");
        Integer data = 8;
        bst.insert(data, "EEE");
        bst.insert(7, "FFF");
        bst.insert(9, "FFF");
        bst.insert(6, "XXX");
        bst.inOrderTraversal();
        System.out.println();
        System.out.println(bst);
        bst.delete(6);
        System.out.println(bst);

        System.out.println(bst.get(data));
        System.out.println(bst.get(12));
        System.out.println(bst.get(null));
        bst.clear();
        bst.inOrderTraversal();


        HashMap<Integer, String > hm = new HashMap<>();
        hm.put(1,"1");
        hm.put(2,"2");
        hm.put(3,"3");
        hm.put(4,"4");
        hm.put(5,"5");
        hm.put(6,"6");
        System.out.println(hm.get(2));
        hm.remove(6);
        System.out.println(hm.get(2));
        hm.remove(2);
        hm.remove(1);
        System.out.println(hm.get(2));
        for (int i = 0; i < 20; i++) {
            hm.put(i,Integer.toString(i));
        }
        hm.clear();
        for (int i = 0; i < 20; i++) {
            hm.put(i,Integer.toString(i));
        }


        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.get(3);
        list.get(-1);
        list.get(1020);

        Tries t = new Tries();
        t.add("s");
        t.add("ss");
        t.add("sss");
        t.add("ssss");
        t.add("sssss");
        System.out.println(t.noOfMatches("s"));
        System.out.println(t.noOfMatches("ss"));
        System.out.println(t.noOfMatches("sss"));
        System.out.println(t.noOfMatches("ssss"));
        System.out.println(t.noOfMatches("sssss"));
        System.out.println(t.noOfMatches("ssssss"));


    }
}
