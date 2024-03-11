// https://school.programmers.co.kr/learn/courses/30/lessons/43236
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        rocks = Arrays.copyOf(rocks, rocks.length + 1);
        rocks[rocks.length - 1] = distance;
        Arrays.sort(rocks);
        
        int s = 1;
        int e = distance + 1;
        
        while(s < e - 1) {
            int d = (s + e)/2;
            if(isValid(d, rocks, n)) {
                s = d;
            } else {
                e = d;
            }
        }
        return s;
    }
    
    public boolean isValid(int d, int[] rocks, int n) {
        int removed = 0;
        int last = 0; // 마지막 바위 위치
        for (int rock : rocks) {
            if(rock - last < d) {
                removed++;
            } else {
                last = rock;
            }
        }
        return removed <= n;
    }
}
