import java.io.*;
import java.util.*;

public class Main {
	static int M, N, H;
	static int unripeCnt, changeCnt; // 익지 않은 토마토 개수, 익게 만드는 개수
	static int totalDays;
	static int[][][] tomatoes;
	static int nextH, nextX, nextY;

	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomatoes = new int[H][N][M];
		
		Queue<Coord> queue = new LinkedList<>();
		
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tomatoes[h][i][j] = Integer.parseInt(st.nextToken());
					if(tomatoes[h][i][j] == 0) unripeCnt++;
					else if(tomatoes[h][i][j] == 1) queue.offer(new Coord(h, i, j, 0));
				}
			}
		}

    // 안 익은 토마토가 없는 경우
		if(unripeCnt == 0){
			System.out.println("0");
			System.exit(0);
		}

		while(!queue.isEmpty()){
			Coord now = queue.poll();
			totalDays = Math.max(totalDays, now.days); // 모두 익게 만드는 마지막 날 계산
			for (int k = 0; k < 6; k++) {
				nextH = now.h + dh[k];
				nextX = now.x + dx[k];
				nextY = now.y + dy[k];
				if(isValid(nextH, nextX, nextY) && tomatoes[nextH][nextX][nextY] == 0){ // 이동 가능하고 이동한 자리가 안 익은 토마토인 경우 
					changeCnt++;
					tomatoes[nextH][nextX][nextY] = 1; 
					queue.offer(new Coord(nextH, nextX, nextY, now.days + 1));
				}
			}
		}

		if(unripeCnt > changeCnt) System.out.println("-1"); // 모두 익게 할 수 없는 경우
		else System.out.println(totalDays);
	}

	static boolean isValid(int h, int x, int y){
		return h >= 0 && h < H && x >= 0 && x < N && y >=0 && y < M;
	}

}

class Coord {
	int h, x, y, days; 

	public Coord(int h, int x, int y, int days) {
		this.h = h;
		this.x = x;
		this.y = y;
		this.days = days;
	}
}
