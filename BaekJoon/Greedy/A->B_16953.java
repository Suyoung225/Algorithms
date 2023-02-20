import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int cnt = 1;
        while(a != b){ 
            if(b == 0){ // 1/10 = 0 없으면 무한루프
                cnt = -1;
                break;
            }
            if(b % 2 == 0){
                b /= 2;
            }else if(b % 10 == 1){
                b /= 10;
            }else{
                cnt = -1;
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}
