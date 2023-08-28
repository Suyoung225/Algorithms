import java.io.*;
import java.util.*;

public class Main{
	static int N, M;
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1 ,1};
	static boolean[][] visited;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		List<String> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(st.nextToken());
		}
		M = list.get(0).length();
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = list.get(i).charAt(j);
			}
		}

		int normalSection = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]){
					normalSection++;
					DFS(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j] == 'G') arr[i][j] = 'R';
			}
		}

		int jaejunSection = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]){
					jaejunSection++;
					DFS(i, j);
				}
			}
		}

		System.out.println(normalSection + " " + jaejunSection);
	}

	private static void DFS(int x, int y) {
		if(visited[x][y]) return;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0  && nx < N && ny >= 0 && ny < M){
				if(arr[x][y] == arr[nx][ny]){
					DFS(nx, ny);
				}
			}
		}
	}

}


