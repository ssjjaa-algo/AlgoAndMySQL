package Programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class test {

    public static void main(String[] args) {


        TreeSet<String> treeSet = new TreeSet<>();

        treeSet.add("ab");
        treeSet.add("cd");
        treeSet.add("abc");

        List<String> sorted = new ArrayList<>(treeSet);

        int idx = sorted.indexOf("abc");

        System.out.println(idx);
    }
}
