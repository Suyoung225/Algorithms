import java.util.*;
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 마지막 공격 시각
        int lastAttack = 0;
        // 최대 체력
        int maxHealth = health;
        
        for (int i = 0; i < attacks.length; i++) {
            // 연속 회복 시간
            int recoveryTime = attacks[i][0] - lastAttack - 1;
            // 회복 시간 * 초당 회복량
            health += recoveryTime * bandage[1];
            // t초 연속 회복 시 추가 체력 회복 
            health += bandage[2] * (recoveryTime / bandage[0]);
            // 최대 체력 이상의 체력을 가질 수 없음
            health = (health > maxHealth) ? maxHealth : health;
            // 공격
            health -= attacks[i][1];
            lastAttack = attacks[i][0]; // 마지막 공격 시각 변경
            
            // 체략 0 이하로 떨어지면 사망
            if (health <= 0) {
                return -1;
            }
            
        }
  
        return health;
    }
}
