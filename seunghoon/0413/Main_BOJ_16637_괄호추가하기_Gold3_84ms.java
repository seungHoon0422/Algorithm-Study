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
public class Main_BOJ_16637_괄호추가하기_Gold3_84ms {


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
		
		result = Integer.MIN_VALUE;
		selectOperator(number.get(0), 0);
		System.out.println(result);
		
		
		
		
		
		
		
		
	} // end of main

	private static void selectOperator(int value, int index) {
		
		if(index >= operator.size()) {
			// 기저조건, 괄호를 생성할 연산자 선택이 끝난 경우
			result = Math.max(result, value);
			return;
		}
		
		int next = calculation(operator.get(index), value, number.get(index+1));
		selectOperator(next, index+1);
		
		if (index + 1 < operator.size()) {
            // result의 오른쪽에 있는 값을 연산함.
            int res = calculation(operator.get(index + 1), number.get(index + 1), number.get(index + 2));
            // 현재 result와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
            selectOperator(calculation(operator.get(index), value, res), index + 2);
        }
			
		
		
	}

	private static int calculation(char c, int val1, int val2) {
		switch (c) {
		case '+': return val1 + val2;
		case '-': return val1 - val2;
		case '*': return val1 * val2;
		}
		return -1;
	}
	
	
} // end of class








