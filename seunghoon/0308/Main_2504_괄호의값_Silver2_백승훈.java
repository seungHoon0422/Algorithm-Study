package BOJ0308;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2504_괄호의값_Silver2_백승훈 {

	private static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String input = br.readLine();
		System.out.println(go(0, 0, new Stack<Character>(), input));

		
		 
	} // end of main

	private static int go(int index, int temp,Stack<Character> stack, String input) {
		char c = input.charAt(index);
		
		if(c == '(' || c == '[') {
			stack.push(c);
			return go(index+1, temp, stack, input);
		} else if(c == ')') {
			if(temp == 0) return 2;
			else return temp*2;
		} else if(c == ']') {
			if(temp == 0) return 3;
			else return temp*3;
		}
		return 0;
	}
	
} // end of class
