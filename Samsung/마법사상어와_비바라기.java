// https://www.acmicpc.net/problem/21610
import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] board;
	static boolean[][] cloud;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}; // 좌부터 시켸 방향
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1}; // 좌부터 시켸 방향
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] d = new int[M];
		int[] s = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken()) - 1;
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		cloud = new boolean[N][N];
		cloud[N-1][0] = cloud[N-1][1] = cloud[N-2][0] = cloud[N-2][1] = true;
		
		for (int i = 0; i < M; i++) {
			// 1. 모든 구름이 di 방향으로 si칸 이동한다.
			move(d[i], s[i]);
			// 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			rain();
			// 4. 물복사 마법
			magic();
			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다
			cloud();
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans += board[i][j];
			}
		}
		System.out.println(ans);
	}
	
	static void move(int d, int s) {
		boolean[][] movedCloud = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cloud[i][j]) {
					int nr = i;
					int nc = j;
					for (int k = 0; k < s; k++) {
						nr = nr + dr[d];
						nc = nc + dc[d];
						if (nr == -1) nr = N-1;
						if (nr == N) nr = 0;
						if (nc == -1) nc = N - 1;
						if (nc == N) nc = 0;
					}
					movedCloud[nr][nc] = true;
					cloud[i][j] = false;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			cloud[i] = Arrays.copyOf(movedCloud[i], N);
		}
	}
	
	static void rain() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cloud[i][j]) {
					board[i][j]++;
				}
			}
		}
	}
	
	static void magic() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cloud[i][j]) {
					for (int d = 1; d < 8; d +=2) { // 대각선 방향: 1, 3, 5, 7
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] != 0) {
							board[i][j]++; 
						}
					}
				}
			}
		}
	}
	
	static void cloud() {
		boolean[][] lastCloud = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			lastCloud[i] = Arrays.copyOf(cloud[i], N);
		}
		cloud = new boolean[N][N]; 
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!lastCloud[i][j] && board[i][j] >= 2) {
					board[i][j] -= 2;
					cloud[i][j] = true;
				}
			}
		}
	}
	
}
	
