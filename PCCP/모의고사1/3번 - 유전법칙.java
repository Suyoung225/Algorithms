import java.util.*;

class Solution {
    public String[] solution(int[][] queries) {
        String[] ans = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = find(queries[i][0], queries[i][1] - 1);
        }
        return ans;
    }
    
    static String find(int n, int p) {
        if (n == 1) return "Rr";
        
        int slice = (int) Math.pow(4, n - 2);
        int group = p / slice;
        
        switch (group) {
            case 0 : return "RR"; 
            case 3: return "rr"; 
            default: return find(n - 1, p % slice);
        }
    }
}
