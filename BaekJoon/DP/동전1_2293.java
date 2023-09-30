import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N+1];
		int[] D = new int[K+1];
		D[0] = 1;

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			for (int j = arr[i]; j <= K; j++) {
				D[j] += D[j - arr[i]];
			}

		}
		System.out.println(D[K]);
	}


}
