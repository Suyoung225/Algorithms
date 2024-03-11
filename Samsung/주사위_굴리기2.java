// https://www.acmicpc.net/problem/23288
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {0, 1, 0, -1}; // 동, 남 ,서, 북
	static int[] dc = {1, 0, -1, 0};
	static int N, M, K, totalScore;
	static int[][] map;
	static int[][] scores;
	static int[] dice = {6, 5, 1, 2, 3, 4}; // 바닥, 앞, 위, 뒤, 동, 서
	// 동, 남, 서, 북으로 이동 시 주사위 전개도
	static int[][] diceMove = {{4, 1, 5, 3, 2, 0}, {1, 2, 3, 0, 4, 5},
			{5, 1, 4, 3, 0, 2}, {3, 0, 1, 2, 4, 5}};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 각 칸에서 획득할 수 있는 점수
		scores = new int[N][M];
		setScore();

		int d = 0; // 가장 처음에 주사위의 이동 방향은 동쪽
		int r = 0; // 현재 주사위가 있는 칸 (row)
		int c = 0;  // 현재 주사위가 있는 칸 (col)

		for (int i = 0; i < K; i++) {
			// 첫 방향은 항상 동쪽 (d = 0)
			if (i != 0) {
				int A = dice[0]; // 주사위의 아랫면에 있는 정수 A
				int B = map[r][c];
				// A와 B를 이용해 이동 방향 결정
				if(A > B) {
					d = (d + 1) % 4;
				} else if (A < B) {
					d = (d + 3) % 4;
				} else {
					// 방향 변화 없음
				}
			}
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
			if(!(nr >= 0 && nr < N && nc >= 0 && nc < M)) {
				d = (d + 2) % 4;
				nr = r + dr[d];
				nc = c + dc[d];
			}
			moveDice(d);
			r = nr;
			c = nc;
			totalScore += scores[r][c];
		}
		System.out.println(totalScore);
	}

	private static void moveDice(int d) {
		int[] temp = new int[6];
		for (int i = 0; i < 6; i++) {
			temp[i] = dice[diceMove[d][i]];
		}
		dice = Arrays.copyOf(temp, 6);
	}


	static void setScore() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(scores[i][j] != 0) continue;
				boolean[][] visited = new boolean[N][M];
				Queue<Coord> queue = new LinkedList<>();
				queue.add(new Coord(i, j));
				visited[i][j] = true;
				int score = 1;
				while(!queue.isEmpty()) {
					Coord now = queue.poll();
					for (int d = 0; d < 4; d++) {
						int nr = now.r + dr[d];
						int nc = now.c + dc[d];
						if(nr >= 0 && nr < N && nc >= 0 && nc < M
								&& map[nr][nc] == map[now.r][now.c] && !visited[nr][nc]) {
							queue.add(new Coord(nr, nc));
							visited[nr][nc] = true;
							score++;
						}
					}
				}
				scores[i][j] = score * map[i][j]; // B * C
			}
		}
	}

}

class Coord {
	int r, c;
	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
