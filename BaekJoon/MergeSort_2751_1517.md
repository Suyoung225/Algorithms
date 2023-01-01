# 병합 정렬

### 수 정렬하기 2
https://www.acmicpc.net/problem/2751


```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static int[] arr, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tmp = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(0, N-1);
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
    
    public static void mergeSort(int start, int end){
        if(start >= end) return;
        int mid = (start + end)/2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);

        int L = start;
        int R = mid + 1;
        int point = start;

        while(L <= mid || R <= end){
            // 오른쪽이 이미 정렬된 경우 왼쪽 요소를 순차적으로 뒤에 저장
            // 양쪽에 요소가 남아 있으며 왼쪽이 오른쪽보다 작을 때 왼쪽 요소 저장
            if(R > end||(L <= mid && arr[L] <= arr[R])){
                tmp[point++] = arr[L++];
            }
            // L > mid || R<=end && arr[L] > arr[R]
            else{
                tmp[point++] = arr[R++];
            }
        }
        for (int i = start; i < end + 1; i++) {
            arr[i] = tmp[i];
        }
    }
} 
```
***

### 버블 소트
https://www.acmicpc.net/problem/1517
#### 문제
N개의 수로 이루어진 수열 A[1], A[2], …, A[N]이 있다. 이 수열에 대해서 버블 소트를 수행할 때, Swap이 총 몇 번 발생하는지 알아내는 프로그램을 작성하시오.

버블 소트는 서로 인접해 있는 두 수를 바꿔가며 정렬하는 방법이다. 예를 들어 수열이 3 2 1 이었다고 하자. 이 경우에는 인접해 있는 3, 2가 바뀌어야 하므로 2 3 1 이 된다. 다음으로는 3, 1이 바뀌어야 하므로 2 1 3 이 된다. 다음에는 2, 1이 바뀌어야 하므로 1 2 3 이 된다. 그러면 더 이상 바꿔야 할 경우가 없으므로 정렬이 완료된다.

#### 입력
첫째 줄에 N(1 ≤ N ≤ 500,000)이 주어진다. 다음 줄에는 N개의 정수로 A[1], A[2], …, A[N]이 주어진다. 각각의 A[i]는 0 ≤ |A[i]| ≤ 1,000,000,000의 범위에 들어있다.

#### 출력
첫째 줄에 Swap 횟수를 출력한다

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static int[] arr, tmp;
    public static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tmp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, N-1);

        System.out.println(result);
    }
    
    public static void mergeSort(int start, int end){
        if(start >= end) return;
        int mid = (start + end)/2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);

        int L = start;
        int R = mid + 1;
        int point = start;

        while(L <= mid || R <= end){
            if(R > end||(L <= mid && arr[L] <= arr[R])){
                tmp[point++] = arr[L++];
            }
            else{ // L > mid || R <= end && arr[L] > arr[R]
                if(L <= mid && arr[L] > arr[R] && R <= end){ // 오른쪽 값이 작은 경우
                    result += R - point; // 추월한 인덱스만큼 result에 추가
                }
                tmp[point++] = arr[R++];
            }
        }
        for (int i = start; i < end + 1; i++) {
            arr[i] = tmp[i];
        }
    }
}
```
