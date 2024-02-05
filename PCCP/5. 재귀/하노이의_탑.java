// https://school.programmers.co.kr/learn/courses/30/lessons/12946
import java.util.*;

class Solution {
    private static List<int[]> process;
    public int[][] solution(int n) {
        
        process = new ArrayList<>();
        hanoi(n, 1, 3);
        return process.toArray(new int[0][]);
    }
    
    private void hanoi(int n, int from, int to) {
        if(n == 1){
            process.add(new int[]{from, to});
            return;
        }
        int empty = 6 - from - to;
        hanoi(n - 1, from, empty);
        hanoi(1, from, to);
        hanoi(n - 1, empty, to);
    }
}
