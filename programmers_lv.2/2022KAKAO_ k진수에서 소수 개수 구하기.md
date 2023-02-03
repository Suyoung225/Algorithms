## 코딩테스트 연습 2022 KAKAO BLIND RECRUITMENT k진수에서 소수 개수 구하기


```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int k) {
        Queue<Character> queue = new LinkedList<>();
        int cnt = 0;
        char[] chars = Integer.toString(n, k).toCharArray();
        for (char c : chars) {
            if(c != '0'){
                queue.add(c);
            }else if(!queue.isEmpty()){
                StringBuilder sb = new StringBuilder();
                while(!queue.isEmpty()){
                    sb.append(queue.poll());
                }
                long num = Long.parseLong(sb.toString());
                if(isPrime(num)) cnt++;
            }
        }
        if(!queue.isEmpty()){
            StringBuilder sb = new StringBuilder();
            while(!queue.isEmpty()){
                sb.append(queue.poll());
            }
            long num = Long.parseLong(sb.toString());
            if(isPrime(num)) cnt++;
        }

        return cnt;
    }

    static boolean isPrime(long num){
        if(num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0) return false;
        return true;
    }
}
```
