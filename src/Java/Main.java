package Java;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    static Map<String, Integer> giveMap = new HashMap<>();
    static Map<String, Integer> receiveMap = new HashMap<>();
    static Map<String, Integer> presentMap = new HashMap<>();
    static Map<String, Integer> individualGiveMap = new HashMap<>();
    static Map<String,Integer> nextMonthPresent = new HashMap<>();

    public static void main(String[] args) {

        String[] friends = {"muzi","ryan","frodo","neo"};

        String[] gifts = {"muzi frodo","muzi frodo","ryan muzi","ryan muzi","ryan muzi"
        ,"frodo muzi","frodo ryan","neo muzi"};

        for (int i = 0; i < friends.length; i++) {

            giveMap.put(friends[i],0);
            receiveMap.put(friends[i],0);
            presentMap.put(friends[i],0);
            nextMonthPresent.put(friends[i],0);
        }

        for (int i = 0; i < friends.length; i++) {

            for (int j = 0; j < friends.length; j++) {
                if (i == j) continue;
                individualGiveMap.put(friends[i] + " " + friends[j],0);
            }
        }

        for (int i = 0; i < gifts.length; i++) {

            String[] input = gifts[i].split(" ");

            giveMap.put(input[0],giveMap.get(input[0]) + 1);
            receiveMap.put(input[1],receiveMap.get(input[1]) + 1);
            individualGiveMap.put(input[0] + " " + input[1],
                    individualGiveMap.get(input[0] + " " + input[1]) + 1);
        }

        int len = friends.length;

        for (int i = 0; i < len; i++) {
            presentMap.put(friends[i],
                    giveMap.get(friends[i]) - receiveMap.get(friends[i]));

        }

        for (int i = 0; i < len; i++) {

            for (int j = i + 1; j < len; j++) {

                int first = individualGiveMap.get(friends[i] + " " + friends[j]);
                int second = individualGiveMap.get(friends[j] + " " + friends[i]);

                check(friends[i], friends[j], first, second);
            }
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = Math.max(ans,nextMonthPresent.get(friends[i]));
        }

        System.out.println(ans);

    }

    private static void check(String a, String b, int first, int second) {

        if (first != second) {

            if (first > second) {
                nextMonthPresent.put(a,nextMonthPresent.get(a) + 1);
                return;
            }

            else {
                nextMonthPresent.put(b,nextMonthPresent.get(b) + 1);
                return;
            }

        }

        if (first == second) {

            int presentA = presentMap.get(a);
            int presentB = presentMap.get(b);

            if (presentA == presentB) return;

            if (presentA > presentB) {
                nextMonthPresent.put(a,nextMonthPresent.get(a) + 1);
            }

            else {
                nextMonthPresent.put(b,nextMonthPresent.get(b) + 1);
            }

            return;
        }


    }
}
