// https://school.programmers.co.kr/learn/courses/30/lessons/12979
import java.util.*;

class Solution {
    static int ans;
    public int solution(int n, int[] stations, int w) {
        Arrays.sort(stations);
        int idx = 1;
        for(int s : stations){
            if(s - w > idx){ // 겹치는 부분 건너뛰기 위해
                setGigi(s - w - idx, w);
            }
            idx = s + w + 1;
        }
        if(idx <= n){ // 마지막 남은 부분
            setGigi(n + 1 - idx, w);
        }
        return ans;
    }
    
    void setGigi(int coverage, int w){
        ans += coverage/ (w * 2 + 1);
        ans += (coverage % (w * 2 + 1) == 0)? 0 : 1;
    }
}
