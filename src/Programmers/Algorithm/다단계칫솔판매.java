import java.util.*;

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        Map<String, Integer> enrollMap = enrollUser(enroll);
        int[] parents = setParent(enrollMap, referral);
        int[] result = new int[enroll.length + 1];
        
        int length = seller.length;
        for (int i = 0; i < length; i++) {
            
            int findSeller = enrollMap.get(seller[i]);
            int profit = amount[i] * 100;
            divideProfit(parents, result, findSeller, profit);
            
        }
        
        return Arrays.copyOfRange(result, 1, result.length); 
    }
    
    public void divideProfit(int[] parents, int[] result, int seller, int profit) {
        
        if (profit / 10 == 0) {
            result[seller] += profit;
            return;
        }
        
        result[seller] += profit - (profit / 10);
        
        if(parents[seller] == 0) return;
        divideProfit(parents, result, parents[seller], profit / 10);
    }
    
    public int[] setParent(Map<String,Integer> enrollMap, String[] referral) {
        
        int length = referral.length;
        int[] parents = new int[length + 1];
        
        for (int i = 0; i < length; i++) {
            parents[i + 1] = enrollMap.get(referral[i]);
        }
        
        return parents;
    }
    
    public Map<String, Integer> enrollUser(String[] enroll) {
        
        int len = enroll.length;
        
        Map<String, Integer> ans = new HashMap<>();
        ans.put("-", 0);
        for (int i = 0; i < len; i++){
            ans.put(enroll[i], i + 1);
        }
        
        return ans;
    }
}

/*
1. map으로 enroll의 순서를 정해둔다.

2. referral에서 map에 있는 이름의 인덱스를 가져와서, 부모를 상정한다.

3. seller에서 map에 있는 이름을 가져와서, 10%를 계산한 금액이 1원 이상인 경우 부모에게 준다.
   --> 이익금 / 10이 1보다 큰 경우만 해당된다.

4. 최상위 부모 시에는 그냥 return

*/
