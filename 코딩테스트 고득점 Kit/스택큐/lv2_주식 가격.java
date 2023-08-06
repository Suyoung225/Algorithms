import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
      // 스택에 주식 가격과 해당 주식의 순서를 저장한다.
        Stack<Stock> stack = new Stack<>();

        stack.push(new Stock(prices[0], 0));
        for (int i = 1; i < n; i++) {
          // 하락장에는 새로 들어온 가격보다 높은 주식 정보를 모두 pop, stack은 항상 오름차순으로 정렬됨
            while(!stack.isEmpty() && prices[i] < stack.peek().price){
                Stock stock = stack.pop();
             /* 새로 들어온 주식의 순서 (i) - stack에서 빼낸 가격이 하락한 주식이 들어온 순서 (stock.idx)가 
                 stack에서 빼낸 주식이 하락하지 않은 시간이므로 정답 배열에 저장 */
                ans[stock.idx] = i - stock.idx; 
            }
          // 상승장에는 바로 stack에 넣기, 하락장에는 위 연산을 마친 뒤에 새로 들어온 주식 정보 stack에 넣기
            stack.push(new Stock(prices[i], i));
        }
      // stack에 남은 주식 정보 
        while(!stack.isEmpty()){
            Stock stock = stack.pop();
            ans[stock.idx] = (n - 1) - stock.idx; // 마지막까지 하락하지 않았으므로 마지막 순서인 (n-1)에서 해당 주식이 들어온 순서를 빼줌
        }

        return ans;
    }

}

class Stock{
    int price;
    int idx;

    public Stock(int price, int idx) {
        this.price = price;
        this.idx = idx;
    }
}

