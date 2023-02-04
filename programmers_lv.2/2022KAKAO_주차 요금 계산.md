## 코딩테스트 연습 2022 KAKAO BLIND RECRUITMENT 주차 요금 계산

https://school.programmers.co.kr/learn/courses/30/lessons/92341

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        Map<String, ArrayList<ParkInfo>> map = new TreeMap<>();
        // key: 차량번호, value: in, out 시간
        for (String r : records) {
            String[] record = r.split(" ");
            if(record[2].equals("IN")){
                if(!map.containsKey(record[1])) {
                    ArrayList<ParkInfo> list = new ArrayList<>();
                    list.add(new ParkInfo(record[0], "23:59"));
                    map.put(record[1], list);
                }else{
                    ArrayList<ParkInfo> list = map.get(record[1]);
                    list.add(new ParkInfo(record[0], "23:59"));
                }
            }else {
                ArrayList<ParkInfo> list = map.get(record[1]);
                list.get(list.size()-1).outTime = record[0];
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (String s : map.keySet()) {
            int time = 0;
            for (ParkInfo parkInfo : map.get(s)) {
                time += accumulatedTime(parkInfo.inTime, parkInfo.outTime);
            }
            ans.add(totalFee(time,fees));
        }

        return ans;
    }

    int accumulatedTime(String inTime, String outTime){
        String[] in = inTime.split(":");
        String[] out = outTime.split(":");
        return (Integer.parseInt(out[0]) - Integer.parseInt(in[0])) * 60
                + (Integer.parseInt(out[1]) - Integer.parseInt(in[1]));
    }

    int totalFee(int time, int[] fees){
        int total = fees[1];
        if(time > fees[0]){
            int additionalTime = time - fees[0];
            total += (additionalTime % fees[2] == 0) ? fees[3] * (additionalTime / fees[2]) :  fees[3] * (additionalTime / fees[2] + 1);
        }
        return total;
    }
}

class ParkInfo{
    String inTime, outTime;

    public ParkInfo(String inTime, String outTime) {
        this.inTime = inTime;
        this.outTime = outTime;
    }
}
```
