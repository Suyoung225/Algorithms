import java.io.*;
import java.util.*;

public class Main {
	static int N, x, y, d, g;
	static boolean[][] map = new boolean[101][101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		while(N --> 0){
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			drawMap(x, y, getDirections(d, g));
		}

		System.out.println(getSquareNum());

	}

	static List<Integer> getDirections(int d, int g){
		List<Integer> list = new ArrayList<>();
		list.add(d);
		for (int i = 0; i < g; i++) {
			int size = list.size();
			for (int j = size - 1; j >= 0; j--) {
				int nextDir = (list.get(j) + 1) % 4;
				list.add(nextDir);
			}
		}
		return list;
	}
	static void drawMap(int x, int y, List<Integer> directions){
		map[x][y] = true;
		for (Integer direction : directions) {
			switch (direction){
				case 0:
					map[++x][y] = true;
					break;
				case 1:
					map[x][--y] = true;
					break;
				case 2:
					map[--x][y] = true;
					break;
				case 3:
					map[x][++y] = true;
					break;
			}
		}
	}

	static int getSquareNum(){
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]){
					cnt++;
				}
			}
		}
		return cnt;
	}


}
