import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    List<Integer>[] prior = new ArrayList[N + 1];
    for (int i = 0; i < N + 1; i++) {
      prior[i] = new ArrayList<>();
    }
    int[] degree = new int[N + 1];
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      prior[a].add(b);
      degree[b]++;
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 1; i <= N; i++) {
      if (degree[i] == 0) {
        pq.offer(i);
      }
    }
    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      int cur = pq.poll();
      sb.append(cur).append(" ");
      for (Integer i : prior[cur]) {
        degree[i]--;
        if (degree[i] == 0) {
          pq.offer(i);
        }
      }
    }
    System.out.println(sb);
  }

}
