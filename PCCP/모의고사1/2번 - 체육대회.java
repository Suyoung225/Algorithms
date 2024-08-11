import java.util.*;
class Solution {
    static int ans; 
    public int solution(int[][] ability) {
        ans = 0;
        Set<Integer> set = new HashSet<>();
        combi(0, 0, set, ability[0].length, ability);
        return ans;
    }
    
    void combi (int sum, int j, Set<Integer> players, int n, int[][] ability) { // sum: 현재까지 더한 능력치의 합, k: 종목 번호, n: 종목 개수
        if (j == n) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < ability.length; i++) {
            if (!players.contains(i)) {
                players.add(i);
                combi (sum + ability[i][j], j + 1, players, n, ability);
                players.remove(Integer.valueOf(i));
            }
        }
        
    }
}
