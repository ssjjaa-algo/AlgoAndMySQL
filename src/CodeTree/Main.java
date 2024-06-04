package CodeTree;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Node {

        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) {

        List<Node> a = new ArrayList<>();
        a.add(new Node(1,2));

        for (Node n : a) {
            n.r = 3;
            n.c = 4;
        }

        for (Node n : a) {
            System.out.println(n.r + " " + n.c);
        }
    }
}
