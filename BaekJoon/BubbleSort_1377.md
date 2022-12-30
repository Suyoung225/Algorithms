# 버블 정렬
데이터의 인접 요소끼리 비교하고, swap 연산을 수행하며 정렬하는 방식 <br>
**시간 복잡도: O(N^2)**

### 버블 소트
https://www.acmicpc.net/problem/1377

#### 아이디어
안쪽 for 문에서 swap의 왼쪽으로 이동할 수 있는 최대 거리는 1이다. <br>
정렬 전 index와 정렬 후 index를 비교해 왼쪽으로 가장 많이 이동한 값을 찾으면 됨

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] arr = new Point[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Point(Integer.parseInt(br.readLine()),i);
        }
        Arrays.sort(arr);
        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            if(maxIdx < (arr[i].index - i)) maxIdx = arr[i].index - i;
        }
        System.out.println(maxIdx + 1);
    }

}

class Point implements Comparable<Point>{
    int value;
    int index;

    public Point(int value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(Point o) { // value 기준 오름차순 정렬
        return this.value - o.value;
    }
}
```

