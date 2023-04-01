class Solution {
    static String result = "";
    static int N, M;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        if(!isPossible(x, y, r, c, k)) return "impossible";
        mazeEscape(x, y, r, c, k);
        return result;
    }
    boolean isPossible(int x1, int y1, int x2, int y2, int k){
        int dist = Math.abs(x2 - x1) + Math.abs(y2 - y1); // 최소 거리
        if(k < dist) return false;
        return (k - dist) % 2 == 0;
    }
    void mazeEscape(int sx, int sy, int ex, int ey, int k){
        if(k == 0) return;
        if((sx + 1) <= N && isPossible(sx + 1, sy, ex, ey, k - 1)){
            result += "d";
            mazeEscape(sx + 1, sy, ex, ey, k - 1);
        }
        else if((sy - 1) >= 1 && isPossible(sx, sy - 1, ex, ey, k - 1)){
            result += "l";
            mazeEscape(sx, sy - 1, ex, ey, k - 1);
        }
        else if((sy + 1) <= M && isPossible(sx, sy + 1, ex, ey, k - 1)){
            result += "r";
            mazeEscape(sx, sy + 1, ex, ey, k - 1);
        }
        else{
            result += "u";
            mazeEscape(sx - 1, sy, ex, ey, k - 1);
        }
    }
}
