import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    long[][] dist = new long[N + 1][N + 1];
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= N; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else {
          dist[i][j] = Integer.MAX_VALUE;
        }
      }
    }
    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      // a -> b
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      dist[a][b] = 1;
    }

    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          if (i == j) continue;
          if (dist[i][k] + dist[k][j] < dist[i][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
          }
        }
      }
    }


    int ans = 0; // 자신의 키가 몇 번째인지 알 수 있는 학생 수 (정답)
    int[] arr = new int[N + 1]; // 자신의 앞 + 뒤의 학생 수 합
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        // i보다 뒤에 있는 학생 수
        if (dist[i][j] != Integer.MAX_VALUE && dist[i][j] != 0) {
          arr[i]++;
        }
        // i보다 앞에 있는 학생 수
        if (dist[j][i] != Integer.MAX_VALUE && dist[j][i] != 0) {
          arr[i]++;
        }
      }
      // 앞 + 뒤의 학생 수 합이 자신을 제외한 N-1명인 경우 자신의 위치 확인 가능
      if (arr[i] == N - 1) {
        ans++;
      }
    }

    System.out.println(ans);
  }

}
