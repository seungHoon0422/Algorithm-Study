package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_11399_ATM_S3_박지호_152ms {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ArrayList<Integer> person = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			person.add(Integer.parseInt(st.nextToken()));
		}

		int result = getResult(person);
		System.out.println(result);

	}

	public static int getResult(ArrayList<Integer> person) {

		Collections.sort(person); // 오름차순 정렬
		int sum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				sum += person.get(j);
			}

		}

		return sum;
	}

}

// 그리디 => 뽑은 이후 선택을 번복하지 않는다
// 현재 상태에서 최선의 선택을 하는 것
// 정렬하고 뒤부터 비교해서 리스트에 넣는다
