import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[H+1][W+1];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] DP = new int[H+2][W+2]; // N-1번까지 해당 (i,j)에 방문한 횟수
        DP[1][1] = N-1;
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= W; j++) {
                int right = ((DP[i][j] % 2 != 0) && map[i][j] == 1) ? 1 : 0;
                int down = ((DP[i][j] % 2 != 0) && map[i][j] == 0) ? 1 : 0;
                DP[i][j+1] += (DP[i][j] / 2) + right;
                DP[i + 1][j] += (DP[i][j] / 2) + down;
            }
        }
        int i = 1;
        int j = 1;
        while(i <= H && j <= W){
            if(DP[i][j] % 2 == 0 && map[i][j] == 1) j++;
            else if(DP[i][j] % 2 != 0 && map[i][j] == 0) j++;
            else i++;
        }
        System.out.println(i +" "+j);
    }
}

