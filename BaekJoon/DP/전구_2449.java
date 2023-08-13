import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] bulb = new int[N];
		for (int i = 0; i < N; i++) {
			bulb[i] = Integer.parseInt(st.nextToken());
		}
		// DP[i][j] = i번째 전구부터 j번째 전구까지 같아지려면 색을 바꿔야 하는 횟수
		// DP[0][N-1] 이 정답
		int[][] DP = new int[N][N];
		for (int interval = 1; interval < N; interval++) {
			for (int i = 0; i < (N - interval); i++) {
				int s = i;
				int e = i + interval;
				int temp = Integer.MAX_VALUE;
				for (int j = s; j < e; j++) {
					temp = Math.min(temp, DP[s][j] + DP[j+1][e] + ((bulb[s] != bulb[e]) ? 1 : 0));
				}
				DP[s][e] = temp;
			}
		}
		System.out.println(DP[0][N-1]);
	}

}



