import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        // 진출 위치 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int cnt = 1; // 카메라 설치 대수
        int camIdx = 0; // 진출 위치에 카메라가 설치된 루트의 인덱스
      
        for(int i = 0; i < routes.length; i++){
            if(i < camIdx) continue;
            for(int j = i + 1; j < routes.length; j++){
                // 겹치지 않는 경우
                if(routes[j][0] > routes[camIdx][1]){
                    cnt++;
                    camIdx = j;
                }
            }
        }
    
        return cnt;
        
        }
}
