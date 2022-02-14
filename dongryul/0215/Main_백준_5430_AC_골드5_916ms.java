import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/** Main_백준_5430_AC_골드5_916ms*/
public class Main_백준_5430_AC_골드5_916ms {
	
	// 배열이 가질수 있는 수의 최대값으로 지정
	static int[] AC;
	static int head, tail;
	static boolean flag, reversed;
	private static StringBuilder sb;
	
	/** AC배열에 어떤 값이 들어왔을 떄 메서드*/
	private static void addAC(int num) {
		AC[tail++] = num;
	}
	
	private static void D() {
		// 데티터가 없을 떄
		if(tail<0 || (AC[head]==0 && AC[tail]==0)) {
			flag = false;
		} // check end of array
		
		else if(reversed) {
			if(AC[tail-1]==0){
				flag = false;
			}
			else {
				AC[--tail]=0;
			}
		} // end of if reversed 
		
		else {
			if(AC[head]==0){
				flag = false;
			}
			else {
				AC[head++]=0;
			}
		} // end of not reversed 
	}
	
	private static void R() {
		// head 부터 빼기 & 출력
		if(reversed) {
			reversed = false;
		}
		// tail부터 빼기 & 출력
		else {
			reversed = true;
		}
	}
	
	private static void ACString() {
		if(flag) {
			sb.append("[");
			if(head!=tail) {
				if(!reversed) {
					while(head<tail) {
						sb.append(AC[head++]).append(",");
					}
				}
				else {
					if(tail>0) {
						while(--tail>=head) {
							sb.append(AC[tail]).append(",");
						}
					}
				}
				// [] 이런 출력도 있으니까
				if(sb.length()>=2) {
					sb.setLength(sb.length()-1);
				}
			}
			sb.append("]").append("\n");
		}
		else {
			sb.append("error").append("\n");
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		// TC의 수만큼 반복
		for(int testcase=0; testcase<TC; testcase++) {
			// 실행 작업을 커맨트에 넣는다.
			char[] command = br.readLine().toCharArray();
			// 배열의 개수
			int N = Integer.parseInt(br.readLine());
			// 한계치 배열을 만든다. 배열은 비었는데 D가 100000번 올 수 있다.
			AC = new int[4000001];
			// 헤드, 테일 0로 초기화
			head = 0;
			tail = 0;
			flag = true;
			reversed = false;
			// 배열 값 StringTokenizer에 delim을 [,]로 두어 해당 기호들 기준으로 나눔
			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
			for(int i=0; i<N; i++) {
				addAC(Integer.parseInt(st.nextToken()));
			}
			// AC 행동 지시
			for(int i=0, len = command.length; i<len; i++) {
				if(command[i]=='R') {
					R();
				}
				else {
					D();
				}
			}
			// 정답 출력하기
			ACString();
		}
		// 정답 출력
		System.out.println(sb.toString());
	} // end of main
	
} // end of Main
