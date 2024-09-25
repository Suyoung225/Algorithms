import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // 초기화
    List<Integer>[] postLine = new ArrayList[N + 1];
    for (int i = 0; i <= N; i++) {
      postLine[i] = new ArrayList<>();
    }
    int[] indegree = new int[N + 1];

    while (M-- > 0) {
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int prev = 0;
      while (t-- > 0) {
        int cur = Integer.parseInt(st.nextToken());
        if (prev != 0) {
          postLine[prev].add(cur);
            indegree[cur]++;
        }
        prev = cur;
      }
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
      if (indegree[i] == 0) {
        q.add(i);
      }
    }

    List<Integer> ans = new ArrayList<>();
    while(!q.isEmpty()) {
      int cur = q.poll();
      ans.add(cur);
      for (Integer i : postLine[cur]) {
        indegree[i]--;
        if (indegree[i] == 0) {
          q.add(i);
        }
      }
    }

    if (ans.size() != N) {
      System.out.println("0");
    } else {
      ans.forEach(System.out::println);
    }

  }
}


