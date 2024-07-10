import java.io.*;
import java.util.*;

public class Main {
    static int n, hx, hy, fx, fy;
    static int[][] store;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        while (t --> 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 편의점 개수
            store = new int[n][2];
            // 집 위치
            st = new StringTokenizer(br.readLine());
            hx = Integer.parseInt(st.nextToken());
            hy = Integer.parseInt(st.nextToken());
            // 편의점 위치
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                store[i][0] = Integer.parseInt(st.nextToken());
                store[i][1] = Integer.parseInt(st.nextToken());
            }
            // 페스티벌 위치
            st = new StringTokenizer(br.readLine());
            fx = Integer.parseInt(st.nextToken());
            fy = Integer.parseInt(st.nextToken());
            System.out.println((bfs()) ? "happy" : "sad");
        }
    }

    static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(new int[]{hx, hy});
        while(!q.isEmpty()) {
            int[] now = q.poll();
            // 현재 위치 (시작점 or 편의점)와 도착 위치 사이 거리
            if (Math.abs(now[0] - fx) + Math.abs(now[1] - fy) <= 20 * 50) {
                return true;
            }
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    // i 번째 편의점과 현재 위치 사이 거리
                    if(Math.abs(now[0] - store[i][0]) + Math.abs(now[1] - store[i][1]) <= 20 * 50) {
                        visited[i] = true;
                        q.add(new int[]{store[i][0], store[i][1]});
                    }
                }
            }
        }
        return false;
    }
}
