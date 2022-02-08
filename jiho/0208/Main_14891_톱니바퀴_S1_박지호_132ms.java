package practice;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴_S1_박지호_132ms {

	static int[][] T1;

	public static void turn(int T_num, int way) {
		left(T_num - 1, -way); // 회전 여부 결정
		right(T_num + 1, -way); // 회전 여부 결정
		rotate(T_num, way); // 회전
	}

	public static void left(int T_num, int way) {
		if (T_num < 0) {
			return;
		}

		if (T1[T_num][2] != T1[T_num + 1][6]) { // 왼쪽의 바퀴를 돌렸을 때 그 바퀴의 왼쪽이 돌아가는지 검사
			left(T_num - 1, -way);// 여기 들어온 순간 이미 돌리기로 한 바퀴의 왼쪽 바퀴가 선택된거라고 생각
			rotate(T_num, way); // 돌릴 바퀴가 시계로 돌면 주변 바퀴는 반대로 돈다 => -way
		}

	}

	public static void right(int T_num, int way) {
		if (T_num > 3) {
			return;
		}

		if (T1[T_num][6] != T1[T_num - 1][2]) { // 왼쪽의 바퀴를 돌렸을 때 그 바퀴의 왼쪽이 돌아가는지 검사
			right(T_num + 1, -way);// 여기 들어온 순간 이미 돌리기로 한 바퀴의 왼쪽 바퀴가 선택된거라고 생각
			rotate(T_num, way);
		}
	}

	public static void rotate(int T_num, int way) {
		if (way == 1) {
			int temp = T1[T_num][7];

			for (int i = 7; i > 0; i--) {
				T1[T_num][i] = T1[T_num][i - 1];
			}

			T1[T_num][0] = temp;

		} else {
			int temp = T1[T_num][0];

			for (int i = 0; i < 7; i++) {
				T1[T_num][i] = T1[T_num][i + 1];
			}

			T1[T_num][7] = temp;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StringBuilder();
		T1 = new int[4][8];

		for (int i = 0; i < 4; i++) { // 톱니바퀴 배열 입력
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				T1[i][j] = line.charAt(j) - '0';
			}
		}

		int spin = Integer.parseInt(br.readLine());
		for (int i = 0; i < spin; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int T_num = Integer.parseInt(st.nextToken()); // 돌릴 바퀴
			int way = Integer.parseInt(st.nextToken()); // 방향

			turn(T_num - 1, way);
		}

		int score = 0;
		for (int i = 0; i < 4; i++) {
			score += T1[i][0] * Math.pow(2, i);
		}

		System.out.println(score);

	}
}

//고려해야될 수
//1번 바퀴의 3번 이 = 2번 바퀴의 7번
//2번 바퀴의 3번 이 = 3번 바퀴의 7번 
//3번 바퀴의 3번 이 = 4번 바퀴의 7번 
//
//우선 4줄 읽어와서 각 배열에 저장
//N = 0 / S = 1 / 12시 방향 이 부터 시작
//
//0번지부터 시작해서 따지면
//배열의 2번째 요소와 6번째 요소를 비교한다
//
//n번 회전한다 입력받고
//m번째 톱니를 돌리도록 입력받고
//시계방향 1 / 반시계방향 -1
//
//left / right 메서드를 둬서 idx를 넣어서 돌린다
//만약 idx가 1보다 작거나 3보다 클 경우 return 으로 안돌아가게 조치하면 됨
