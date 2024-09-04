import java.io.*;
import java.util.*;

public class Main {
  static int N, M, X;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    ArrayList<Node>[] adj = new ArrayList[N + 1];
    ArrayList<Node>[] r_adj = new ArrayList[N + 1];
    for (int i = 0; i < N + 1; i++) {
      adj[i] = new ArrayList<>();
      r_adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      adj[s].add(new Node(e, t));
      r_adj[e].add(new Node(s, t));
    }
    int[] d = daikstra(adj);
    int[] r_d = daikstra(r_adj);
    int ans = 0;

    for (int i = 1; i < N + 1; i++) {
      ans = Math.max(ans, d[i] + r_d[i]);
    }

    System.out.println(ans);
  }

  static int[] daikstra(ArrayList<Node>[] adj) {
    int[] d = new int[N + 1];
    Arrays.fill(d, Integer.MAX_VALUE);
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(X, 0));
    d[X] = 0;
    while (!pq.isEmpty()) {
      Node node = pq.poll();
      int cur = node.num;
      for (Node next : adj[cur]) {
        if (d[next.num] > d[cur] + next.time) {
          d[next.num] = d[cur] + next.time;
          pq.offer(new Node(next.num, d[next.num]));
        }
      }
    }

    return d;
  }

}

class Node implements Comparable<Node> {
  int num;
  int time;

  Node(int num, int time) {
    this.num = num;
    this.time = time;
  }

  @Override
  public int compareTo(Node o) {
    return this.time - o.time;
  }
}
