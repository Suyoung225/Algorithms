import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] gear = new int[4][8];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			int j = 0;
			for (String s : input.split("")) {
				gear[i][j] = Integer.parseInt(s);
				j++;
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			move(num, direction);
		}

		System.out.println(calculateScore());
	}

	// clockwise: 1이면 시계방향 회전, -1이면 반시계방향, gearNum: 회전할 톱니바퀴 번호
	static void move(int gearNum, int clockwise){
		// 접점 1
		int right1 = gear[0][2];
		int left2 = gear[1][6];
		boolean contact1 = right1 != left2;
		// 접점 2
		int right2 = gear[1][2];
		int left3 = gear[2][6];
		boolean contact2 = right2 != left3;
		// 접점 3
		int right3 = gear[2][2];
		int left4 = gear[3][6];
		boolean contact3 = right3 != left4;

		rotate(gearNum, clockwise);

		int counterClockwise = (clockwise == 1) ? -1 : 1;

		if(gearNum == 1 && contact1){
			rotate(2, counterClockwise);
			if(contact2){
				rotate(3, clockwise);
				if(contact3){
					rotate(4, counterClockwise);
				}
			}
		}

		if(gearNum == 2){
			if(contact1){
				rotate(1, counterClockwise);
			}
			if(contact2){
				rotate(3, counterClockwise);
				if(contact3){
					rotate(4, clockwise);
				}
			}
		}

		if(gearNum == 3){
			if(contact3){
				rotate(4, counterClockwise);
			}
			if(contact2){
				rotate(2, counterClockwise);
				if(contact1){
					rotate(1, clockwise);
				}
			}
		}

		if(gearNum == 4 && contact3){
			rotate(3, counterClockwise);
			if(contact2){
				rotate(2, clockwise);
				if(contact1){
					rotate(1, counterClockwise);
				}
			}
		}

	}

	// clockwise: 1이면 시계방향 회전, -1이면 반시계방향, gearNum: 회전할 톱니바퀴 번호
	static void rotate(int gearNum, int clockwise){
		int[] arr = gear[gearNum - 1].clone();
		if(clockwise == 1){
			for (int i = 0; i < 8; i++) {
				int newIdx = (i + 7) % 8;
				gear[gearNum - 1][i] = arr[newIdx];
			}
		}else{
			for (int i = 0; i < 8; i++) {
				int newIdx = (i + 1) % 8;
				gear[gearNum - 1][i] = arr[newIdx];
			}
		}
	}


	static int calculateScore(){
		int score = 0;
		if(gear[0][0] == 1) score += 1;
		if(gear[1][0] == 1) score += 2;
		if(gear[2][0] == 1) score += 4;
		if(gear[3][0] == 1) score += 8;
		return score;
	}


}

