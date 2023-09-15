import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int cnt; // 청소한 방의 개수
	static int[] dx = {-1, 0, 1, 0}; // 북, 동, 남, 서 방향으로 이동시 x 좌표 변화
	static int[] dy = {0, 1, 0, -1};  // 북, 동, 남, 서 방향으로 이동시 y 좌표 변화

	static int[][] room; // 0: 청소하지 않은 칸, 1: 벽, 2: 청소한 칸


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		clean(r, c, d);

		System.out.println(cnt);
	}

	static void clean(int r, int c, int d){
		// 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
		if(room[r][c] == 0){
			cnt++;
			room[r][c] = 2;
		}

		// 현재 칸의 주변 네 칸 중 청소되지 않은 빈 칸 존재 여부 확인
		boolean isAllCleaned = true;
		for (int k = 0; k < 4; k++) {
			if(isNotOverflow(r + dx[k], c + dy[k]) && room[r + dx[k]][c + dy[k]] == 0){
				isAllCleaned = false;
				break;
			}
		}
		// 청소되지 않은 빈칸이 없는 경우
		if(isAllCleaned){
			// 후진할 수 있으면 후진
			int backdx = dx[(2 + d) % 4];
			int backdy = dy[(2 + d) % 4];
			if(isNotOverflow(r + backdx, c + backdy) && room[r + backdx][c + backdy] != 1){
				clean(r + backdx, c + backdy, d);
			}
		} 
		// 주변 네 칸 중 청소되지 않은 빈칸이 있는 경우
		else{
			// 반시계 방향으로 90도 이동
			for (int k = 0; k < 4; k++) {
				d = (d + 3) % 4;
				if(isNotOverflow(r + dx[d], c + dy[d]) && room[r + dx[d]][c + dy[d]] == 0){
					clean(r + dx[d], c + dy[d], d);
					return;
				}
			}
		}


	}
	static boolean isNotOverflow(int x, int y){
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}

