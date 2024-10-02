import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int[] diff = Arrays.copyOf(diffs, diffs.length);
        // 가장 큰 수 구하기
        Arrays.sort(diffs);
        // 이진 탐색
        int s = 1;
        int e = diffs[diffs.length - 1];
        while(s <= e) {
            int m = (s + e) / 2;
            if (isInTime(m, diff, times, limit)) {
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return s;
    }
    
    private boolean isInTime(int level, int[] diff, int[] times, long limit) {
        // 총 소요 시간, 어떤 레벨이든 diffs[0] = 1는 통과하므로
        long totalTime = times[0]; 
        int prevTime = times[0]; 
        for (int i = 1; i < diff.length; i++) {
            totalTime += Math.max(0, diff[i] - level) * (times[i] + prevTime) + times[i];
            // limit 초과하면 실패
            if (totalTime > limit) return false;
            // 이전 시간 업데이트
            prevTime = times[i];
        }
        return true;
    }
}
