import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map; // 입력 받는 맵
	static int[][] cost; // 최소 비용
	static int[] dx = {1 ,-1, 0, 0};
	static int[] dy = {0, 0 ,1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int problem = 0; // 문제
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			cost = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cost[i][j] = Integer.MAX_VALUE;
				}

			}
			problem++;
			sb.append("Problem " + problem + ": " + dijkstra() + "\n"); // 출력문
		}

		System.out.println(sb);
	}

	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		cost[0][0] = map[0][0];
		pq.offer(new Node(0, 0, cost[0][0]));
		while(!pq.isEmpty()){
			Node now = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				if(!isValid(nextX, nextY)) continue;
				if(cost[nextX][nextY] > cost[now.x][now.y] + map[nextX][nextY]){
					cost[nextX][nextY] = cost[now.x][now.y] + map[nextX][nextY];
					pq.offer(new Node(nextX, nextY, cost[nextX][nextY]));
				}
			}
		}
		return cost[N-1][N-1];
	}

	static boolean isValid(int x, int y){
		return (x >= 0 && x < N && y >= 0 && y < N);
	}
}

class Node implements Comparable<Node>{
	int x, y, cost;

	public Node(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost; // 오름차순 정렬
	}
}


