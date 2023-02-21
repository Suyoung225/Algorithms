# Segement Tree

### 구간합 응용

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static int treeSize;
    static int startIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> hm = new HashMap<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            hm.put(Integer.parseInt(st.nextToken()), i);
        }

        int temp = N;
        int height = 0;
        while(temp != 0){
            temp = temp / 2;
            height++;
        }

        treeSize = (int) Math.pow(2, height + 1);
        tree = new long[treeSize + 1];
        startIdx = treeSize / 2 - 1;

        st = new StringTokenizer(br.readLine());
        int[] B = new int[N+1];
        for (int i = 1; i <= N; i++) {
            B[i] = hm.get(Integer.parseInt(st.nextToken()));
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += sum(B[i] + startIdx, N + startIdx);
            update(B[i] + startIdx);
        }
        System.out.println(ans);
    }

    static long sum(int s, int e) {
        long sum = 0;
        while(s <= e){
            if(s % 2 == 1){
                sum += tree[s];
            }
            if(e % 2 == 0){
                sum += tree[e];
            }
            s = (s+1)/2;
            e = (e-1)/2;
        }
        return sum;
    }

    static void update(int node) {
        while(node > 0){
            tree[node] += 1;
            node = node / 2;
        }
    }
    
}


```

***

### 구간합
https://www.acmicpc.net/problem/2042

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static int treeSize;
    static int startIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int temp = N;
        int height = 0;
        while(temp != 0){
            temp = temp / 2;
            height++;
        }

        treeSize = (int) Math.pow(2, height + 1);
        tree = new long[treeSize + 1];
        startIdx = treeSize / 2 - 1;

        for (int i = startIdx + 1; i <= startIdx + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                long c =  Long.parseLong(st.nextToken());
                changeTree(startIdx + b, c);
            }else{
                int b = Integer.parseInt(st.nextToken());
                int c =  Integer.parseInt(st.nextToken());
                System.out.println(getSum(b + startIdx,c + startIdx));
            }
        }

    }

    static long getSum(int s, int e) {
        long sum = 0;
        while(s <= e){
            if(s % 2 == 1){
                sum += tree[s];
            }
            if(e % 2 == 0){
                sum += tree[e];
            }
            s = (s+1)/2;
            e = (e-1)/2;
        }
        return sum;
    }

    static void changeTree(int idx, long value) {
        long diff = value - tree[idx];
        while(idx > 0){
            tree[idx] = tree[idx] + diff;
            idx = idx / 2;
        }
    }

    static void setTree() {
        int idx = treeSize - 1;
        while(idx != 1){
            tree[idx/2] += tree[idx];
            idx--;
        }
    }


}

```

***

### 최솟값
https://www.acmicpc.net/problem/10868

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree;
    static int treeSize;
    static int startIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int temp = N;
        int height = 0;
        while(temp != 0){
            temp = temp / 2;
            height++;
        }

        treeSize = (int) Math.pow(2, height + 1);
        tree = new long[treeSize];
        startIdx = (int) Math.pow(2, height);

        for (int i = 0; i < treeSize; i++) {
            tree[i] = Long.MAX_VALUE;
        }

        for (int i = startIdx; i < startIdx + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(getMin(a + startIdx - 1,b + startIdx - 1));
            }
        }

    static long getMin(int s, int e) {
        long min = Long.MAX_VALUE;
        while(s <= e){
            if(s % 2 == 1){
                min = Math.min(min, tree[s]);
            }
            if(e % 2 == 0){
                min = Math.min(min, tree[e]);
            }
            s = (s+1)/2;
            e = (e-1)/2;
        }
        return min;
    }

    static void setTree() {
        int idx = treeSize - 1;
        while(idx != 1){
            if(tree[idx/2] > tree[idx])
                tree[idx/2] = tree[idx];
            idx--;
        }
    }


}


```
