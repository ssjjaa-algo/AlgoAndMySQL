
class 양궁대회 {
    
    static int[] result = new int[11];
    static int[] choice = new int[11];
    static int max = 0;
    
    public int[] solution(int n, int[] info) {
        
        dfs(0, n, info);
        
        if (max == 0) return new int[]{-1};
        
        return result;
    }
    
    public void dfs(int idx, int n, int[] info) {
        
        if (n == 0 || idx == 11) {
            checkSum(n, info);
            choice[10] = 0;
            return;
        }
        
        
        if (n > info[idx]) {
            choice[idx] = info[idx] + 1;
            dfs(idx + 1, n - (info[idx] + 1), info);
            choice[idx] = 0;
        }
        
        dfs(idx + 1, n, info);
    }
    
    public void checkSum(int n, int[] info) {
        
        int apeach = 0;
        int ryan = 0;
        for (int i = 0; i < 10; i++) {
            if (info[i] == 0 && choice[i] == 0) continue;
            
            if (info[i] >= choice[i]) apeach += (10 - i);
            
            else ryan += (10 - i);
        }
        
        if (apeach >= ryan) return;
        
        int sum = ryan - apeach;
        
        if (n > 0) choice[10] = n;
        
        if (sum > max) {
            max = sum;
            
            for (int i = 0; i < 11; i++) {
                result[i] = choice[i];
            }
            
        }
        
        else if (sum == max) {
            for (int i = 10; i >= 0; i--) {
                if (choice[i] > result[i]) {
                    
                    for (int j = 0; j < 11; j++) {
                        result[j] = choice[j];
                    }
                    return;
                }
                else if (choice[i] == result[i]) continue;
                
                else return;
            }
        }
        
    }
}
