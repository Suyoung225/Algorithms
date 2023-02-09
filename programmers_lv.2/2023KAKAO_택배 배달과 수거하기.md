## 코딩테스트 연습 2023 KAKAO BLIND RECRUITMENT 택배 배달과 수거하기

https://school.programmers.co.kr/learn/courses/30/lessons/150369

```java
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ans = 0;
        int deliver = 0;
        int pickup = 0;
        for (int i = n-1; i >= 0; i--) {
            int cnt = 0;
            while (deliver - deliveries[i] < 0 || pickup - pickups[i] < 0) { // 남은 자리가 없는 경유
                deliver += cap;
                pickup += cap;
                cnt++;
            }
            deliver -= deliveries[i];
            pickup -= pickups[i];
            ans += 2L * (i + 1) * cnt;
            
        }
        return ans;
    }
}
```
