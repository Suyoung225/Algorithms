// https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/901.%20Online%20Stock%20Span.md
class StockSpanner {
    Stack<int[]> stack;

    public StockSpanner() {
        this.stack = new Stack<>();
    }
    
    public int next(int price) {
        int cnt = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            cnt += stack.pop()[1];
        }
        stack.push(new int[]{price, cnt});
        return cnt;
    }
}
