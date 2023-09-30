import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		long[] D = new long[N];
		D[0] = arr[0];
		long ans = D[0];

		for (int i = 1; i < N; i++) {
			D[i] = Math.max(D[i-1] + arr[i], arr[i]);
			ans = Math.max(ans, D[i]);
		}


		System.out.println(ans);
	}


}
