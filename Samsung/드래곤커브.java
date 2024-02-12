import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] board = new boolean[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			drawDragonCurve(x, y, getDirections(d, g));
		}
		System.out.println(countCircles());
	}

	static List<Integer> getDirections(int d, int g) {
		List<Integer> directions = new ArrayList<>();
		directions.add(d);
		for (int i = 1; i <= g; i++) {
			int size = directions.size();
			for (int j = size - 1; j >= 0; j--) {
				directions.add((directions.get(j) + 1) % 4);
			}
		}
		return directions;
	}

	static void drawDragonCurve(int x, int y, List<Integer> directions) {
		board[x][y] = true;
		for (Integer direction : directions) {
			int nx = x + dx[direction];
			int ny = y + dy[direction];
			board[nx][ny] = true;
			x = nx;
			y = ny;
		}
	}

	static int countCircles() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
