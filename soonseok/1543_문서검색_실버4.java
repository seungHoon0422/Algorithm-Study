import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 예외 : 인덱스를 처음부터 탐색위치+문자열길이만큼 지워주지 않는 경우
 */

public class Main_백준_1543_문서검색_실버4 {
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String docs = bf.readLine(); // input document 0<d<=2500
		StringBuilder sb = new StringBuilder(docs);

		String word = bf.readLine(); // input word to find 0<w<=50

		int wordCount = 0; // count of word in docs
		
		while (sb.length() >= word.length()) {

			int idx = sb.indexOf(word);

			if (idx == -1)
				break; // not exists

			sb.delete(0, idx + word.length()); // exists
			wordCount++;

		} // end of while

		System.out.print(wordCount);

	} // end of main
} // end of class
