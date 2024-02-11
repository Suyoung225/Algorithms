import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static Map<Integer, List<Integer>> likesMap = new HashMap<>();
	private static int[][] seats;
	private static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
	private static int[] dc = {0, 0, -1, 1}; // 상, 하, 좌, 우
	private static int N;
  
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		seats = new int[N+1][N+1];
    
		for (int i = 1; i <= N * N; i++) {
			st = new StringTokenizer(br.readLine());
			// 현재 순번의 학생
			int student = Integer.parseInt(st.nextToken());
			// 좋아하는 학생
			List<Integer> likes = new ArrayList<>();
      
			for (int j = 1; j <= 4; j++) {
				likes.add(Integer.parseInt(st.nextToken()));
			}
      
			// key: 학생, value: key 학생이 좋아하는 학생 리스트
			likesMap.put(student, likes);
			allocateSeat(student);
		}

		System.out.println(getScore());
	}

	private static void allocateSeat(int student) {
		List<int[]> availableSeats = getAdjacentLikeSeat(likesMap.get(student));

		if (availableSeats.size() == 1) {
			seats[availableSeats.get(0)[0]][availableSeats.get(0)[1]] = student;
			return;
		}

		availableSeats = getAdjacentVacantSeat(availableSeats);

		if (availableSeats.size() == 1) {
			seats[availableSeats.get(0)[0]][availableSeats.get(0)[1]] = student;
			return;
		}

		int[] seat = getSmallestNumSeat(availableSeats);
		seats[seat[0]][seat[1]] = student;
	}

	/**
	 * @param likes 좋아하는 학생 리스트
	 * @return 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸
	 */
	private static List<int[]> getAdjacentLikeSeat(List<Integer> likes) {
		// 인접 칸에 있는 좋아하는 학생 수
		int[][] likesNum = getAdjacentLikeNumArray(likes);

		// 가장 많은 인접 칸의 개수
		int max = 0;
		List<int[]> result = new ArrayList<>();
    
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(likesNum[r][c] >= max) {
					if(likesNum[r][c] > max){
						max = likesNum[r][c];
						result.clear();
					}
					result.add(new int[] {r, c});
				}
			}
		}
    
		return result;
	}

	/**
	 *
	 * @param likes 좋아하는 학생 리스트
	 * @return 인접 칸에 있는 좋아하는 학생 수
	 */
	private static int[][] getAdjacentLikeNumArray(List<Integer> likes){
		int[][] likesNum = new int[N+1][N+1];

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (seats[r][c] != 0) {
					likesNum[r][c] = -1;
					continue;
				}

				int cnt = 0;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (! (nr <= 0 || nr > N || nc <= 0 || nc > N )) {
						if(likes.contains(seats[nr][nc])) cnt++;
					}
				}
				likesNum[r][c] = cnt;
			}
		}
		return likesNum;
	}


	// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸
	private static List<int[]> getAdjacentVacantSeat(List<int[]> availableSeat) {
		int[] vacant = new int[availableSeat.size()];

		for (int i = 0; i < availableSeat.size(); i++) {
			for (int d = 0; d < 4; d++) {
				int nr = availableSeat.get(i)[0] + dr[d];
				int nc = availableSeat.get(i)[1] + dc[d];

				if (! (nr <= 0 || nr > N || nc <= 0 || nc > N )) {
					if(seats[nr][nc] == 0) {
						vacant[i] += 1;
					}
				}
			}
		}
    
		int max = Arrays.stream(vacant).max().orElse(0);
		List<int[]> result = new ArrayList<>();
    
		for (int i = 0; i < availableSeat.size(); i++) {
			if(vacant[i] == max){
				result.add(availableSeat.get(i));
			}
		}
		return result;
	}

	/**
	 *  3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로,
	 *  그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로
 	 */
	private static int[] getSmallestNumSeat(List<int[]> possibleSeat) {
		possibleSeat.sort((o1, o2) -> {
			if(o1[0] == o2[0]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		});
		return possibleSeat.get(0);
	}

	private static int getScore() {
		int score = 0;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				List<Integer> likes = likesMap.get(seats[r][c]);
				int cnt = 0;

				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (! (nr <= 0 || nr > N || nc <= 0 || nc > N )) {
						if(likes.contains(seats[nr][nc])) cnt++;
					}
				}

				switch (cnt) {
					case 1: score += 1; break;
					case 2: score += 10; break;
					case 3: score += 100; break;
					case 4: score += 1000; break;
					default: score += 0;
				}
			}
		}

		return score;
	}
}
