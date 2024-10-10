import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M, safeZone;
  static char[][] map;
  static int[][] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new char[N][M];
    for (int i = 0; i < N; i++) {
      int j = 0;
      for (char c : br.readLine().toCharArray()) {
        map[i][j++] = c;
      }
    }

    visited = new int[N][M];
    int idx = 0; // 구역 번호
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (visited[i][j] == 0) {
          visited[i][j] = ++idx;
          dfs(i, j, idx);
        }
      }
    }

    System.out.println(safeZone);
  }

  static void dfs(int r, int c, int idx) {
    int nr = r;
    int nc = c;
    switch (map[r][c]) {
      case 'D':
        nr = r + 1; break;
      case 'U' :
        nr = r - 1; break;
      case 'R':
        nc = c + 1; break;
      case 'L':
        nc = c - 1; break;
      default:
        return;
    }
    if (nr >=0 && nr < N && nc >= 0 && nc < M) {
      // 순환
      if (visited[nr][nc] == idx) {
        safeZone++;
        return;
      }
      if (visited[nr][nc] == 0) {
        visited[nr][nc] = idx;
        dfs(nr, nc, idx);
      }
    }
  }

}
