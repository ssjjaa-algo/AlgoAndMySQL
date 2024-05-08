package Programmers.Algorithm;

class 정수삼각형 {
    public int solution(int[][] triangle) {

        int size = triangle.length;

        left(triangle, size);
        right(triangle, size);
        center(triangle, size);
        int answer = 0;

        for (int i = 0; i < size; i++) {
            answer = Math.max(answer, triangle[size - 1][i]);
        }

        return answer;
    }

    public void center(int[][] triangle, int size) {

        for (int i = 2; i < size; i++) {
            for (int j = 1; j < i; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }
    }

    public void left(int[][] triangle, int size) {

        for (int i = 1; i < size; i++) {
            triangle[i][0] += triangle[i - 1][0];
        }
    }

    public void right(int[][] triangle, int size) {

        for (int i = 1; i < size; i++) {
            triangle[i][i] += triangle[i - 1][i - 1];
        }
    }

}
