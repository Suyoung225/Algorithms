import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int[] sum1 = new int[N];
        sum1[0] = (arr[0] == 1) ? 1 : -1;
        for (int i = 1; i < N; i++) {
            int add = (arr[i] == 1) ? 1 : -1;
            sum1[i] = sum1[i-1] + add;
        }
        int minus = 0;
        int max = 0;
        for (int i : sum1) {
            if(i < 0 && i < minus){
                minus = i;
            }else if(i > 0 && i > max){
                max = i;
            }
        }
        System.out.println(Math.abs(max - minus));

    }

}
