import java.io.*;
import java.util.*;

public class Main {
	static char[][] board = new char[5][5];
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			board[i] = br.readLine().toCharArray();
		}
		combi(new int[7], 0, 0, 0);
		System.out.println(ans);
	}
	static void combi(int[] seatNum, int start, int r, int SCnt){ // seatNum[i]: i 번 학생의 자리
		if(r - SCnt > 3) return; // 임도연파 4명 이상이면 이다솜파가 3명 이하가 되므로
		if(r == 7){
			if(isConnected(seatNum)) ans++;
			return;
		}
		for (int i = start; i < 25; i++) {
			int row = i / 5;
			int col = i % 5;
			seatNum[r] = i;
			combi(seatNum, i + 1, r + 1,  (board[row][col] == 'S') ? SCnt + 1 : SCnt);
		}
	}

	static boolean isConnected(int[] seatNum){
		boolean[] visited = new boolean[25];
		int[] dx = {-1, 1, 0 ,0};
		int[] dy = {0 ,0, -1, 1};
		Queue<Integer> queue = new LinkedList<>();
		visited[seatNum[0]] = true;
		int visitCnt = 1;
		queue.add(seatNum[0]);
		while(!queue.isEmpty()){
			int now = queue.poll();
			for (int k = 0; k < 4; k++) {
				int row = now / 5 + dx[k];
				int col = now % 5 + dy[k];
				if(row >= 0 && row < 5 && col >= 0 && col < 5){
					// 움직인 자리도 seatNum에 해당하는 경우
					int moveNum = 5 * row + col;
					if(!visited[moveNum]){
						for (int i : seatNum) {
							if(moveNum == i){
								visited[moveNum] = true;
								visitCnt++;
								if(visitCnt == 7) return true;
								queue.offer(moveNum);
							}
						}
					}
				}
			}
		}
		return false;
	}


}
