import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = n / 5;
        int leftOver = n - cnt * 5;
        while(cnt >= 0){
            if(leftOver % 2 == 0){
                cnt += leftOver / 2;
                break;
            }else{
                cnt--;
                leftOver = n - cnt * 5;
            }
        }
        System.out.println(cnt);
    }
}
