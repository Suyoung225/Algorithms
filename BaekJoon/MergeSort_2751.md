# 병합 정렬

### 수 정렬하기 2
https://www.acmicpc.net/problem/2751


```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static int[] arr;
    public static int[] tmp;
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
            // 오른쪽이 이미 정렬된 경우 왼쪽 요소 순차적으로 뒤에 저장
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
