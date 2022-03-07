import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** Main_백준_16987_계란으로계란치기_실버1_204ms*/
public class Main_백준_16987_계란으로계란치기_실버1_204ms {
	
	private static int N, total;
	private static int[] durability;
	private static int[] weight;

	public static void main(String[] args) throws Exception{
		
		/*
		 * 계란은 내구도와 무게가 정해져있다.
		 * 계란으로 계란을 치게되면, 각 계란의 내구도는 상대 계란의 무게만큼 줄어든다.
		 * 내구도가 0 이하가 되면 계란을 깨진다. 
		 * 
		 * 일렬로 놓여있는 계란에 대해 왼쪽부터 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제
		 * 1 가장 왼쪽 계란을 든다
		 * 2 다른 계란을 친다. 단. 손에 든 계란이 깨졌거나, 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다. 이때 계란을 원위치시킨다,
		 * 3 최근에 들었던 계란 우측에 있던 계란을 들고 깨지지 않은 계란 중 하나를 친다. 
		 * 단 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란인 경우 종료한다
		 * 
		 * 백트래킹 예제,
		 * + 순서에 상관없이 가장 많은 계란을 깨야한다
		 * --> 사용 꺠기 반납 반복
		 * 가장 오른쪽에 위치한 계란을 들게 된다면 return total(깨진 계란 수)
		 * + 깰 수 있는 계란이 없는 경우 return
		 */
		
		// 초기화 세션
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 1 <= N <= 8 
		durability = new int[N];
		weight = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			durability[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		total = 0;
		// 몇번째 계란, 깨진 계란 수
		go(0, 0);
		
		System.out.println(total);
	} // end of main
	

	private static void go(int picked, int broken) {
		// 가장 우측에 있는 계란을 들었을 경우 + 꺨 수 있는 계란이 없는 경우
		if(picked==N || N-broken == 1) {
			total = Math.max(total, broken);
			return;
		}
		// 반복문
		for(int i=0; i<N; i++) {
			// 지금 손에 쥔 계란이라면
			if(picked==i) continue;
			// 들고 있는 계란이 깨진 계란이면 + 아래에서 이미 깨진 계란으로 합산 함
			if(durability[picked]<=0) {
				// 다음 계란
				go(picked+1, broken);
				return;
			}
			// 대상 계란이 이미 깨진 계란이라면
			if(durability[i]<=0) continue;
			int breakEggs = 0;
			// 계란으로 계란깨기
			durability[picked]-=weight[i];
			durability[i]-=weight[picked];
			
			// 계란으로 계란을 쳐서 깨진 계란 수 카운팅
			if(durability[picked]<=0) breakEggs++;
			if(durability[i]<=0) breakEggs++;
			
			go(picked+1, broken+breakEggs);
			
			// 다른 계란 기준으로 탐색을 위한 복구
			durability[picked]+=weight[i];
			durability[i]+=weight[picked];
		}
	} // end of go()
} // end of class
