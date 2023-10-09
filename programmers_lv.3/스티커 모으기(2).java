import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        // 배열 개수가 1인 경우
        if(n == 1){
            return sticker[0];
        }
      
        // 첫 번째 스티커를 뽑는 경우, 마지막 카드는 못 뽑음
        int[] D = new int[n];
        D[0] = sticker[0];
        D[1] = Math.max(D[0], sticker[1]);
        for(int i = 2; i < n-1; i++){
            D[i] = Math.max(D[i-1], D[i-2] + sticker[i]);
        }
        
        // 첫 번째 스티커를 뽑는 것을 가정했을 때의 최대값
        int cardMax = D[n-2];
        
        // 첫 번째 스티커를 뽑지 않는 경우, 마지막 카드 뽑기 가능
        // D[0] = 0 
        D = new int[n];
        D[1] = sticker[1];
        for(int i = 2; i < n; i++){
             D[i] = Math.max(D[i-1], D[i-2] + sticker[i]);
        }

        return Math.max(cardMax, D[n-1]);
    }
}
