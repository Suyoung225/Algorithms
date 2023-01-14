# 시간 초과
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> arr;

    static int num, match;
    static long sum = 0;
    static long max = 0;
    static long[][] W;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        W = new long[C+1][C+1];
        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                W[i][j] = Long.parseLong(st.nextToken());
            }
        }
        A = new int[N];
        B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        match = N;
        num = M;
        if(N > M){
            num = N;
            match = M;
        }

        arr = new ArrayList<>();
        DFS(0,0);

        System.out.println(max);


    }
    static void DFS(int L, int s) {
        if (L == match) {
            if(sum > max) max = sum;
            sum = 0;
        } else {
            for (int i = s; i < num; i++) {
                sum += W[A[L]][B[i]];
                DFS(L + 1, i + 1);
            }
        }
    }


}
