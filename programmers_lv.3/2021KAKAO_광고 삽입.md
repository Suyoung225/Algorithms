## 코딩테스트 연습2021 KAKAO BLIND RECRUITMENT 광고 삽입

https://school.programmers.co.kr/learn/courses/30/lessons/72414

```java
import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = timeToSecond(play_time);
        int advTime = timeToSecond(adv_time);
        int[] watchList = new int[playTime + 1];
        for (String log : logs) {
            int start = timeToSecond(log.split("-")[0]);
            int end = timeToSecond(log.split("-")[1]);
            for (int i = start; i < end; i++) {
                watchList[i] += 1;
            }
        }

        long maxTime = 0;
        for (int i = 0; i <= advTime; i++) {
            maxTime += watchList[i];
        }
        long time = maxTime;
        int startTime = 0;
        int s = 0;
        for (int e = advTime; e <= playTime; e++) {
            time =  time - watchList[s] + watchList[e];
            if(time > maxTime){
                maxTime = time;
                startTime = s + 1;
            }
            s++;
        }
        return secondToTime(startTime);
    }
    
    int timeToSecond(String time){
        String[] str = time.split(":");
        return Integer.parseInt(str[0]) * 3600 + Integer.parseInt(str[1]) * 60 + Integer.parseInt(str[2]);
    }
    
    String secondToTime(int seconds){
        int hour = seconds / 3600;
        int minute = seconds % 3600 / 60;
        int second = seconds % 3600 % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
```
