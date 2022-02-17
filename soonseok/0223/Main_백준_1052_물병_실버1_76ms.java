import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 이진법을 활용하는 문제
 * 
 * N=13, K=2 / 정답=3 인 케이스에서
 * 
 * 13을 물병으로 쪼개보면 8 4 1 이 남고 13 = 1101(2) 로 표현 가능하다 즉 각 이진법의 자리수는 현재 물병의 상태를 나타낸다
 * 이진법을 진행하여 가장 처음으로 만나는 1 -> ex) 0100 인 경우 8 물병을 더해줘야 한다.
 * 
 * 기존에는 1bit씩 더해서 900ms 가량 나왔었음.
 * 1101 -> 1110 -> 1111 -> 10000 이러면 기존의 4자리까지가 모두 0이므로 총 3회
 * 
 * 불가능한 경우가 있을때는 -1을 출력하라고 했지만, 사실상 불가능한 경우는 존재하지 않음
 *
 */
public class Main_백준_1052_물병_실버1_76ms {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 물병의 총 개수 N개 ( 1 ≤ N ≤ 10,000,000 )
		int K = Integer.parseInt(st.nextToken()); // 한번에 옮길 수 있는 물병 K개 ( 1 ≤ K ≤ 1,000 )

		int buyBottle = 0; // 구매한 물병 개수 저장
		int firstIdx; // 가장 처음 마주치는 이진법 1의 자리수
		int curBottle; // 현재 물병의 개수
		do {
			firstIdx = -1;
			curBottle = 0;
			// 이진법으로 표현했을때 존재하는 1의 개수가 현재 물병의 개수
			for (int i = 0, size = Integer.toBinaryString(N).length(); i < size; i++) {
				if ((N & 1 << i) != 0) {
					if (firstIdx == -1) // 처음 만났다면
						firstIdx = 1 << i; // 해당 자리수를 저장해준다

					curBottle++; // 현재 물병 개수 카운팅
				}

			}

			buyBottle += firstIdx; // 구매한 물병 개수를 더해준다
			N += firstIdx; // 2^(firstIdx) 를 더해준다

		} while (curBottle > K); // 현재 물병 수가 들고 갈 수 있게 되면 종료

		System.out.print(buyBottle - firstIdx); // 마지막 종료시에도 buyBottle 더해주니까, 빼줘야함

	} // end of main

} // end of class
