import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static String[] chars;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		chars = br.readLine().split(" ");
		Arrays.sort(chars);
		backtrack(new boolean[C], 0, L);
	}
	static void backtrack(boolean[] visited, int start, int r){
		if(r == 0){
			if(isValid(visited))print(visited);
			return;
		}
		for (int i = start; i < C; i++) {
			visited[i] = true;
			backtrack(visited, i + 1, r - 1);
			visited[i] = false;
		}
	}

	static void print(boolean[] visited){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < C; i++) {
			if(visited[i]) sb.append(chars[i]);
		}
		System.out.println(sb);
	}

	static boolean isValid(boolean[] visited){
		int vowelCnt = 0;
		int consonantCnt = 0;
		Set<String> vowelSet = Set.of("a", "e", "i", "o", "u");
		for (int i = 0; i < C; i++) {
			if(visited[i]){
				if(vowelSet.contains(chars[i])) vowelCnt++;
				else consonantCnt++;

				if(vowelCnt >= 1 && consonantCnt >=2) return true;
			}
		}
		return false;
	}
	

}
