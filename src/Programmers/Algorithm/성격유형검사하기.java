package Programmers.Algorithm;

class 성격유형검사하기 {

    static char[][] map = {{'R','T'},{'C','F'},{'J','M'},{'A','N'}};
    static int[] scores = new int[32];

    public String solution(String[] survey, int[] choices) {

        calculate(survey,choices);

        return ans();

    }

    public void calculate(String[] survey, int[] choices) {

        int len = choices.length;

        for (int i = 0; i < len; i++) {

            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);

            if (choices[i] <= 3)
                scores[a - 'A'] += 4 - choices[i];

            else if (choices[i] >= 5)
                scores[b - 'A'] += choices[i] - 4;
        }
    }

    public String ans() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            sb.append(check(i));
        }

        return sb.toString();
    }

    public char check(int num) {

        if (scores[map[num][0] - 'A'] >= scores[map[num][1] - 'A'])
            return map[num][0];

        return map[num][1];

    }
}