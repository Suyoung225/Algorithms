import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {0, 1, 0, -1}; // 좌, 하, 우, 상
	static int[] dc = {-1, 0, 1, 0};
	static int[][] A;
	static int outSideSandAmount = 0;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int r = N / 2;
		int c = N / 2;
		int changeNumCnt = 0;
		int d = 0;
		int straight = 1;
		while(!(r == 0 && c == 0)){
			for (int i = 1; i <= straight; i++) {
				if(r == 0 && c == 0) break;
				move(r, c, d);
				r = r + dr[d];
				c = c + dc[d];
			}
			d = (d + 1) % 4;
			changeNumCnt++;
			if(changeNumCnt == 2) {
				straight++;
				changeNumCnt = 0;
			}
		}
		System.out.println(outSideSandAmount);
	}

	static void move(int r, int c, int d) {
		int nr = r + dr[d];
		int nc = c + dc[d];
		int totalSpread = 0;
		totalSpread += spreadSand(r, c, (d + 1) % 4, 1, 0.01, A[nr][nc]);
		totalSpread += spreadSand(r, c, (d + 3) % 4, 1, 0.01, A[nr][nc]);
		totalSpread += spreadSand(nr, nc, (d + 1) % 4, 1, 0.07, A[nr][nc]);
		totalSpread += spreadSand(nr, nc, (d + 3) % 4, 1, 0.07, A[nr][nc]);
		totalSpread += spreadSand(nr, nc, (d + 1) % 4, 2, 0.02, A[nr][nc]);
		totalSpread += spreadSand(nr, nc, (d + 3) % 4, 2, 0.02, A[nr][nc]);
		totalSpread += spreadSand(r + dr[d] * 2, c + dc[d] * 2, (d + 1) % 4, 1, 0.1, A[nr][nc]);
		totalSpread += spreadSand(r + dr[d] * 2, c + dc[d] * 2, (d + 3) % 4, 1, 0.1, A[nr][nc]);
		totalSpread += spreadSand(r, c, d, 3, 0.05, A[nr][nc]);

		// alpha
		int leftOverSand = A[nr][nc] - totalSpread;
		int rAlpha = r + dr[d] * 2;
		int cAlpha = c + dc[d] * 2;
		if (rAlpha < 0 || rAlpha >= N || cAlpha < 0 || cAlpha >= N) {
			outSideSandAmount += leftOverSand;
		} else {
			A[rAlpha][cAlpha] += leftOverSand;
		}
	}
	
	static int spreadSand(int r, int c, int d, int times, double percent, int sand) {
		int nr = r + dr[d] * times;
		int nc = c + dc[d] * times;
		int spreadSand = (int) Math.floor(sand * percent);
		if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
			outSideSandAmount += spreadSand;
		} else{
			A[nr][nc] += spreadSand;
		}
		return spreadSand;
	}

}
