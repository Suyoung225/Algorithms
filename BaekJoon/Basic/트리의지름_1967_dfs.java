import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
  int num;
  int weight;

  Node(int num, int weight) {
    this.num = num;
    this.weight = weight;
  }
}

public class Main {
  static int n, maxWeight;
  static List<Node>[] tree;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    tree = new ArrayList[n + 1];
    for (int i = 0; i <= n; i++) {
      tree[i] = new ArrayList<>();
    }
    for (int i = 1; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int parent = Integer.parseInt(st.nextToken());
      int child = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      tree[parent].add(new Node(child, weight));
      tree[child].add(new Node(parent, weight));
    }
    for (int i = 1; i <= n; i++) {
      visited = new boolean[n + 1];
      visited[i] = true;
      dfs(i, 0);
    }
    System.out.println(maxWeight);
  }

  static void dfs(int start, int totalWeight) {
    maxWeight = Math.max(maxWeight, totalWeight);
    for (Node node : tree[start]) {
      if (!visited[node.num]) {
        visited[node.num] = true;
        dfs(node.num, totalWeight + node.weight);
      }
    }
  }
}
