class Solution {
    static class Order {
        int menuTime;
        int orderTime;
        public Order (int menuTime, int orderTime) {
            this.menuTime = menuTime;
            this.orderTime = orderTime;
        }
    }
    public int solution(int[] menu, int[] order, int k) {
        Order[] orders = new Order[order.length];
        for (int i = 0; i < order.length; i++) {
            orders[i] = new Order(menu[order[i]], i * k);
        }
        int time = 0;
        int lastOrder = 0;
        int ans = 1;
        for (int i = 0; i < order.length; i++) {
            if (orders[i].orderTime >= time) {
                time = orders[i].orderTime;
            } 
            
            time += orders[i].menuTime;
            while (lastOrder < order.length &&
                   orders[lastOrder].orderTime < time) {
                lastOrder++;
            }
            ans = Math.max(ans, lastOrder - i);
        }
        
        return ans;
    }
}
