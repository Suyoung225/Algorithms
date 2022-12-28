# 슬라이딩 윈도우

#### DNA 비밀번호
https://www.acmicpc.net/problem/12891

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static char[] chars;
    static Map<Character, Integer> hm;
    static int[] limit = new int[4];;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();
        chars = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 문자열
        for (int i = 0; i < P; i++) insert(chars[i]);
        validation();

        for (int i = P; i < S; i++) {
            insert(chars[i]);
            remove(chars[i-P]);
            validation();
        }
        System.out.println(cnt);

    }

    private static void insert(char c) {
        hm.put(c,hm.getOrDefault(c,0)+1);
    }
    private static void remove(char c){
        hm.put(c,hm.getOrDefault(c,0)-1);
    }
    private static void validation(){
        char[] dna = {'A', 'C', 'G', 'T'};
        for (int i = 0; i < 4; i++) {
            if(limit[i] == 0) continue;
            if(!hm.containsKey(dna[i])) return;
            if(hm.get(dna[i]) < limit[i]) return;
        }
        cnt++;
    }
}

```
