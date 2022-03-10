import java.io.*;
import java.util.*;

/** Main_백준_20055_컨베이어벨트위의로봇_골드5_280ms*/
public class Main_백준_20055_컨베이어벨트위의로봇_골드5_280ms {
	
	private static class Box{
		int durability;
		boolean robot;
		public Box(int durability, boolean robot) {
			super();
			this.durability = durability;
			this.robot = robot;
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		/*
		 * 컨베이어 벨트는 매 턴마다 한칸씩 회전한다
		 * 로봇을 컨베이어 벨트 위 올리는 위치에 올릴 수 있으며
		 * 로봇을 올리거나, 로봇이 이동할 때 해당 칸의 내구도가 1씩 감소한다
		 * 진행 순서
		 * 1. 벨트가 각 칸 위에 있는 로봇들과 함께 회전
		 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 이동할 수 있다면 이동
		 * 3. 회전 및 이동 후 첫번째 칸의 내구도가 0이 아니라면 로봇을 올린다
		 * 4. 내구도가 0인 칸의 개수를 카운팅, K개 이상이면 과정 종료
		 * 
		 * -> 내구도 카운팅 변수를 전역으로 사용
		 * do while? 
		 * 현재칸, 다음칸을 확인해야 하니, index를 사용
		 */
		
		// 0~N-1 올리는 칸, N~2N-1 내리는 칸
		st = new StringTokenizer(br.readLine());
		Box[] box = new Box[N*2];
		for(int i=0, len=2*N; i<len; i++) {
			box[i] = new Box(Integer.parseInt(st.nextToken()), false);
		}
		
		int cnt = 0;
		int turn = 0;
		do {
			// 1. rotateBelt
			int temp = box[N*2-1].durability;
			for(int start=N*2-1, i=start; i>0; i--) {
				box[i].durability = box[i-1].durability;
				if(i<N) box[i].robot = box[i-1].robot;  // 올리는 칸이면
				else box[i].robot = false;              // 내리는 칸이면
			}
			box[0].durability = temp;
			box[0].robot = false;
			
			// 2. moveRobot
			// 올리는 칸 끝에 있는 로봇을 내린다
			box[N-1].robot = false;
			for(int start=N-2, i=start; i>=0; i--) {
				// 현재 조회할 칸에 로봇이 있고, 다음칸이 비어있으며 내구도가 0을 넘을 때 다음칸으로 이동
				if(box[i].robot && 
						(!box[i+1].robot && box[i+1].durability>0)) {
					// 현재 칸에 로봇을 없애고
					box[i].robot=false;
					// 다음 칸으로 로봇을 옮기고
					box[i+1].robot=true;
					// 내구도를 낮추는데, 내구도가 0이 된다면 내구도 0이된 칸 증가
					if(--box[i+1].durability==0) cnt++;
				}
			}
			
			// 3. 올리는 칸 첫칸에 로봇을 올리기
			if(box[0].durability>0) {
				box[0].robot = true;
				// 현재 칸의 내구성 체크
				if(--box[0].durability==0) cnt++;
			}
			turn++;
		} while(cnt<K); // 내구성이 0인게 K개보다 적은 경우에만 반복
		// 몇 턴 지났는지 출력
		System.out.println(turn);
	}
}
