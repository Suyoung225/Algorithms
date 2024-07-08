import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int[] dpUp = new int[N + 1]; // dpUp[i] : i번째 수까지의 오름차순 수열 개수
        int[] dpDown = new int[N + 1]; // dpDown[i] : N-1부터 i번째 수까지의 내림차순 수열 개수
        dpUp[0] = 1;
        dpDown[N - 1] = 1;

        for (int i = 1; i < N; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dpUp[j] + 1);
                }
            }
            dpUp[i] = max;
        }

        for (int i = N - 2; i >= 0; i--) {
            int max = 1;
            for (int j = i + 1; j < N; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dpDown[j] + 1);
                }
            }
            dpDown[i] = max;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dpUp[i] + dpDown[i] - 1);
        }

        System.out.println(ans);
    }
}
