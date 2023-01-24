## 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 기둥과 보 설치


```java
class Solution {
    static boolean[][][] map;
    public int[][] solution(int n, int[][] build_frame) {
        map = new boolean[n+1][n+1][2]; // x, y, 기둥:0/보:1
        int num = 0;
        for (int[] build : build_frame) {
            if(build[3] == 1){
                map[build[0]][build[1]][build[2]] = true;
                num++;
            }
            else{
                map[build[0]][build[1]][build[2]] = false;
                num--;
            }

            if(!isValid(n)){ // 유효하지 않으면 롤백
                if(build[3] == 0){
                    map[build[0]][build[1]][build[2]] = true;
                    num++;
                }
                else{
                    map[build[0]][build[1]][build[2]] = false;
                    num--;
                }
            }

        }

        int[][] ans = new int[num][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if(map[i][j][0]){
                    ans[idx] = new int[]{i, j, 0};
                    idx++;
                }
                if(map[i][j][1]){
                    ans[idx] = new int[]{i, j, 1};
                    idx++;
                }
            }
        }
        return ans;
    }
    boolean isValid(int n){
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if(map[i][j][0]){
                    if(j != 0 && !map[i][j-1][0] && !map[i][j][1] && (i == 0 || !map[i-1][j][1])) // 바닥이 아니거나, 밑에 기둥이 없거나 보가 밑에 없으면
                        return false;
                }
                if(map[i][j][1]){
                    if((j == 0 || !map[i][j-1][0]) && // 왼쪽 밑에 기둥 없거나
                            (j == 0 || i == n|| !map[i+1][j-1][0]) && // 오른쪽 밑에 기둥이 없거나
                            ((i == n || i == 0 || !map[i+1][j][1] || !map[i-1][j][1]))) { // 양쪽에 보가 없거나
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
```
