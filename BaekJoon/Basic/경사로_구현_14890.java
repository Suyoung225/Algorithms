import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
	static int roadNum = 0;
	static int[][] mapCol, mapRow;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		mapRow = new int[N][N];
		mapCol = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mapRow[i][j] = mapCol[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			if(walkable(mapRow[i])) roadNum++;
			if(walkable(mapCol[i])) roadNum++;
		}

		System.out.println(roadNum);
	}

	private static boolean walkable(int[] map) {
		int height = map[0];
		int cnt = 1; // 이어지는 같은 높이 개수
		for (int i = 1; i < N; i++) {
			// 이전과 같은 높이
			if(height == map[i]){
				cnt++;
			}
			// 이전이 한 칸 낮음
			else if(height == map[i] - 1){
				if(cnt < L) return false; // 이어지는 같은 높이 개수가 경사로보다 적으면 경사로를 놓을 수 없음
				// 설치 가능
				height++;
				cnt = 1;
			}
			// 이전 칸이 한 칸 높음
			else if(height == map[i] + 1){
				// 뒤에 같은 높이인 칸 개수 구하기
				cnt = 0;
				for (int j = i; j < N; j++) {
					if(height != map[j] + 1) break;
					cnt++;
				}
				if(cnt < L) return false;
				// 설치 가능
				i += L - 1;
				cnt = 0;
				height--;
			}
			else{
				return false;
			}
		}
		return true;
	}


}

