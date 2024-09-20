import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  static int INF = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      int j = 0;
      for (char c : line.toCharArray()) {
        map[i][j++] = c - '0';
      }
    }

    int[][] dist = new int[N][M];
    for (int i = 0; i < N; i++) {
      Arrays.fill(dist[i], INF);
    }

    dist[0][0] = 0;
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0, 0});
    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int r = cur[0];
      int c = cur[1];
      if (r == N - 1 && c == M - 1) {
        continue;
      }
      for (int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
          if (dist[nr][nc] > dist[r][c] + map[nr][nc]) {
            dist[nr][nc] = dist[r][c] + map[nr][nc];
            q.add(new int[]{nr, nc});
          }
        }
      }
    }

    System.out.println(dist[N - 1][M - 1]);
  }

}


