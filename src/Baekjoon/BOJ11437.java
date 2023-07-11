package Baekjoon;

import java.io.*;
import java.util.*;

/*
1. 두 정점의 depth를 같게 한다.
2. 두 정점이 각각 부모를 올라가보며 같은 조상을 만날 때 까지 반복한다.

각 정점은 호출한 부모를 알 수 있도록 한다.

1번 정점이 루트
1번 정점은 부모 0, 깊이 0 시작

2번 정점은 부모 1, 깊이 1
3번 정점은 부모 1, 깊이 1

4번 정점은 부모 2, 깊이 2

nodeParents[][2].


 */
public class BOJ11437 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static String[] input;
    static List<Integer> adj[];
    static int infoOfNode[][];
    static boolean[] visited;
    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());
        init(N);
        fillInfoOfNode(1);

        int M =Integer.parseInt(br.readLine());
        calculate(M);


        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    private static void calculate(int M) throws IOException{

        int a,b;
        int max, min;
        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");

            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);

            sb.append(findCommonParent(a,b)).append("\n");
        }
    }
    private static int findCommonParent(int a, int b) {

        if (infoOfNode[a][1] > infoOfNode[b][1]) {
            a = matchDepth(a,infoOfNode[b][1]);
        }
        else if (infoOfNode[b][1] > infoOfNode[a][1]){
            b = matchDepth(b,infoOfNode[a][1]);
        }


        if (a == b) return a; // 최초에 입력이 둘이 똑같이 들어오 경우 제외하고

        while(infoOfNode[a][0] != infoOfNode[b][0]) {
            a = infoOfNode[a][0];
            b = infoOfNode[b][0];
        }


        //System.out.println(num + " " + comp + " " + infoOfNode[num][0]);
        return infoOfNode[a][0];
    }

    private static int matchDepth(int num, int depth) {

        while (infoOfNode[num][1] != depth) {
            num = infoOfNode[num][0];
        }
        return num;
    }

    private static void fillInfoOfNode(int num) {

        infoOfNode[1][0] = 0;
        infoOfNode[1][1] = 0;
        visited[1] = true;

        int child;
        for (int i = 0; i < adj[num].size(); i++) {
            child = adj[num].get(i);

            if (!visited[child]) {
                visited[child] = true;
                infoOfNode[child][0] = num;
                infoOfNode[child][1] = infoOfNode[num][1] + 1;
                fillInfoOfNode(child);
            }
        }
    }

    private static void init(int N) throws IOException {
        adj = new ArrayList[N + 1];
        infoOfNode = new int[N + 1][2]; // 0번은 부모, 1번은 depth
        visited = new boolean[N + 1];

        for (int i=1; i<=N; i++)
            adj[i] = new ArrayList<>();

        int a,b;
        for (int i=1; i<N; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
             adj[a].add(b);
             adj[b].add(a);
        }
    }
}
