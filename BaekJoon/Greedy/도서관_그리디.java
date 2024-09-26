import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    st = new StringTokenizer(br.readLine());
    List<Integer> pos = new ArrayList<>(); // 양수 좌표에 위치한 책의 좌표
    List<Integer> neg = new ArrayList<>(); // 음수 좌표에 위치한 책의 좌표
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(st.nextToken());
      if (num > 0) {
        pos.add(num);
      } else {
        neg.add(num);
      }
    }
    pos.sort(Collections.reverseOrder());
    Collections.sort(neg);
    int ans = 0;
    for (int i = 0; i < pos.size(); i += M) {
      ans += 2 * pos.get(i);
    }
    for (int i = 0; i < neg.size(); i += M) {
      ans += 2 * Math.abs(neg.get(i));
    }

    if (neg.isEmpty()) {
      ans -= pos.get(0);
    } else if (pos.isEmpty()) {
      ans += neg.get(0);
    } else {
      ans -= Math.max(Math.abs(neg.get(0)), pos.get(0));

    }
    System.out.println(ans);

  }
}


