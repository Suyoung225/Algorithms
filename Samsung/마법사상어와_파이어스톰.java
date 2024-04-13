// https://www.acmicpc.net/problem/20058
import java.util.*;
import java.io.*;

public class Main {
	static int N, Q, len;
	static int[][] A;
	static int[] dr = {-1, 1, 0 ,0 };
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] isGroup;

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		len = (int) Math.pow(2, N);
		A = new int[len][len];
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] L = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int q = 0; q < Q; q++) {
			// 시계 방향으로 회전
			int interval = (int) Math.pow(2, L[q]);
			for (int i = 0; i < len; i += interval) {
				for (int j = 0; j < len; j += interval) {
					rotate(i, j, interval);
				}
			}
			// 얼음 양 줄어듦
			reduceIce();
		}
		// 남아있는 얼음의 합
		int total = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				total += A[i][j];
			}
		}
		System.out.println(total);
		
		// 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
		isGroup = new boolean[len][len];
		int maxGroupSize = getLargestIceGroup();
		System.out.println(maxGroupSize);
		
	}
	
	static void rotate(int r, int c, int interval) {
		int[][] temp = new int[interval][interval];
		for (int i = 0; i < interval; i++) {
			for (int j = 0; j < interval; j++) {
				temp[i][j] = A[r + i][c + j];
			}
		}
		int[][] roated =  new int[interval][interval];
		for (int i = 0; i < interval; i++) {
			for (int j = 0; j < interval; j++) {
				roated[i][j] = temp[interval -1 - j][i];
			}
		}
		for (int i = 0; i < interval; i++) {
			for (int j = 0; j < interval; j++) {
				A[r + i][c + j] = roated[i][j];
			}
		}
	}
	
	static void reduceIce() {
		boolean[][] reduceIce = new boolean[len][len];
		int noIceCnt = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				noIceCnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (!isIce(nr, nc)) {
						noIceCnt++;
					}
					if (noIceCnt == 2) {
						reduceIce[i][j] = true;
						break;
					}
				}
			}
		}
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (reduceIce[i][j] && A[i][j] > 0) {
					A[i][j]--;
				}
			}
		}
	}
	
	static int getLargestIceGroup() {
		int max = 0;
		int groupSize = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!isGroup[i][j] && A[i][j] > 0) {
					groupSize = getIceGroup(i, j);
					max = Math.max(max, groupSize);
				}
			}
		}
		return max;
	}
	
	static int getIceGroup(int r, int c) {
		boolean[][] visited = new boolean[len][len];
		int groupSize = 0;
		Queue<Coord> queue = new LinkedList<>();
		queue.add(new Coord(r, c));
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			Coord now = queue.poll();
			groupSize++;
			isGroup[now.r][now.c] = true;
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				if (isIce(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new Coord(nr, nc));
				}
			}
		}
		return groupSize;
	}
	
	static boolean isIce(int nr, int nc) {
		return nr >= 0 && nr < len && nc >= 0 && nc < len && A[nr][nc] > 0;
	}
	
}

class Coord {
	int r, c;
	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
