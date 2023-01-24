## 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 문자열 압축

https://school.programmers.co.kr/learn/courses/30/lessons/60057?language=java


#### 첫 풀이 - 문자열 길이 계산 

```java
public int solution(String s) {
    int min = s.length();
    for (int i = 1; i < s.length()/2 + 1; i++) {
        String[] strings = new String[(s.length()%i == 0)? s.length()/i : s.length()/i + 1];
        int idx = 0;
        for (int j = 0; j < strings.length; j++) {
            strings[j] = (j == strings.length - 1)? s.substring(idx) : s.substring(idx, idx+i);
            idx += i;
        }
        int cnt = 1;
        int length = strings[0].length();
        for (int j = 1; j < strings.length; j++) {
            if(strings[j-1].equals(strings[j])){
                cnt++;
                if(cnt <= 2) length++;
                else if(cnt % 10 == 0) length++;
            }else{
                cnt = 1;
                length += strings[j].length();
            }
        }
        if(length < min) min = length;
    }

    return min;
}

```

#### 재귀함수 이용, 직접 문자열 만들기

```java
class Solution {

    public int solution(String s) {
        int min = s.length();
        for (int i = 1; i < s.length() / 2 + 1; i++) {
            min = Math.min(min, words(s, i, 1).length());
        }
        return min;
    }
    
    StringBuilder words(String s, int n, int cnt){
        if(s.length() < n) return new StringBuilder(s);
        StringBuilder sb = new StringBuilder();
        String before = s.substring(0,n);
        String now = s.substring(n);
        if(!now.startsWith(before)){
            if(cnt == 1) return sb.append(before).append(words(now, n, 1));
            return sb.append(cnt).append(before).append(words(now, n, 1));
        }
        return sb.append(words(now, n, cnt + 1));
    }
}
```
