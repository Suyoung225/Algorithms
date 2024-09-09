import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int[][] map;
  static boolean[][][] visited;
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    visited = new boolean[N][M][2];
    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = line.charAt(j) - '0';
      }
    }
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0, 0, 1});
    visited[0][0][0] = true;

    while(!q.isEmpty()) {
      int[] cur = q.poll();
      int r = cur[0];
      int c = cur[1];
      int isDestroyed = cur[2];
      int step = cur[3];
      if (r == N - 1 && c == M - 1) {
        System.out.println(step);
        return;
      }
      for (int d = 0; d < 4; d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc][isDestroyed]) {
          if (map[nr][nc] == 0) {
            visited[nr][nc][isDestroyed] = true;
            q.add(new int[]{nr, nc, isDestroyed, step + 1});
          } else {
            // 아직 부순 이력이 없는 경우 부수기
            if (isDestroyed == 0) {
              visited[nr][nc][1] = true;
              q.add(new int[]{nr, nc, 1, step + 1});
            }
          }
        }

      }

    }

    System.out.println("-1");

  }

}
