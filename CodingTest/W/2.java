import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int N,M;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == 0 && !visited[i][j]){
                    DFS(i, j);
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
    static void DFS(int i, int j){
        if(arr[i][j] == 1 || visited[i][j]) return;
        visited[i][j] = true;
        if(i == 0){
            DFS(N-1,j);
        }else{
            DFS(i-1,j);
        }
        if(i == N-1){
            DFS(0,j);
        }else{
            DFS(i+1,j);
        }
        if(j == 0){
            DFS(i,M-1);
        }else{
            DFS(i,j-1);
        }if(j == M-1){
            DFS(i,0);
        }else{
            DFS(i,j+1);
        }

    }

}
