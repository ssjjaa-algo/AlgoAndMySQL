package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ21939 {

    static class Node implements Comparable<Node>{
        int problem;
        int level;

        public Node(int problem, int level) {
            this.problem=problem;
            this.level=level;
        }

        @Override
        public int compareTo(Node o) {
            if (this.level == o.level) return Integer.compare(o.problem,this.problem);

            return Integer.compare(o.level,this.level);
        }
    }
    static TreeSet<Node> binary = new TreeSet<Node>();
    static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] input;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }
    private static void calculate() throws IOException {
        int num = Integer.parseInt(br.readLine());

        String command;
        StringBuilder sb = new StringBuilder();
        int problem;
        int level;
        for (int i = 0; i < num; i++) {
            input = br.readLine().split(" ");

            command = input[0];

            if(command.equals("recommend")) {
                problem = Integer.parseInt(input[1]);

                if (problem == 1) {
                    sb.append(binary.first().problem).append("\n");
                }
                else if (problem == -1) {
                    sb.append(binary.last().problem).append("\n");
                }
            }

            else if (command.equals("solved")) {
                problem = Integer.parseInt(input[1]);
                binary.remove(new Node(problem,map.get(problem)));
                map.remove(problem);
            }

            else if (command.equals("add")) {
                problem = Integer.parseInt(input[1]);
                level = Integer.parseInt(input[2]);
                binary.add(new Node(problem,level));
                map.put(problem,level);
            }
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        int num = Integer.parseInt(br.readLine());

        int problem;
        int level;
        for (int i = 0; i < num; i++) {
            input = br.readLine().split(" ");
            problem = Integer.parseInt(input[0]);
            level = Integer.parseInt(input[1]);

            binary.add(new Node(problem,level));
            map.put(problem,level);
        }
    }
}