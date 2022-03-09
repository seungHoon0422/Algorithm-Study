package BOJ0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


/**
벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.

	문제분석 : 기본적인 시뮬레이션 문제
	 처음에는 deque를 사용해서 처리하려했지만, 인덱스 접근이 자주 필요해 배열로 구현
	 belt클래스를 생성하여 로봇이 있는지 여부와 내구도를 저장
	 
	 벨트 이동 -> 로봇 이동 -> 내구도 확인
	 순서로 코드 진행
	 벨트 이동 : 모든밸트를 한칸씩 이동 한다 => 이 과정에서 덱의 효율이 좋을 것 같았다.
	 로봇 이동 : 현재 칸에 로봇이 있고, 다음칸에 로봇이 업고, 내구도가 있는 경우 로봇 이동
	 		처음 칸에 로봇을 올리는 동작도 같이 진행
	 		
*
*/
public class Main_20055_컨베이어벨트위의로봇_Gold5_264ms {

	
	private static int N;
	private static int K;
	private static Belt[] belt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		belt = new Belt[2*N];
		
		for(int i=0; i<2*N; i++) 
			belt[i] = new Belt(Integer.parseInt(st.nextToken()));
		
	
		
		int time = 1;
		while(true) {
			moveBelt();
			moveRobot();
			if(checkZero()) { // 내구도가 0인 칸의 개수가 K개 이상인 경우
				System.out.println(time);
				break;
			}
			time++;
		}
	}
	private static void print() {
		for(int i=0; i<2*N; i++) {
			System.out.print(belt[i].durability+" ");
		}
		System.out.println();
	}
	private static boolean checkZero() {
		
		int count = 0;
		for(int i=0; i<2*N; i++) {
			if(belt[i].durability <=0) count++;
		}
		if(count >=K) return true;
		return false;
	}
	
	private static void moveRobot() {
		belt[N-1].robot = false;
		for(int i=N-2; i>0; i--) {
			// 다음자리에 로봇이 없고, 내구도가  0보다 큰 경우 로봇 이동
			if(belt[i].robot && !belt[i+1].robot && belt[i+1].durability>=1) {
				belt[i].robot = false;
				belt[i+1].robot = true;
				belt[i+1].durability--;
			}
		}
		if(belt[0].durability > 0) {
			belt[0].robot = true;
			belt[0].durability--;
		}
		
		
	}
	private static void moveBelt() {
		belt[N-1].robot = false;
		int nextDurability = belt[2*N-1].durability;
		for(int i=2*N-1; i>0; i--) {
			belt[i].durability = belt[i-1].durability;
			belt[i].robot = belt[i-1].robot;
		}
		belt[0].robot = false;
		belt[0].durability = nextDurability;
	}
	
}

class Belt{
	boolean robot;
	int durability;
	public Belt(int durability) {
		super();
		this.robot = false;
		this.durability = durability;
	}
	
}
