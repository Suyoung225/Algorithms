// https://www.acmicpc.net/problem/21609
import java.util.*;

public class Main {
	static int N, M, score;
	static int[] dx = {0, -1, 0, 1}; // 좌, 상, 우, 하
	static int[] dy = {-1, 0, 1, 0}; // 좌, 상, 우, 하
	
	static int[][] board; // 격자에 블록 색상
	static Map<Integer, List<Coord>> groupNum; // key: 블록 수, value: 기준 블록
	static boolean[][] isGroup;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		while (true) {
			int maxGroupNum = 1;
			groupNum = new HashMap<>();
			isGroup = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 기준 블록이 될 수 있는 일반 블록이면서, 방문된 적 없는 블록
					if (board[i][j] >= 1 && !isGroup[i][j]) {
						int cnt = getGroupCnt(i, j);
						maxGroupNum = (cnt > maxGroupNum) ? cnt : maxGroupNum;
						if (cnt > 1) {
							if (groupNum.get(cnt) == null) {
								groupNum.put(cnt, new ArrayList<>());
							} 
							groupNum.get(cnt).add(new Coord(i, j));
							
						
						}
					}
				}
			}
			
			if (maxGroupNum == 1) break;
			
			// 1. 가장 큰 블록 그룹 찾기
			Coord coord = findBiggestGroup(groupNum.get(maxGroupNum));
			
			// 2. 블록 제거, 블록 점수 획득
			removeBlocks(coord.x, coord.y);
			score += (maxGroupNum * maxGroupNum);
			
			// 3. 중력 작용
			gravity();

			// 4. 반시계 방향 회전
			rotate();
			
			// 5. 중력 작용
			gravity();
		}
		
		System.out.println(score);
	}
	
	static void rotate() {
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = board[j][N-1-i];
			}
		}
		for (int i = 0; i < N; i++) {
			board[i] = Arrays.copyOf(arr[i], N);
		}
	}
	
	static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N-1; i > 0; i--) {
				if (board[i][j] == -2) {
					int idx = i - 1;
					while(idx >= 0 && board[idx][j] == -2) {
						idx--;
					}
					if (idx >= 0 && board[idx][j] >= 0) {
						board[i][j] = board[idx][j];
						board[idx][j] = -2;
					}
					
				}
			}
		}
	}
	
	static void removeBlocks(int x, int y) {
		boolean[][] visited = new boolean[N][N];
		Queue<Coord> queue = new LinkedList<>();
		queue.add(new Coord(x, y));
		visited[x][y] = true;
		int color = board[x][y];
		while(!queue.isEmpty()) {
			Coord now = queue.poll();
			board[now.x][now.y] = -2; // 블록 제거
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (movable(nx, ny, color) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Coord(nx, ny));
				}
			}
		}
	}
	
	static Coord findBiggestGroup(List<Coord> groupList) {
		if (groupList.size() == 1) return new Coord(groupList.get(0).x, groupList.get(0).y);
		
		// 무지개 블록 수 구하기
		int[] rainbow = new int[groupList.size()];
		int max = -1;
		for(int i = 0; i < groupList.size(); i++) {
			rainbow[i] = getRainbowCnt(groupList.get(i).x, groupList.get(i).y);
			max = (rainbow[i] > max) ? rainbow[i] : max;
		}
		List<Coord> groupListByRainbow = new ArrayList<>();
		for(int i = 0; i < groupList.size(); i++) {
			if (rainbow[i] == max) {
				groupListByRainbow.add(new Coord(groupList.get(i).x, groupList.get(i).y));
			}
		}
		if (groupListByRainbow.size() == 1) {
			return new Coord(groupListByRainbow.get(0).x, groupListByRainbow.get(0).y);
		}
		
		// 기준 블록의 행이 가장 큰 것을, 그 것도 여러 개이면 열이 가장 큰 것
		groupListByRainbow.sort(null);
		return new Coord(groupListByRainbow.get(0).x, groupListByRainbow.get(0).y);
	}
	
	static int getRainbowCnt(int x, int y) {
		boolean[][] visited = new boolean[N][N];
		Queue<Coord> queue = new LinkedList<>();
		queue.add(new Coord(x, y));
		visited[x][y] = true;
		int color = board[x][y];
		int rainbowCnt = 0;
		while(!queue.isEmpty()) {
			Coord now = queue.poll();
			if(board[now.x][now.y] == 0) rainbowCnt++;
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (movable(nx, ny, color) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Coord(nx, ny));
				}
			}
		}
	
		return rainbowCnt;
	}
	
	static int getGroupCnt(int x, int y) {
		boolean[][] visited = new boolean[N][N];
		Queue<Coord> queue = new LinkedList<>();
		queue.add(new Coord(x, y));
		visited[x][y] = true;
		int color = board[x][y];
		int cnt = 0;
		while(!queue.isEmpty()) {
			Coord now = queue.poll();
			cnt++;
			isGroup[now.x][now.y] = true;
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (movable(nx, ny, color) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Coord(nx, ny));
				}
			}
		}
		return cnt;
	}
	
	static boolean movable(int x, int y, int color) {
		return x >= 0 && x < N && y >= 0 && y < N && // 격자 안으로
				(board[x][y] == 0 || board[x][y] == color); // 무지개색 또는 같은 색
	}
	
}

class Coord implements Comparable<Coord>{
	int x, y;
	
	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Coord o) {
		if(o.x == this.x) return o.y - this.y;
		return o.x - this.x;
	}
}
