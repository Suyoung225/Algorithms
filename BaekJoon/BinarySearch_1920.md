# 이진 탐색

## 수 찾기
https://www.acmicpc.net/problem/1920

#### 문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

#### 입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

#### 출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] arr;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            binary(sc.nextInt());
        }
    }
    static void binary(int target){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            int midValue = arr[mid];
            if(midValue > target){
                end = mid - 1;
            }else if(midValue < target){
                start = mid + 1;
            }else{
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
    
}
```
***
## K번째 수
https://www.acmicpc.net/problem/1300

#### 문제
세준이는 크기가 N×N인 배열 A를 만들었다. 배열에 들어있는 수 A[i][j] = i×j 이다. 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다. B를 오름차순 정렬했을 때, B[k]를 구해보자.

배열 A와 B의 인덱스는 1부터 시작한다.

#### 입력
첫째 줄에 배열의 크기 N이 주어진다. N은 105보다 작거나 같은 자연수이다. 둘째 줄에 k가 주어진다. k는 min(109, N2)보다 작거나 같은 자연수이다.

#### 출력
B[k]를 출력한다.

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = 0;
        int start = 1;
        int end = K;
        
        while(start <= end){
            int mid = (start + end) / 2;
            int cnt = 0;
            for (int i = 1; i < N + 1; i++) {
                cnt += Math.min(mid/i, N);
            }
            if(cnt < K){
                start = mid + 1;
            }else{
                result = mid;
                end = mid - 1;
            }
        }
        System.out.println(result);
    }

}
```
