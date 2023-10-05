// https://school.programmers.co.kr/learn/courses/30/lessons/12987
import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int n = A.length;
        int idxA = 0;
        int winCnt = 0;
        for(int i = 0; i < n; i++){
            if(B[i] > A[idxA]){
                winCnt++;
                idxA++;
            }
        }
        return winCnt;
    }
}
