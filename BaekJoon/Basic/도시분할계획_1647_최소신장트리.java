import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
  int start, end, weight;
  public Edge(int start, int end, int weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }
}

public class Main {
  static int N, M;
  static int[] parent;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    parent = new int[N + 1];
    PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      pq.add(new Edge(s, e, w));
    }

    for (int i = 1; i <= N; i++) {
      parent[i] = i;
    }

    int ans = 0;
    int maxWeight = 0;
    while (!pq.isEmpty()) {
      Edge cur = pq.poll();
      if (find(cur.start) != find(cur.end)) {
        ans += cur.weight;
        maxWeight = Math.max(maxWeight, cur.weight);
        union(cur.start, cur.end);
      }
    }
    System.out.println(ans - maxWeight);
  }

  private static int find(int start) {
    if (parent[start] == start) return start;
    return parent[start] = find(parent[start]);
  }

  private static void union(int a, int b) {
    parent[find(b)] = find(a);
  }

}


