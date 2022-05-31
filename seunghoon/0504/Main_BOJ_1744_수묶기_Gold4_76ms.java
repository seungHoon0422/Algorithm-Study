import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 
 * [규칙]
 * 양수는 큰수부터 2개씩 곱한다.
 * 1은 곱하기보단 더하기로 처리
 * 
 * 음수는 작은수부터 2개씩 곱한다.
 * 음수의 경우 -1과 0을 곱하려고 할 때 무조건 곱해야하므로
 * 1개만 남는경우가 아니면 무조건 곱한다.
 * 
 * @author SeongHoon
 *
 */
public class Main_BOJ_1744_수묶기_Gold4_76ms {


	static int N;
	static List<Integer> nn = new ArrayList<>();
	static List<Integer> pn = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n > 0)
				pn.add(n);
			else
				nn.add(n);
		}

		Collections.sort(pn, Collections.reverseOrder());
		Collections.sort(nn);

//		System.out.println(Arrays.toString(pn.toArray()));
//		System.out.println(Arrays.toString(nn.toArray()));
		
		int sum = 0;
		int i = 0;
		while (i < pn.size()) {
			if (i + 1 < pn.size() && pn.get(i) != 1 && pn.get(i + 1) != 1)
				sum += pn.get(i++) * pn.get(i++);
			else
				sum += pn.get(i++);
		}

		i = 0;
		while (i < nn.size()) {
			if (i + 1 < nn.size() )
				sum += nn.get(i++) * nn.get(i++);
			else
				sum += nn.get(i++);
		}

		System.out.println(sum);
	}
}








