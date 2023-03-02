import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int cnt = 0;
        int[] A = new int[N+1];
        for (int i = 2; i <= N; i++) {
            A[i] = i;
        }
        for (int i = 2; i <= N; i++) {
            if(A[i] == 0) continue;
            for (int j = i; j <= N; j += i) {
                if(A[j] != 0){
                    A[j] = 0;
                    cnt++;
                    if(cnt == K){
                        System.out.println(j);
                        return;
                    }
                }
            }
        }

    }
}
