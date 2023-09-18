package CodeTree;


import java.io.*;

public class 바이러스검사 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] foodStore;
    static int leader,teammate;

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(calculate());
    }

    private static long calculate() {

        long people = 0;
        for (int i = 0; i < n; i++) {
            foodStore[i] -= leader;
            people++;
            if (foodStore[i] <= 0) continue;

            if (foodStore[i] % teammate == 0) {
                people += foodStore[i] / teammate;
            }

            else  {
                people += foodStore[i] / teammate + 1;
            }

        }

        return people;
    }

    private static void init() throws IOException {

        n = Integer.parseInt(br.readLine());
        foodStore = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++){
            foodStore[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");
        leader = Integer.parseInt(input[0]);
        teammate = Integer.parseInt(input[1]);


    }
}
