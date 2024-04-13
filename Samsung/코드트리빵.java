// https://www.codetree.ai/training-field/frequent-problems/problems/codetree-mon-bread/description?page=1&pageSize=20

import java.util.*;
import java.io.*;

public class Main {
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static boolean[][] impassable;
	static int[][] store, board, people;
	static int n, m;
	static int arriveCnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][n]; // 0: empty, 1: base camp, -1: occupied
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		store = new int[m][2]; // m번째 사람의 desired convenience store
		people = new int[m][2]; // m번째 사람의 현재 위치
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			store[i][0] = Integer.parseInt(st.nextToken()) - 1;
			store[i][1] = Integer.parseInt(st.nextToken()) - 1;
			people[i][0] = -1;
			people[i][1] = -1;
		}
		impassable = new boolean[n][n];
		int time = 1;
		while (arriveCnt < m) {
			// 1. move 
			for (int i = 0; i < m; i++) {
				if (people[i][0] != -1 && // 출발했으나 도착하지 않은 사람만 move
					!(people[i][0] == store[i][0] && people[i][1] == store[i][1])) {
					move(i);
				}
			}
			// 2. reset impassable board
			for (int i = 0; i < m; i++) {
				if ((people[i][0] == store[i][0] && people[i][1] == store[i][1])
					&& !impassable[store[i][0]][store[i][1]]) {
					impassable[store[i][0]][store[i][1]] = true;
					arriveCnt++;
				}

			}
			
			// 3. enters the base camp & reset impassable board
			if (time <= m) {
				int[] basecamp = chooseBaseCamp(time - 1);
				people[time - 1][0] = basecamp[0];
				people[time - 1][1] = basecamp[1];
				impassable[basecamp[0]][basecamp[1]] = true;
			}
			time++;
		}
		System.out.println(time - 1);
	}
	
	static int[] chooseBaseCamp(int person) {
		int r = store[person][0];
		int c = store[person][1];
		int[][] dist = new int[n][n];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		dist[r][c] = 1;
		int minDist = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if (dist[now[0]][now[1]] > minDist) break;
			if (board[now[0]][now[1]] == 1) {
				minDist = dist[now[0]][now[1]];
			}
			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dr[d];
				int nc = now[1] + dc[d];
				if (nr >= 0 && nr < n && nc >= 0 && nc < n 
						&& !impassable[nr][nc] && dist[nr][nc] == 0) {
					queue.add(new int[] {nr, nc});
					dist[nr][nc] = dist[now[0]][now[1]] + 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1 && dist[i][j] == minDist) {
					return new int[] {i, j};
				}
			}
		}
		
		return new int[2];
	}
	
	static void move(int person) {
		int r = people[person][0];
		int c = people[person][1];
		boolean[][] visited = new boolean[n][n];
		Queue<Coord> queue = new LinkedList<>();
		queue.add(new Coord(r, c, -1));
		visited[r][c] = true;
		while(!queue.isEmpty()) {
			Coord now = queue.poll();
			// 도착 시 종료
			if (now.r == store[person][0] && now.c == store[person][1]) {
				// 가장 빠른 길의 처음 스텝으로 이동
				people[person][0] = r + dr[now.firstDirection];
				people[person][1] = c + dc[now.firstDirection];
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				int nd = (now.firstDirection == -1) ? d : now.firstDirection;
				if (nr >= 0 && nr < n && nc >= 0 && nc < n 
						&& !impassable[nr][nc] && !visited[nr][nc]) {
					queue.add(new Coord(nr, nc, nd));
					visited[nr][nc] = true;
				}
			}
		}
	}
}

class Coord {
	int r, c, firstDirection;
	public Coord(int r, int c, int firstDirection) {
		this.r = r;
		this.c = c;
		this.firstDirection = firstDirection;
	}
}
