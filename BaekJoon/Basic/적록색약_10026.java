import java.io.*;
import java.util.*;

public class Main{
	static int N, M; // 지도의 행과 열 크기
	static int[] dx = {-1, 1, 0 ,0};  // 상하좌우 이동을 위한 배열
	static int[] dy = {0, 0, -1 ,1};
	static boolean[][] visited; // 방문 여부를 나타내는 배열
	static char[][] map;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// 입력된 map 정보를 리스트에 저장
		List<String> mapList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			mapList.add(st.nextToken());
		}

		M = mapList.get(0).length();
		map = new char[N][M];

		// 리스트에 저장된 지도 정보를 배열에 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapList.get(i).charAt(j);
			}
		}

		int normalSection = 0; // 일반인이 볼 때의 섹션 수를 나타내는 변수
		visited = new boolean[N][M];

		// 일반인의 섹션 수 계산을 위한 DFS
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]){
					normalSection++;
					DFS(i, j);
				}
			}
		}

		// 빨강과 초록을 같은 색으로 처리
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'G')
					map[i][j] = 'R';
			}
		}

		int jaejunSection = 0; // 적록색약이 볼 때의 섹션 수를 나타내는 변수
		visited = new boolean[N][M];

		// 적록색약의 섹션 수 계산을 위한 DFS
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]){
					jaejunSection++;
					DFS(i, j);
				}
			}
		}
		
		// 정답 출력
		System.out.println(normalSection + " " + jaejunSection);
	}

	private static void DFS(int x, int y) {
		if(visited[x][y]) return;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx >= 0  && nx < N && ny >= 0 && ny < M){
				if(map[x][y] == map[nx][ny]){
					DFS(nx, ny);
				}
			}
		}
	}

}


