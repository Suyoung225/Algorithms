class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = getTime(video_len);
        int now = getTime(pos);
        int opStart = getTime(op_start);
        int opEnd = getTime(op_end);
        // 오프닝 구간 건너뛰기
        if (now >= opStart && now <= opEnd) {
            now = opEnd;
        }
        for (String command : commands) {
            if (command.equals("prev")) {
                now -= 10;
                if (now < 0) now = 0;
            } else {
                now += 10;
                if (now > videoLen) now = videoLen;
            }
            
            // 오프닝 구간 건너뛰기
            if (now >= opStart && now <= opEnd) {
                now = opEnd;
            }
        }
        
        return timeToString(now);
    }
    
    int getTime(String str) {
        int minutes = Integer.parseInt(str.split(":")[0]);
        int seconds = Integer.parseInt(str.split(":")[1]);
        return minutes * 60 + seconds;
    }
    
    String timeToString(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        StringBuilder sb = new StringBuilder();
        if (minutes < 10) sb.append(0);
        sb.append(minutes).append(":");
        if (seconds < 10) sb.append(0);
        sb.append(seconds);
        return sb.toString();
    }
}
