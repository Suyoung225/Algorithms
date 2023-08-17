import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	static int N, M, maxMove;
	static int[][] board, DP;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			int j = 0;
			for (String s : temp.split("")) {
				if(s.equals("H")) board[i][j] = 0;
				else board[i][j] = Integer.parseInt(s);
				j++;
			}
		}
		visited = new boolean[N][M];
		DP = new int[N][M];
		dfs(0, 0 , 1);
		System.out.println(maxMove);
	}
	static void dfs(int i, int j, int depth){
		maxMove = Math.max(maxMove, depth);
		DP[i][j] = depth;
		int numX = board[i][j]; // 움직여야 할 개수
		for (int k = 0; k < 4; k++) {
			int x = i + (numX * dx[k]);
			int y = j + (numX * dy[k]);
			if(x >= 0 && x < N && y >= 0 && y < M && board[x][y] != 0){
				// 무한으로 가는 경우
				if(visited[x][y]){
					System.out.println(-1);
					System.exit(0);
				}
				// 이미 깊이 탐색한 경우
				if(DP[x][y] > depth) continue;

				visited[x][y] = true;
				dfs(x, y, depth + 1);
				visited[x][y] = false;
			}
		}

	}

}



