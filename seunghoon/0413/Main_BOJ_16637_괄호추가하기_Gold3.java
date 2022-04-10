import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.omg.CORBA.FREE_MEM;

/**
 * category : Brute Force
 * 
 * - 아이디어1
 * 괄호의 개수가 정해져있지도 않고, 연산의 결과만 최대로 나오면 된다. => 프루트포스 접근 필요
 * 괄호를 추가할 때마다 연산을 실행해야 함
 * 
 * 연산자의 개수가 홀수개일 경우
 * 1-2-3-4
 * 괄호를 만들 수 있는 가지수 =>  
 * 괄호 0개 : 1-2-3-4
 * 괄호 1개 : (1-2)-3-4, 1-(2-3)-4, 1-2-(3-4)
 * 괄호 2개 : (1-2)-(3-4)
 * 
 * 
 * 연산자를 기준으로 부분집합으로 표현
 * 
 * <고려사항>
 * 연산자 2개가 연속으로 선택될 수 없다.
 * 연산의 결과를 들고다니면서 비교할수 없으므로 마지막까지 도달했을 때 연산 진행
 * 
 * <자료구조 선택>
 * 연산을 진행하면서 index를 사용해서 연산될 값과 연산자를 배열에 저장해 사용할 수 있지만,
 * 리스트를 사용해서 풀이
 * 
 *  
 * point1 : 괄호안에는 연산자 1개만
 * point2 : 중첩된 괄호는 사용하지 못한다.
 * 
 * 1-2+3-4-5-6-7
 * 2+3을 괄호로 묶으면, 2와연결되있는 1, 3과연결되있는 4는 묶을수 없다.
 * 1-(2+3)-4-5-6-7
 * => 연산자별로 묶을수 있는지를 저장하고, 괄호로 묶은 연산자 양쪽의 연산자는 묶을 수 없음
 * 
 * @author SeongHoon
 *
 */
public class Main_BOJ_16637_괄호추가하기_Gold3 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static ArrayList<Integer> number;
	private static ArrayList<Character> operator;
	private static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		String expression = br.readLine();
		
		number = new ArrayList<Integer>();
		operator = new ArrayList<Character>();
		
		for(int i=0; i<N; i+=2) number.add(expression.charAt(i)-'0');	
		for(int i=1; i<N; i+=2) operator.add(expression.charAt(i));	
		
		result = 0;
		selectOperator(0, new boolean[operator.size()]);
		System.out.println(result);
		
		
		
		
		
		
		
		
	} // end of main

	private static void selectOperator(int n, boolean[] select) {
		
		if(n == operator.size()) {
			// 기저조건, 괄호를 생성할 연산자 선택이 끝난 경우
			
			for(int i=0; i<operator.size()-1; i++) if(select[i] && select[i+1]) return;
			int value = operate(select);
			result = Math.max(result, value);
			return;
		}
		
		for(int i=0; i<operator.size(); i++) {
			
			selectOperator(n+1, select);
			
			// 연산자가 연속으로 선택되지 않게 백트래킹
			if(i!=0 && !select[i-1]) {
				select[i] = true;
				selectOperator(n+1, select);
				select[i] = false;
			}
			
			
			
			
		}
		
		
	}

	private static int operate(boolean[] select) {
		// 괄호를 씌울 연산자를 찾고 연산 진행
		// 연산의 결과를 return하는 함수
		
		int[] numbers = new int[number.size()];
		Character[] operators = new Character[operator.size()];
		boolean[] numberVisit = new boolean[numbers.length];
		
		
		for(int i=0; i<number.size(); i++) numbers[i] = number.get(i);
		for(int i=0; i<operator.size(); i++) operators[i] = operator.get(i);
		
		
		// 괄호가 생성된 연산자의 경우 연산자 오른쪽의 자리에 연산 결과를 저장
		for(int i=0; i<operator.size(); i++) {
			if(select[i]) { // 괄호가 쳐진 연산자
				switch (operator.get(i)) {
				case '+':
					numbers[i+1] = numbers[i] + numbers[i+1];
					numberVisit[i] = true;
					operators[i] = '#';
					break;
				case '-':
					numbers[i+1] = numbers[i] - numbers[i+1];
					numberVisit[i] = true;
					operators[i] = '#';
					break;
				case '*':
					numbers[i+1] = numbers[i] * numbers[i+1];
					numberVisit[i] = true;
					operators[i] = '#';
					break;
				case '/':
					numbers[i+1] = numbers[i] / numbers[i+1];
					numberVisit[i] = true;
					operators[i] = '#';
					break;
				}
			}
		}
		
		List<Integer> nums = new ArrayList<>();
		List<Character> opers = new ArrayList<>();
		
		for(int i=0; i<numbers.length; i++) if(!numberVisit[i]) nums.add(numbers[i]);
		for (Character c  : operators) if(c!='#') opers.add(c);
		
		
		// 괄호가 생성되지 않은 연산자만 연산을 진행
		// 괄호가 생성된 연산자의 
		
//		for (int val : nums) {
//			System.out.print(val+ " ");
//		}
//		for (char c : opers) {
//			System.out.print(c+ " ");
//		}
		
		
		int res = nums.get(0);
		
		for(int i=0; i<opers.size(); i++) {
			switch (opers.get(i)) {
			case '+':
				res += nums.get(i+1);
				break;
			case '-':
				res -= nums.get(i+1);
				break;
			case '*':
				res *= nums.get(i+1);
				break;
			case '/':
				res /= nums.get(i+1);
				break;

			default:
				break;
			}
		}
		
//		System.out.println("<"+res+">");
		return res;
	}
} // end of class








