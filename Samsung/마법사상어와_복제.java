// https://www.acmicpc.net/problem/23290

import java.util.*;

public class Main {
	static int M, S, sx, sy, idx;
	// key: 각 격자 번호: 1 ~ 16, value: 물고기 방향
	static Map<Integer, List<Integer>> board = new HashMap<>();
	static Map<Integer, List<Integer>> snapshot = new HashMap<>();
	static int[][] blood = new int[5][5];
	static int[] dfx = {0, -1, -1, -1, 0, 1, 1, 1}; // 물고기 방향: 좌부터 반시계 방향
	static int[] dfy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dsx = {-1, 0, 1, 0}; // 상어 방향: 상, 좌, 하, 우
	static int[] dsy = {0, -1, 0, 1};
	static int[][] dshark = new int[64][3];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		S = sc.nextInt();
		
		for (int i = 1; i <= 16; i++) {
			snapshot.put(i, new ArrayList<>());
			board.put(i, new ArrayList<>());
		}
		
		// 초기값 세팅
		for (int i = 0; i < M; i++) {
			int fx = sc.nextInt();
			int fy = sc.nextInt();
			int d = sc.nextInt() - 1;
			int num = getCoordToNum(fx, fy);
			snapshot.get(num).add(d);
		}
		
		sx = sc.nextInt();
		sy = sc.nextInt();
		
		getdshark(0, new int[3]);
		
		// 연습 횟수만큼
		for (int time = 1; time <= S; time++) {
			// 2. 물고기 한 칸 이동
			moveAllFishes(time);
			
			// 3. 상어 연속 세 칸 이동
			moveShark(time);
			
			// 5. 복제 완료
			for (int j = 1; j <= 16; j++) {
				for(Integer fish: board.get(j)) {
					snapshot.get(j).add(fish);
				}
				board.get(j).clear();
			}
		}
		
		// 최종 물고기 개수
		int ans = 0;
		for (int j = 1; j <= 16; j++) {
			ans += snapshot.get(j).size();
		}
		System.out.println(ans);
	}
	
	static void moveShark(int time) {
		int max = -2;
		int[] bestDirection = Arrays.copyOf(dshark[0], 3);
		for (int i = 0; i < 64; i++) {
			if (removeFishCnt(dshark[i]) > max) {
				max = removeFishCnt(dshark[i]);
				bestDirection = Arrays.copyOf(dshark[i], 3);
			}
		}
		removeFishes(bestDirection, time);
	}
	
	static int removeFishCnt(int[] directions) {
		int x = sx;
		int y = sy;
		int cnt = 0;
		Set<Integer> nums = new HashSet<>();
		for (int i = 0; i < 3; i++) {
			x = x + dsx[directions[i]];
			y = y + dsy[directions[i]];
			if (!(x >= 1 && x <= 4 && y >= 1 && y <= 4)) return -1;
			int num = getCoordToNum(x, y);
			nums.add(num);
		}
		for (Integer num : nums) cnt += board.get(num).size();
		return cnt;
	}
	
	static void removeFishes(int[] directions, int time) {
		for (int i = 0; i < 3; i++) {
			sx = sx + dsx[directions[i]];
			sy = sy + dsy[directions[i]];
			int num = getCoordToNum(sx, sy);
			if (!board.get(num).isEmpty()) {
				blood[sx][sy] = time;
				board.get(num).clear();
			}
		}
		
	}
	
	static void getdshark(int depth, int[] number) {
		if (depth == 3) {
			dshark[idx] = Arrays.copyOf(number, 3);
			idx++;
			return;
		}
		for (int i = 0; i < 4; i++) {
			number[depth] = i;
			getdshark(depth + 1, number);
		}
	}
	
	static void moveAllFishes(int time) {
		for (int i = 1; i <= 16; i++) {
			List<Integer> fishes = snapshot.get(i);
			for(Integer fishD : fishes) {
				boolean moved = false;
				for (int j = 0; j <= 7; j++) {
					if (!fishMovable(i, fishD, time)) {
						fishD = (fishD + 7) % 8;
					} else {
						moved = true;
						board.get(moveFish(i, fishD)).add(fishD);
						break;
					}
				}
				if(!moved) {
					board.get(i).add(fishD);
				}
			}
		}

	}
	
	static boolean fishMovable (int num, int fishD, int time) {
		int[] before = getNumToCoord(num);
		int x = before[0] + dfx[fishD];
		int y = before[1] + dfy[fishD];
		return (x >= 1 && x <= 4 && y >= 1 && y <= 4 && // 격자 안으로
				!(x == sx && y == sy) && // 상어 있는 칸
				(blood[x][y] == 0 || (time - blood[x][y]) >= 3)); // 물고기 냄새 나는 칸
	}
	
	static int moveFish(int num, int fishD) {
		int[] before = getNumToCoord(num);
		int x = before[0] + dfx[fishD];
		int y = before[1] + dfy[fishD];
		return getCoordToNum(x, y);
	}
	
	static int getCoordToNum(int x, int y) {
		return 4 * (x - 1) + y;
	}
	
	static int[] getNumToCoord(int num) {
		int x = (num - 1) / 4 + 1;
		int y = num - (x - 1) * 4;
		return new int[] {x, y};
	}
	
}
