import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	static int N, M, V;
	static List<Integer>[] adjacencyList;
	static boolean[] visited;
	static List<Integer> dfsList = new ArrayList<>();
	static List<Integer> bfsList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adjacencyList = new ArrayList[N + 1]; // 정점 번호 1부터 시작
		for (int i = 0; i <= N; i++) {
			adjacencyList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjacencyList[s].add(e);
			adjacencyList[e].add(s);
		}

		Arrays.stream(adjacencyList).forEach(i -> i.sort(null)); // 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문

		visited = new boolean[N + 1];
		DFS(V);
		dfsList.forEach(i -> System.out.print(i + " "));
		System.out.println();

		visited = new boolean[N+1];
		BFS(V);
		bfsList.forEach(i -> System.out.print(i + " "));
	}
	static void DFS(int v){
		if(visited[v]) return; // 인접 노드에 모두 방문되었을 때 리턴
		visited[v] = true;
		dfsList.add(v);
		for (Integer node : adjacencyList[v]) {
			if(!visited[node]) DFS(node);
		}
	}

	static void BFS(int v){
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		visited[v] = true;
		bfsList.add(v);
		while(!queue.isEmpty()){
			int now = queue.poll();
			for (Integer node : adjacencyList[now]) {
				if(!visited[node]){
					visited[node] = true;
					bfsList.add(node);
					queue.add(node);
				}
			}
		}

	}

}



