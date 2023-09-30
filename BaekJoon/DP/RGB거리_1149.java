import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N+1][3];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] D = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + arr[i][0];
			D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + arr[i][1];
			D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + arr[i][2];

		}

		System.out.println(Math.min(D[N][2], Math.min(D[N][0], D[N][1])));
	}




}
