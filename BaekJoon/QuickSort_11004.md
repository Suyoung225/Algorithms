# 퀵 정렬
기준값(pivot)을 선정해 해당 값보다 작은 데이터와 큰 데이터로 분류하는 것을반복해 정렬하는 알고리즘 <br>

**시간 복잡도: O(nlogn)**
1. 리스트 안에 한 요소를 privot으로 선택 (주로 중간에 위치한 요소)
2. 피벗을 기준으로 피벗보다 작은 요소들은 모두 피벗의 왼쪽, 큰 요소들은 모두 피벗의 오른쪽으로 옮겨진다.
3. 피벗을 기준으로 왼쪽과 오른쪽 리스트에서 각자 피벗을 선택하여 리스트 정렬
4. 부분 리스트들이 더이상 분할이 되지 않을 때까지 반복

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        quickSort(A, 0, N - 1);

        System.out.println(A[K-1]);
    }
    
    private static void quickSort(int[] arr, int L, int R){
        //파티션 나누고 오른쪽 값 첫번째 값을 받아옴
        int part = partition(arr, L, R);

        //왼쪽 파티션에 데이터 존재
        if (L < part - 1)
            quickSort(arr, L, part - 1);
            
        //오른쪽 파티션에 데이터 존재
        if (part < R)
            quickSort(arr, part, R);
    }

    private static int partition(int[] arr, int L, int R){
        int pivot = arr[(L + R)/2];
        while (L <= R) {
            while (arr[L] < pivot) L++;
            while (arr[R] > pivot) R--;
            if (L <= R) {
                swap (arr, L, R);
                L++;
                R--;
            }
        }
        return L;
    }

    private static void swap(int[] arr, int L, int R){
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }
}

```
