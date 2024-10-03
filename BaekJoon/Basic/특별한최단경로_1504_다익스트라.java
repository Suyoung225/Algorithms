import java.io.*;
import java.util.*;

class Node {
	int num;
	int cost;
	Node(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}
public class Main {
	static int N, E;
	static List<Node>[] adj;
	static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		E = Integer.parseInt(st.nextToken()); 
		// 간선 리스트 초기화
		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		// 간선 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); 
			int b = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
		// 특정 경로 입력
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()); 
		int v2 = Integer.parseInt(st.nextToken());
		
		// v1, v2부터 각 노드까지의 최단거리 구하기
		int[] dist1 = daikstra(v1);
		int[] dist2 = daikstra(v2);
		
		if (dist1[v2] == INF) {
			System.out.println("-1");
			return;
		}
		
		// route1: 1 -> V1 -> V2 -> N
		// (V1 -> 1) + (V1 -> V2) + (V2 -> N)
		int route1 = (dist1[1] == INF || dist2[N] == INF) ? INF : dist1[1] + dist1[v2] + dist2[N];
		// 1 -> V2 -> V1 -> N
		// (V2 -> 1) + (V1 -> V2) + (V1 -> N)
		int route2 = (dist2[1] == INF || dist1[N] == INF) ? INF : dist2[1] + dist1[v2] + dist1[N];
		int ans = (route1 == INF && route2 == INF) ? -1 : Math.min(route1, route2);
		System.out.println(ans);
    }
	
	static int[] daikstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		// cost 기준 내림차순
		PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> y.cost - x.cost);
		pq.add(new Node(start, 0));
		dist[start] = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node next : adj[cur.num]) {
				if (dist[next.num] > dist[cur.num] + next.cost) {
					dist[next.num] = dist[cur.num] + next.cost;
					pq.add(new Node(next.num, dist[next.num]));
				}
			}
		}
		return dist;
	}
	


}
