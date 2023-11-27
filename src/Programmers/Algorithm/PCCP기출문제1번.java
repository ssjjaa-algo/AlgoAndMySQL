package Programmers.Algorithm;

class PCCP기출문제1번 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        return calculate(bandage,health, attacks);
    }
    
    public int calculate(int[] bandage, int health, int[][] attacks) {
        
        int currentHp = health;
        int prevTime = 0;
        int nextTime, nextAttack, additionalRecovery, secondRecovery;
        
        for (int[] attack : attacks) {
            
            nextTime = attack[0];
            nextAttack = attack[1];
            
            additionalRecovery = (nextTime - prevTime - 1) / bandage[0];
            secondRecovery = (nextTime - prevTime - 1) * bandage[1];

            currentHp += secondRecovery + (bandage[2] * additionalRecovery);
            
            if (currentHp > health) currentHp = health;
            
            currentHp -= nextAttack;
            
            prevTime = nextTime;
            if (currentHp <= 0) return -1;
        }
        
        return currentHp;
        
    }
}