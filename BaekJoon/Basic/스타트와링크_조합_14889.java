import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int minDiff = Integer.MAX_VALUE; // 가장 적게 차이나는 점수
	static int[][] S;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		combi(new boolean[N], 0, N/2);

		System.out.println(minDiff);
	}
	static void combi(boolean[] visited, int start, int r){
		if(r == 0){
			minDiff = Math.min(getScoreDiff(visited), minDiff);
			return;
		}
		for (int i = start; i < N; i++) {
			visited[i] = true;
			combi(visited, i + 1 , r - 1);
			visited[i] = false;
		}
	}

	private static int getScoreDiff(boolean[] visited) {
		int team1Score = 0; // visited가 true인 팀
		int team2Score = 0; // visited가 false인 팀
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if(visited[i] && visited[j]){ // team1
					team1Score += (S[i][j] + S[j][i]);
				}else if(!visited[i] && !visited[j]){ // team2
					team2Score += (S[i][j] + S[j][i]);
				}
			}
		}
		return Math.abs(team1Score - team2Score);
	}


}

