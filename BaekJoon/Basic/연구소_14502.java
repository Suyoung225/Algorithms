import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N, M;
	static int[][] labMap;
	static int maxSafeZone = 0;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		labMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				labMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setWalls(0);
		System.out.println(maxSafeZone);

	}

	static void setWalls(int depth){ // 벽 3개 세우기
		if(depth == 3){
			spreadVirus();
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(labMap[i][j] == 0){
					labMap[i][j] = 1;
					setWalls(depth+1);
					labMap[i][j] = 0;
				}
			}
		}
	}

	static void spreadVirus(){ // 벽이 3개 세워진 상태에서 바이러스가 유출된 공간을 2로 채우기
		Queue<Node> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(labMap[i][j] == 2) queue.add(new Node(i, j));
			}
		}
		int[][] tempMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			tempMap[i] = labMap[i].clone();
		}
		while(!queue.isEmpty()){
			Node node = queue.poll();

			for (int i = 0; i < 4; i++) {
				int x = node.x + dx[i];
				int y = node.y + dy[i];
				if(x >= 0 && x < N && y >= 0 && y < M){
					if(tempMap[x][y] == 0){
						tempMap[x][y] = 2;
						queue.add(new Node(x, y));
					}
				}
			}
		}
		countSafeZone(tempMap);
	}

	static void countSafeZone(int[][] tempMap){ // 바이러스가 퍼진 뒤에 남은 안전 영역 크기 구해서 가장 큰 값과 비교하여 변경
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(tempMap[i][j] == 0){
					safeZone++;
				}
			}
		}
		maxSafeZone = Math.max(safeZone, maxSafeZone);
	}

}

class Node{
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

