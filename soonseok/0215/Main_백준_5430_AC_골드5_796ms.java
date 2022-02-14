import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 입력 명령어에서 R이 두번 들어오는 "RR" 은 의미가 없다.
 * 문자열을 그대로 받고, 배열로 관리 안해도 가능할것 같다(입출력 양식이 동일함)
 * 배열의 앞과 끝을 가리키는 변수를 선언, reverseflag로 앞 뒤 변경을 체크
 * R이 들어오면 앞 뒤를 변경, D가 들어오면 인덱스만 수정한다면 어떨까?
 * 
 * 초기에 문자열로 그냥 하려다가 시행착오 엄청 겪음
 * 
 * 예외상항 [1] 처럼 원소가 1개 남았을때
 * 예외상황 []
 * 
 * 예외상황 배열에 들어있는 수가 0 00 000 최대 세자리수!!!!!
 * 
 * 1. 덱으로 만들어서 앞 뒤로 왔다 갔다하며 실제로 지워본다.
 * 2. 배열로 만들어서 index만 가지고 처리한다.
 * 3. 문자열 그대로 쓰면서 "," 가 나올때까지 검사하며 문자열 상태 그대로 index를 이용한다
 * 
 * 
 * 배열로 만들어서 풀기
 * 
 */
public class Main_백준_5430_AC_골드5_796ms {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수, (TC ≤ 100)

		for (int tc = 1; tc <= TC; tc++) {
			String ops = br.readLine(); // 명령어 p "RDDRD...", (1 ≤ p ≤ 100,000)
			ops = ops.replace("RR", ""); // 우선 ops에서 "RR"을 제거한다.
			int opsize = ops.length(); // RR은 두번 뒤집은거기때문에, 360도 회전은 사실상 아무것도 동작 안한것과 같음

			int size = Integer.parseInt(br.readLine()); // 배열에 있는 수의 개수 (0 ≤ n ≤ 100,000)
			String str = br.readLine(); // 배열에 들어있는 정수 (1 ≤ xi ≤ 100)
			str = str.replace("[", "").replace("]", ""); // 배열에 넣기 위해 앞뒤 괄호 제거

			StringTokenizer st = new StringTokenizer(str, ",");
			int[] array = new int[size];

			for (int i = 0; i < size; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			} // 숫자 배열 생성 완료

			int left = 0; // 배열의 맨 왼쪽 숫자를 가리키고 있다
			int right = size - 1; // 배열의 맨 오른쪽 숫자를 가리키고 있다
			boolean reverseFlag = false; // 현재 커서가 뒤집혔는지 체크
			boolean outOfRangeFlag = false; // 범위 벗어나서 에러 나는지 체크

			// 명령어 개수만큼 수행
			for (int i = 0; i < opsize; i++) {
				if (ops.charAt(i) == 'R') {
					reverseFlag = !reverseFlag; // 앞 뒤 뒤집기
				} else { // D : 현재 가리키는 쪽의 맨 앞에 있는 애를 지운다
					if (size == 0 || left > right) { // 배열 범위 초과하면 즉시 종료
						outOfRangeFlag = true;
						break;
					}

					if (!reverseFlag) { // 정순인 경우
						left++;
						size--;
					} else { // 역순인 경우
						right--;
						size--;
					}
				}
			} // end of ops for

			if (outOfRangeFlag) { // 문자열 벗어난 경우
				sb.append("error").append('\n');
				continue;
			}
			if (size == 0) { // size가 0인 경우
				sb.append("[]\n");
				continue;
			}

			if (!reverseFlag) { // 정순인 경우
				sb.append('[');
				for (int i = left; i <= right; i++) {
					sb.append(array[i]).append(',');
				}
				if (sb.length() > 0) // 뒤에 붙은 쉼표 없애주기
					sb.setLength(sb.length() - 1);
				sb.append("]\n");
			} else { // 역순으로 넣어야 하는 경우
				sb.append('[');
				for (int i = right; i >= left; i--) {
					sb.append(array[i]).append(',');
				}
				if (sb.length() > 0) // 뒤에 붙은 쉼표 없애주기
					sb.setLength(sb.length() - 1);
				sb.append("]\n");
			}

		} // end of tc for

		System.out.print(sb.toString());

	} // end of main
} // end of class
