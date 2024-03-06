https://school.programmers.co.kr/learn/courses/30/lessons/42895
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        for (int i = 1; i <= 8; i++) {
            // 연속된 숫자
            dp.get(i).add(Integer.parseInt((N+"").repeat(i)));
            
            // dp[j] 와 dp[i-j] 를 이용해 N이 i개 들어가는 숫자 만들기
            for (int j = 1; j < i; j++) {
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(i-j)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if(num2 != 0) {
                            dp.get(i).add(num1 / num2);
                        }
                    }
                }
            }
            
            if(dp.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
