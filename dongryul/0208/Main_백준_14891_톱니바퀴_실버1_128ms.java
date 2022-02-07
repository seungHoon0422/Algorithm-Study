import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Main_백준_14891_톱니바퀴_실버1_128ms*/
public class Main_백준_14891_톱니바퀴_실버1_128ms {
	
	static String[] chains;
	static boolean[] rotated;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 톱니바퀴 데이터 초기화
		chains = new String[4];
		for(int i=0; i<4; i++) {
			chains[i] = br.readLine();
		}
		
		// 회전 횟수 초기화
		int K = Integer.parseInt(br.readLine());
		
		//K번 회전
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int chain = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			rotated = new boolean[4];
			// 지정한 톱니바퀴를 지정된 방향으로 회전
			rotate(chain, dir, true, 0);
		}
		
		// 점수의 총합 구하고 출력
		int count = 0;
		for(int i=0; i<4; i++) {
			count+=calc(i);
		}
		System.out.println(count);
	} // end of main

	private static int calc(int chain) {
		// 12시 방향이 N극이라면
		if(chains[chain].charAt(0)=='0') {
			return 0;
		}
		// S극이면 2의 i승을 반환
		else {
			return (int) Math.pow(2, chain);
		}
	} // end of calc

	// 재귀적으로 방향 지정하여 회전
	private static void rotate(int chain, int dir, boolean first, int direction) {
		int ch = chain-1;
		// 범위 확인
		if(ch<0 || ch>3) {
			return;
		}
		// 이미 돌렸던 바퀴라면 
		if(rotated[ch]) {
			return;
		}
		// 우측으로 갈지 좌측으로 갈지 
		boolean right = false;
		boolean left = false;
		// 첫번째 회전이라면 무조건 돈다
		if(first) {
			// 회전 이전 기준 좌우측 탐방 여부 검사
			if(ch>=1) {
				if(chains[ch].charAt(6)!=chains[ch-1].charAt(2)) {
					left = true;
				}
			}
			if(ch<=2) {
				if(chains[ch].charAt(2)!=chains[ch+1].charAt(6)) {
					right = true;
				}
			}
			// 회전 지시
			rotated[ch] = true;
			changeString(ch, dir);
		} // end of first Rotation
		
		// 파생된 회전이라면
		else {
			// 첫번째 기준 우측으로 가는중이라면
			if(direction==1) {
				// 현재 바퀴의 2번 인덱스와 다음 바퀴의 6번 인덱스와 비교
				if(ch <= 2 && chains[ch].charAt(2)!=chains[ch+1].charAt(6)) {
					right = true;
				}
				changeString(ch, dir);
				rotated[ch] = true;
			}
			else {
				if(ch >= 1 && chains[ch].charAt(6)!=chains[ch-1].charAt(2)) {
					left = true;
				}
				changeString(ch, dir);
				rotated[ch] = true;
			}
		} // end of Rotation
		if(right) {
			rotate(chain+1, dir*-1, false, 1);
		}
		if(left) {
			rotate(chain-1, dir*-1, false, -1);
		}
	} // end of rotate
	
	private static void changeString(int chain ,int dir) {
		StringBuilder sb = new StringBuilder();
		if(dir==1) {
			sb.append(chains[chain].charAt(7)).append(chains[chain].substring(0, 7));
		}
		else {
			sb.append(chains[chain].substring(1, 8)).append(chains[chain].charAt(0));
		}
		chains[chain] = sb.toString();
	} // end of changeString
} // end of class
