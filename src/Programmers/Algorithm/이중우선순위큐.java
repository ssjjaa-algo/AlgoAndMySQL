package Programmers.Algorithm;

import java.util.*;

class 이중우선순위큐 {
    public int[] solution(String[] operations) {

        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {

            String[] o = operation.split(" ");

            if (o[0].equals("I")) {
                int num = Integer.parseInt(o[1]);
                maxPq.add(num);
                minPq.add(num);
            }
            else {

                if (maxPq.isEmpty()) continue;

                int num = 0;
                if (o[1].equals("1")) {

                    num = maxPq.poll();
                    minPq.remove(num);
                }

                else {
                    num = minPq.poll();
                    maxPq.remove(num);
                }
            }
        }

        if (maxPq.isEmpty()) return new int[]{0, 0};
        return new int[]{maxPq.peek(), minPq.peek()};
    }
}
