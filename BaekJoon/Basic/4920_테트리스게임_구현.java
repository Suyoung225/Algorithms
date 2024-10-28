import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static long max;
  static int[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 1;
    while (true) {
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      if (N == 0) break;
      // 초기화
      max = Long.MIN_VALUE;
      map = new int[N][N];
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      int d = 4;
      while (d-- > 0) {
        tetris1();
        tetris2();
        tetris3();
        tetris4();
        tetris5();
        rotate();
      }

      System.out.println(T++ + ". " + max);

    }

  }

  // ㅁㅁㅁㅁ
  static void tetris1() {
    long sum = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j <= N - 4; j++) {
        sum = 0;
        for (int k = j; k < j + 4; k++) {
          sum += map[i][k];
        }
        max = Math.max(max, sum);
      }
    }
  }

  // ㅁㅁ
  //   ㅁㅁ
  static void tetris2() {
    long sum = 0;
    for (int i = 0; i <= N - 2; i++) {
      for (int j = 0; j <= N - 3; j++) {
        sum = (map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2]);
        max = Math.max(max, sum);
      }
    }
  }

  // ㅁㅁㅁ
  //    ㅁ
  static void tetris3() {
    long sum = 0;
    for (int i = 0; i <= N - 2; i++) {
      for (int j = 0; j <= N - 3; j++) {
        sum = (map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2]);
        max = Math.max(max, sum);
      }
    }
  }

  // ㅁㅁㅁ
  //  ㅁ
  static void tetris4() {
    long sum = 0;
    for (int i = 0; i <= N - 2; i++) {
      for (int j = 0; j <= N - 3; j++) {
        sum = (map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]);
        max = Math.max(max, sum);
      }
    }
  }

  // ㅁㅁ
  // ㅁㅁ
  static void tetris5() {
    long sum = 0;
    for (int i = 0; i <= N - 2; i++) {
      for (int j = 0; j <= N - 2; j++) {
        sum = (map[i][j] + map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1]);
        max = Math.max(max, sum);
      }
    }
  }

  static void rotate() {
    int[][] temp = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        temp[i][j] = map[N - 1 - j][i];
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = temp[i][j];
      }
    }
  }

}
