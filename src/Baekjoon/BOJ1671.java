package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ1671 {

    static int N;
    static Shark[] sharks;
    static List<Integer> adj[];
    static int[] eat;

    static boolean[] visited;
    static class Shark {
        int size, speed, intelligence;

        public Shark(int size, int speed, int intelligence) {
            this.size = size;
            this.speed = speed;
            this.intelligence = intelligence;
        }

        public boolean canEat(Shark o) {

            return (this.size >= o.size && this.speed >= o.speed && this.intelligence >= o.intelligence);
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        int cnt = 0 ;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {

                Arrays.fill(visited,false);
                if (bipartite(j)) cnt++;
            }
        }

        System.out.println(N - cnt);
    }

    private static boolean bipartite(int num) {

        for (int next : adj[num]) {

            if (visited[next]) continue;
            visited[next] = true;

            if (eat[next] == -1 || bipartite(eat[next])) {
                eat[next] = num;
                return true;
            }
        }

        return false;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        sharks = new Shark[N];
        adj = new ArrayList[N];
        eat = new int[N];
        visited = new boolean[N];

        Arrays.fill(eat, -1);

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        String[] input;
        int size, speed, intelligence;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            size = Integer.parseInt(input[0]);
            speed = Integer.parseInt(input[1]);
            intelligence = Integer.parseInt(input[2]);

            sharks[i] = new Shark(size, speed, intelligence);
        }

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < i; j++) {

                if (sharks[i].canEat(sharks[j]) && sharks[j].canEat(sharks[i])) {
                    adj[i].add(j);
                }

                else if (sharks[i].canEat(sharks[j]) && !sharks[j].canEat(sharks[i])) {
                    adj[i].add(j);
                }

                else if (!sharks[i].canEat(sharks[j]) && sharks[j].canEat(sharks[i])) {
                    adj[j].add(i);
                }

            }
        }

    }
}
