import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] score  = new int[10001];
		for (int i = 1; i <= N; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		int[] DP = new int[10001];
		DP[1] = score[1];
		DP[2] = score[1] + score[2];
		for (int i = 3; i <= N; i++) {
			DP[i] = Math.max(DP[i-3] + score[i-1] + score[i], DP[i-2] + score[i]);
		}
		System.out.println(DP[N]);
	}

}
