package Programmers.Algorithm;

class 행렬의곱셈 {

    static int[][] answer;

    public int[][] solution(int[][] arr1, int[][] arr2) {

        int answerRow = arr1.length;
        int answerCol = arr2[0].length;

        int length = arr1[0].length;

        answer = new int[answerRow][answerCol];

        for (int i = 0; i < answerRow; i++) {

            for (int j = 0; j < answerCol; j++) {

                answer[i][j] = calculate(i,j,length,arr1,arr2);
            }
        }

        return answer;
    }

    public int calculate(int x, int y, int length,int[][] arr1, int[][] arr2) {

        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += arr1[x][i] * arr2[i][y];
        }
        return sum;
    }
}
