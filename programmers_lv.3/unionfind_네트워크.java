// https://school.programmers.co.kr/learn/courses/30/lessons/43162
import java.util.*;
class Solution {
    static int[] parent;
    public int solution(int n, int[][] computers) {
        // parent 배열 초기화
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(computers[i][j] == 1) union(i, j);
            }
        }
        
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(set.add(find(i))) ans++;
        }
        
        return ans;
    }
    
    void union(int a, int b){
        parent[find(b)] = find(a);
    }
    
    int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
}
