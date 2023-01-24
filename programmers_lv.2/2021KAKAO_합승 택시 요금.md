## 코딩테스트 연습 2021 KAKAO BLIND RECRUITMENT 합승 택시 요금

https://school.programmers.co.kr/learn/courses/30/lessons/72413?language=java

플로이드-워셜
https://dev-daybyday.tistory.com/85

```java
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] cost = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j) cost[i][j] = 0;
                else cost[i][j] = 10000000;
            }
        }
        for (int[] fare : fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }
        for (int k = 1; k <= n; k++) { // 경유 노드 k
            for (int i = 1; i <= n; i++) { // 출발 노드 i
                for (int j = 1; j <= n; j++) { // 도착 노드 j
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }
        int minDist = cost[s][a] + cost[s][b]; // 합승 안 하는 경우
        for (int i = 1; i <= n; i++) {
            if(i != s){
                minDist = Math.min(minDist, cost[s][i] + cost[i][a] + cost[i][b]);
            }
        }
        return minDist;
    }
}
```
