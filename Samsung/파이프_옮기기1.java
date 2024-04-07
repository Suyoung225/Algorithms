// https://www.acmicpc.net/problem/17070
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] room;
	static long ans = 0; // 방법 수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		room = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move(0, 0, 0, 1);
		System.out.println(ans);
	}

	static void move(int r1, int c1, int r2, int c2) {
		// (N, N)에 도착
		if (r2 == N - 1 && c2 == N - 1) {
			ans++;
			return;
		}
		// 가로
		if (r1 == r2 && c2 - c1 == 1) {
			right(r2, c2);
			downwardRight(r2, c2);
		}
		// 세로
		else if (r2 - r1 == 1 && c1 == c2) {
			downward(r2, c2);
			downwardRight(r2, c2);
		}
		// 대각선
		else {
			right(r2, c2);
			downward(r2, c2);
			downwardRight(r2, c2);
		}
	}

	// 오른쪽 이동
	static void right(int r2, int c2) {
		if (c2 + 1 < N && room[r2][c2 + 1] == 0) {
			move(r2, c2, r2, c2 + 1);
		}
	}
	// 아래로 이동
	static void downward(int r2, int c2) {
		if (r2 + 1 < N && room[r2 + 1][c2] == 0) {
			move(r2, c2, r2 + 1, c2);
		}
	}

	// 대각선 아래로 이동
	static void downwardRight(int r2, int c2) {
		if (r2 + 1 < N && c2 + 1 < N && room[r2][c2 + 1] == 0
				&& room[r2 + 1][c2 + 1] == 0 && room[r2 + 1][c2] == 0) {
			move(r2, c2, r2 + 1, c2 + 1);
		}
	}

}
