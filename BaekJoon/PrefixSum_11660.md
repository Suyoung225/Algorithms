# 구간 합 (Prefix Sum)

#### 합 배열 S 
```java
S[i] = S[i-1] + A[i]
```

#### i에서 j까지 구간 합
```java
S[j] - S[i-1]
```

#### 2차원 구간 합 배열 D

if i or j = 0, Dij = 0
```java
D[i][j] = D[i][j-1] + D[i-1][j] - D[i-1][j-1] + A[i][j] 
```
D11 = D10 + D01 - D00 + A11 <br>
as D10 = D01 = D00 = 0, then D11 = A11 <br>

D22 = D21 + D12 - D11 + A22 <br>
&nbsp; &nbsp; &nbsp; &nbsp;
= A11 + A21 + A12 + A11 - A11 + A22 <br>
&nbsp; &nbsp; &nbsp; &nbsp;
= A11 + A21 + A12 + A22 <br>


## 구간 합 구하기 5
https://www.acmicpc.net/problem/11660

**문제** <br>
N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다. <br>

예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자. <br>

1	2	3	4 <br>
2	3	4	5 <br>
3	4	5	6 <br>
4	5	6	7 <br>
여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다. <br>

표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.

**입력** <br>
첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)

**출력** <br>
총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 원본 배열
        int arr[][] = new int[N+1][N+1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 합 배열 S
        int S[][] = new int[N+1][N+1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                S[i][j] = S[i-1][j] + S[i][j-1] - S[i-1][j-1] + arr[i][j];
            }
        }

        // 구간 합
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int ans = S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1];
            System.out.println(ans);
        }

    }

}

```
