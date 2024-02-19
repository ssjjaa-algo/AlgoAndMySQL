package Programmers.Algorithm;

class 도넛과막대그래프 {

    static final int MAX_SIZE = 1000001;
    static int[] inDegree = new int[MAX_SIZE];
    static int[] outDegree = new int[MAX_SIZE];
    static int vertex, donut, stick, eight;

    public int[] solution(int[][] edges) {

        init(edges);
        calculate();

        int[] answer = {vertex, outDegree[vertex] - (stick + eight), stick, eight};
        return answer;
    }

    public void calculate() {

        for (int i = 1; i < MAX_SIZE; i++) {

            if (outDegree[i] >= 2 && inDegree[i] >= 2) eight++;

            else if (outDegree[i] == 0 && inDegree[i] != 0) stick++;
        }

    }

    public void init(int[][] edges) {

        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            outDegree[edge[0]]++;
        }

        int max = 0;
        for (int i = 1; i < MAX_SIZE; i++) {

            if (outDegree[i] >= max && inDegree[i] == 0) {
                max = outDegree[i];
                vertex = i;
            }
        }

    }
}