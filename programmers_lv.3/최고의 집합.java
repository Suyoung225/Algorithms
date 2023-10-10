// https://school.programmers.co.kr/learn/courses/30/lessons/12938
import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        if(s < n) return new int[]{-1};
        
        int[] ans = new int[n];
        
        Arrays.fill(ans, s/n);
        
        int remainder = s % n;
        int idx = n-1;
        while(remainder --> 0){
            if(idx < 0) idx = n-1;
            ans[idx--]++;
        }
        
        return ans;
    }
}
