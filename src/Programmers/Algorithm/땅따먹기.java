package Programmers.Algorithm;

class 땅따먹기 {
    int solution(int[][] land) {

        return calculate(land);
    }

    public int calculate(int [][]land) {

        int size = land.length;

        for (int i = 1 ; i < size; i++) {

            land[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2],land[i-1][3]));
            land[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2],land[i-1][3]));
            land[i][2] += Math.max(land[i-1][1], Math.max(land[i-1][0],land[i-1][3]));
            land[i][3] += Math.max(land[i-1][2], Math.max(land[i-1][0],land[i-1][1]));
        }

        int a = Math.max(land[size-1][0],land[size-1][1]);
        int b = Math.max(land[size-1][2],land[size-1][3]);

        return Math.max(a,b);
    }


}
