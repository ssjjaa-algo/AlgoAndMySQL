package Programmers.Algorithm;

import java.util.*;

class n플러스1카드게임 {

    static Set<Integer> isNotSelected = new HashSet<>();
    static int length;
    static Set<Integer> isSelected = new HashSet<>();
    static int answer = 0;

    public int solution(int coin, int[] cards) {

        init(cards);
        return calculate(coin, cards, length / 3);
    }

    public int calculate(int coin, int[] cards, int idx) {

        int answer = 0;

        while (idx <= length) {

            answer++;
            if (idx == length) break;
            int num1 = cards[idx++];
            int num2 = cards[idx++];
            isNotSelected.add(num1);
            isNotSelected.add(num2);

            boolean flag = false;
            for (int number : isSelected) {
                int next = length + 1 - number;
                if (isSelected.contains(next)) {
                    isSelected.remove(number);
                    isSelected.remove(next);
                    flag = true;
                    break;
                }
            }

            if (!flag && coin >= 1) {
                for (int number : isSelected) {
                    int next = length + 1 - number;
                    if (isNotSelected.contains(next)) {
                        isSelected.remove(number);
                        isNotSelected.remove(next);
                        coin--;
                        flag = true;
                        break;
                    }
                }
            }

            if (!flag && coin >= 2) {
                for (int number : isNotSelected) {
                    int next = length + 1 - number;
                    if (isNotSelected.contains(next)) {
                        isNotSelected.remove(number);
                        isNotSelected.remove(next);
                        coin -= 2;
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) break;
        }

        return answer;

    }

    public void init(int[] cards) {

        length = cards.length;

        for (int i = 0; i < length / 3; i++) {
            isSelected.add(cards[i]);
        }

    }
}
