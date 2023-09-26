import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	static List<Integer>[] party;

	static List<Integer> truth = new ArrayList<>(); // 진실을 아는 사람의 번호
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 초기화
		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}

		// 진실을 아는 파티원 입력
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for (int i = 0; i < num; i++) {
			truth.add(Integer.parseInt(st.nextToken()));
		}

		// 각 파티 파티원 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 각 파티 대표 노드 설정
		for (int i = 0; i < M; i++) {
			int before = party[i].get(0);
			for (int j = 1; j < party[i].size(); j++) {
				union(before, party[i].get(j));
			}
		}

		// 진실을 아는 사람이 속한 파티 대표 노드
		Set<Integer> truthSet = new HashSet<>();
		for (Integer t : truth) {
			truthSet.add(find(t));
		}

		// 각 파티의 대표노드와 진실을 아는 사람이 속한 파티 대표 노드와 비교
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			if(!truthSet.contains(find(party[i].get(0)))) cnt++;
		}

		System.out.println(cnt);
	}

	static void union(int a, int b) {
		parent[find(b)] = find(a);
	}

	static int find(int a){
		if(a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}


}

