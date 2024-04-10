// https://www.acmicpc.net/problem/17471

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] population;
	static List<Integer>[] adjacencyList;
	static int minimunDiff = Integer.MAX_VALUE;
	static boolean dividable = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		population = new int[N + 1];
		adjacencyList = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			adjacencyList[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int j = 0; j < m; j++) {
				adjacencyList[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 1; i <= N / 2; i++) {
			divide(i, 0, 1, new boolean[N + 1]); // i개와 N-i개로 나누기
		}
		if (!dividable) {
			System.out.println("-1");
		} else {
			System.out.println(minimunDiff);
		}
	}
	
	static void divide(int end, int depth, int idx, boolean[] divided) {
		if (depth == end) {
			if (checkValid(divided)) {
				dividable = true;
				minimunDiff = Math.min(calculateDiff(divided), minimunDiff);
			}
			return;
		}
		for (int i = idx; i <= N; i++) {
			divided[i] = true;
			divide(end, depth + 1, i + 1, divided);
			divided[i] = false;
		}
	}
	
	static boolean checkValid(boolean[] divided) {
		int groupB = 0;
		for (int i = 2; i <= N; i++) {
			if(divided[1] != divided[i]) {
				groupB = i;
				break;
			}
		}
		
		return checkConnected(divided, 1) && checkConnected(divided, groupB);
	}
	
	static boolean checkConnected(boolean[] divided, int group) {
		boolean[] visited = new boolean[N+1];
		boolean district = divided[group];
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(group);
		visited[group] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			cnt++;
			for (Integer i : adjacencyList[now]) {
				if (!visited[i] && divided[i] == district) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		int districtCnt = 0;
		for (int i = 1; i <= N; i++) {
			if (divided[i] == district) districtCnt++;
		}
		return districtCnt == cnt;
	}
	
	
	static int calculateDiff(boolean[] divided) {
		int groupA = 0;
		int groupB = 0;
		for (int i = 1; i <= N; i++) {
			if (divided[i]) groupA += population[i];
			else groupB += population[i];
		}
		return Math.abs(groupA - groupB);
	}
}
