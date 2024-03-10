// https://school.programmers.co.kr/learn/courses/30/lessons/43238
class Solution {
    public long solution(int n, int[] times) {
        long s = 1;
        long e = 100000000000000L;
        while(s < e) {
            long time = (s + e)/2;
            if(isValid(time, n, times)) {
                e = time;
            } else {
                s = time + 1;
            }
        }
        return e;
    }
    
    private boolean isValid(long time, int n, int[] times) {
        long sum = 0;
        for (int t : times) {
            sum += time / t;
        }
        return sum >= n;
    }
}
