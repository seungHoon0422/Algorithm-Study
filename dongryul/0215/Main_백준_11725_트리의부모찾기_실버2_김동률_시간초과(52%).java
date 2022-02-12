import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** Main_백준_11725_트리의부모찾기_실버2_김동률_시간초과(52%)*/
public class Main {
	public static void main(String[] args) throws Exception{

		
		/* 
		 * 문제
		 * 루트가 없는 트리가 주어진다.
		 * 트리의 루트를 1이라고 가정했을 때 각 노드의 부모를 구하는 프로그램을 작성해라
		 * 
		 * 조건
		 * 노드의 수 : 2 <= N <= 100_000
		 * 시간 1초
		 * 입력
		 * 노드의 수
		 * 2개의 노드
		 * 출력
		 * 각 노드의 부모 노드 번호를 2번 노드부터 출력해라
		 * 
		 * IDEA
		 * 6
		 * 6 5
		 * 5 4
		 * 4 3
		 * 3 2
		 * 2 1
		 * 이렇게 오면 O(N^2)의 시간 복잡도를 갖게 된다.
		 * --> 최악의 경우 약 100억번의 연산을 해야할수도? 
     		 * --> 이왕 계속 삽입 삭제를 해야하면 큐를 쓰자
		 * 
		 * 어떻게 연산을 해야할까? 
		 * 1차원 배열 하나를 사용하고, 각 자리에 자신의 부모노드의 번호를 넣는다
		 * 2개의 데이터가 들어올 때 
		 * 1. 둘 중 하나가 1인지 확인 -> 1이 있다면 다른 하나의 부모를 1로 설정
		 * 2. 둘 중 하나가 부모가 있는지 확인 -> 있다면 없는 녀석의 부모가 부모가 있는 녀석, 없다면 큐에 추가
		 * 3. 위 과정들을 반복하면 나중에 큐에 있는 놈들 비우면서 부모 여부 확인
		 * 
		 * 
		 * 1차, 54퍼 시간 초과
		 * 큐를 사용해서 시간이 초과되었나? 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		// 인덱스와 1대1 매칭
		int [] arr = new int[N+1];
		// 부모가 아직 확정되지 않는 노드들 저장용 
		Queue<int[]> q = new LinkedList<>();
		// 트리 구성
		for(int range = N-1, i=0; i<range; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			// 둘 중 하나가 1인 경우
			if(num1==1) {
				arr[num2] = 1;
			}
			else if(num2==1) {
				arr[num1] = 1;
			}
			// 둘 중 누구도 1이 아닌 경우
			else {
				// num1이 부모가 있는 경우
				if(arr[num1]!=0) {
					arr[num2] = num1;
				}
				// num2이 부모가 있는 경우
				else if(arr[num2]!=0) {
					arr[num1] = num2;
				}
				// 둘 다 아직 부모가 없는 경우
				else {
					// 임시 배열을 만들어서 큐에 추가
					int [] temp = {num1, num2};
					q.offer(temp);
				}
			}
		}
		
		// 아직 부모를 할당받지 못한 좌표들 연결
		while(!q.isEmpty()) {
			// 해당 배열에 속한 이들은 누구도 1이 아니다. --> 따라서 temp[0], temp[1] 중 부모가 있는 놈이 있다면 연결, 없다면 다시 큐에 추가
			int [] temp = q.poll();
			if(arr[temp[0]]!=0) {
				arr[temp[1]] = temp[0];
			}
			else if(arr[temp[1]]!=0) {
				arr[temp[0]] = temp[1];
			}
			// 아직 둘 다 부모가 없다면
			else {
				q.offer(temp);
			}
		}
		
		// 정답 출력
		for(int i=2; i<=N; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	} // end of main
	
} // end of Main


