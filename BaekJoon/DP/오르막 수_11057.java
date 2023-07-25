import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] D = new int[N][10];
//		Arrays.stream(D[0]).forEach(i -> i = 1);
		Arrays.fill(D[0], 1);
		Arrays.stream(D).forEach(i -> i[0] = 1);
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < 10; j++) {
				D[i][j] = D[i][j-1] + D[i-1][j];
				D[i][j] %= 10007;
			}
		}
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += D[N-1][i];
		}
		System.out.println(sum % 10007);
	}

}

