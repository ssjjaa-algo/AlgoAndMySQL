package Swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] map = new int[5][5];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map[i][j] = cnt++;
            }
        }

        rotate(1, 1, 3);

    }

    private static void rotate(int r, int c, int size) {

        int[][] temp = copy();
        int tempCol = c;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i + r][j + c] = map[r + size - 1 - j][tempCol];
                System.out.println((i + r) + " " +(j + c) + "-> " + (r + size - 1- j) + " " + tempCol);
            }
            tempCol++;
        }

        print(temp);
    }
    private static int[][] copy() {

        int[][] temp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp[i][j] = map[i][j];
            }
        }

        return temp;
    }

    private static void print(int[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
