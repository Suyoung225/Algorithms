import java.util.*;

class Solution {
    int n;
    boolean[] visited;
    Map<String, List<Ticket>> map;
    String[] ans;
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        visited = new boolean[n];
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(tickets[i][0])) {
                map.put(tickets[i][0], new ArrayList<>());
            }
            map.get(tickets[i][0]).add(new Ticket(tickets[i][1], i));
        }
        for (List<Ticket> list : map.values()) {
            Collections.sort(list);
        }
        
        List<String> route = new ArrayList<>();
        route.add("ICN");
        ans = null;
        dfs("ICN", 0, route);
        
        return ans;
    }
    
    void dfs(String airport, int step, List<String> route) {
        if (step == n) {
            ans = route.toArray(new String[0]);
            return;
        }
        
        List<Ticket> tickets = map.get(airport);
        if (tickets == null) return; 
        
        for (int i = 0 ; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (!visited[ticket.idx]) {
                visited[ticket.idx] = true;
                route.add(ticket.arrival);
                dfs(ticket.arrival, step + 1, route);
                if (ans != null) return; 
                route.remove(route.size() - 1);
                visited[ticket.idx] = false;
            }
        }
    }
}

class Ticket implements Comparable<Ticket> {
    String arrival;
    int idx;
    public Ticket(String arrival, int idx) {
        this.arrival = arrival;
        this.idx = idx;
    }

    @Override
    public int compareTo(Ticket o) {
        return this.arrival.compareTo(o.arrival);
    }
}
