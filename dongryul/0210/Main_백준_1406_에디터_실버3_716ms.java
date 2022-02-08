import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

// 시간 초과
/** Main_백준_1406_에디터_실버3_716ms*/
public class Main_백준_1406_에디터_실버3_716ms {
	static int n;
	static List<Character> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		// 잦은 추가, 삭제를 위한 LinkedList
		list = new LinkedList<Character>();
		//문자열 list에 추가
		char[] temp = br.readLine().toCharArray();
		for(int i=0; i<temp.length; i++) {
			list.add(temp[i]);
		}
		// List.add remove는 O(n)이고, listIterator를 사용하면 인덱스에 직접 접근하여 O(1)이라고 하네요.
		ListIterator<Character> iter = list.listIterator();
		while(iter.hasNext()) {
			iter.next();
		}
		// 입력의 수
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			// 입력을 받는다
			st = new StringTokenizer(br.readLine());
			// 입력에 대한 행동 지시
			switch(st.nextToken()) {
			case "L":
				if(iter.previousIndex()!=-1) {
					iter.previous();
				}
				break;
			case "D":
				if(iter.hasNext()) {
					iter.next();
				}
				break;
			case "B":
				// 범위를 벗어나지 않게
				if(iter.previousIndex()!=-1) {
					// 이전 값 선택 후 삭제
					iter.previous();
					iter.remove();
				}
				break;
			case "P":
				iter.add(st.nextToken().charAt(0));;
				break;
			default:
				break;
			} // end of switch
		} // end of for 
		// iterator 맨 앞으로 이동
		while(iter.previousIndex()!=-1) {
			iter.previous();
		}
		// 앞에서부터 단어 출력
		while(iter.hasNext()) {
			sb.append(iter.next());
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
