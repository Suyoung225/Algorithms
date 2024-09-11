import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
  int end;
  int time;
  public Edge(int end, int time) {
    this.end = end;
    this.time = time;
  }
}

public class Main {
  static int INF = Integer.MAX_VALUE;
  static int N, M, W;
  static List<Edge>[] edgeList;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int TC = Integer.parseInt(st.nextToken());
    while (TC-- > 0) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      edgeList = new ArrayList[N + 1];
      for (int i = 0; i <= N; i++) {
        edgeList[i] = new ArrayList<>();
      }
      // S, E, T 입력
      for (int i = 0; i < M + W; i++) {
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int time = Integer.parseInt(st.nextToken());
        if (i < M) { // 양방향 도로 정보
          edgeList[start].add(new Edge(end, time));
          edgeList[end].add(new Edge(start, time));
        } else { // 웜홀은 단방향
          edgeList[start].add(new Edge(end, -time));
        }
      }
      boolean isMinusCycle = false;
      for (int i = 1; i <= N; i++) {
        if (bellmanFord(i)) {
          System.out.println("YES");
          isMinusCycle = true;
          break;
        }
      }
      if (!isMinusCycle) {
        System.out.println("NO");
      }
    }
  }

  private static boolean bellmanFord(int start) {
    int[] dist = new int[N + 1];
    Arrays.fill(dist, INF);
    dist[start] = 0;

    // (정점 개수 - 1)만큼 최단 거리 초기화
    for (int i = 1; i < N; i++) {
      boolean isUpdated = false;
      for (int j = 1; j <= N; j++) {
        for (Edge edge : edgeList[j]) {
          if (dist[j] != INF && dist[edge.end] > dist[j] + edge.time) {
            dist[edge.end] = dist[j] + edge.time;
            isUpdated = true;
          }
        }
      }
      // 더이상 초기화 안 되면 종료 (음수 가중치 없음)
      if(!isUpdated) {
        return false;
      }
    }

    // 정점 개수번째에 최단 거리 업데이트 발생 -> 음수 가중치 존재 : return true
    for (int i = 1; i <= N; i++) {
      for (Edge edge : edgeList[i]) {
        if (dist[i] != INF && dist[edge.end] > dist[i] + edge.time) {
          return true;
        }
      }
    }
    return false;
  }

}
