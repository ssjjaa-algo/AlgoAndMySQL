import java.io.*;

/*
n일의 휴가동안 외주 개발
외주 작업은 수행 완료하는데 걸리는 기한 t, 완료 했을 때의 수익 p

1) 두 개 이상의 외주 작업은 동시에 수행할 수 없으며

2) 휴가 기간 이후로는 일을 할 수 없다.
-> 어떤 일을 시작한 날짜 day + 걸리는 기한 t > n + 1 then 일 할 수 없다.

2
2 5
1 4

void dfs(int curDay, int sum)

if (curDay >= n + 1) { // 휴가 기간을 넘는 경우
    ans = Math.max(ans,sum);
    return;
}


for (int i = curDay; i <= n; i++) {
    i) 해당 날짜에서 dfs를 수행해본다.
    dfs(i + day[i].date, sum + day[i].profit);
}

*/
public class 외주수익최대화하기dfs {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static class Info {
        int date;
        int profit;

        public Info(int date, int profit) {
            this.date=date;
            this.profit=profit;
        }
    }

    static Info[] day;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        init();
        dfs(1,0,0);
        System.out.println(ans);
    }

    private static void dfs(int curDay, int sum, int before) {

        if (curDay == n + 1) {
            ans = Math.max(ans,sum);
            return;
        }

        if (curDay > n + 1) {
            ans = Math.max(ans,sum - day[before].profit);
            return;
        }

        for (int i = curDay; i <= n; i++) {
            dfs(i + day[i].date, sum + day[i].profit, i);
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        String[] input;
        day = new Info[n + 1];
        int date, profit;
        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            date = Integer.parseInt(input[0]);
            profit = Integer.parseInt(input[1]);

            day[i] = new Info(date,profit);
        }
    }
}