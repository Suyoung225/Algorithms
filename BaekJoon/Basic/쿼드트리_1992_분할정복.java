import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			int j = 0;
			String input = br.readLine();
			for (char c : input.toCharArray()) {
				arr[i][j++] = c - '0';
			}
		}
		quadTree(0, 0, N);
		System.out.println(sb.toString());
    }
	
	private static void quadTree(int I, int J, int len) {
		if (isCompactable(I, J, len)) {
			sb.append(arr[I][J]);
			return;
		}
		
		sb.append("(");
		quadTree(I, J, len/2);
		quadTree(I, J + len/2, len/2);
		quadTree(I + len/2, J, len/2);
		quadTree(I + len/2, J + len/2, len/2);
		sb.append(")");
	}
	
	private static boolean isCompactable(int I, int J, int len) {
		int value = arr[I][J];
		for (int i = I; i < I + len; i++) {
			for (int j = J; j < J + len; j++) {
				if (arr[i][j] != value) return false;
			}
		}
		return true;
	}

}
