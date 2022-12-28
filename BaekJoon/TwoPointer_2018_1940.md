# 투 포인터/슬라이딩 윈도우 알고리즘

N의 값이 매우 커서 완전 탐색 방식으로 풀면 시간초과가 날 수 있을 때 사용

### 수들의 합 5
https://www.acmicpc.net/problem/2018

어떠한 자연수 N은, 몇 개의 연속된 자연수의 합으로 나타낼 수 있다. 당신은 어떤 자연수 N(1 ≤ N ≤ 10,000,000)에 대해서, 이 N을 몇 개의 연속된 자연수의 합으로 나타내는 가지수를 알고 싶어한다. 이때, 사용하는 자연수는 N이하여야 한다.

예를 들어, 15를 나타내는 방법은 15, 7+8, 4+5+6, 1+2+3+4+5의 4가지가 있다. 반면에 10을 나타내는 방법은 10, 1+2+3+4의 2가지가 있다.

N을 입력받아 가지수를 출력하는 프로그램을 작성하시오.

**시간 복잡도: O(n)**
```java
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int start_idx = 1;
        int end_idx = 1;
        int sum = 1;
        int cnt = 1;
        while(end_idx < N){
            if(sum == N){
                cnt++;
                end_idx++;
                sum += end_idx;
            }else if(sum > N){
                sum -= start_idx;
                start_idx++;
            }else{
                end_idx++;
                sum += end_idx;
            }
        }
        System.out.println(cnt);
    }
}

```

### 주몽 
https://www.acmicpc.net/problem/1940

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 0;
        int end = N-1;
        int cnt = 0;
        while(start < end){
            if(arr[start] + arr[end] > M){
                end--;
            }else if(arr[start] + arr[end] < M){
                start++;
            }else{
                cnt++;
                start++;
                end--;
            }
        }
        System.out.println(cnt);
    }
}

```
