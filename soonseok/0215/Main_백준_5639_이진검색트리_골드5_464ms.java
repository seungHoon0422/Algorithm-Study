package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * 트리 만든 후 postorder로 출력
 * 이진트리이므로 노드 구조 left, right
 */

public class Main_백준_5639_이진검색트리_골드5_464ms {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> postList = new ArrayList<Integer>();

		// VLR 순서대로 전위순회했으니 맨 처음 값이 root 이다.
		// 우선 root 노드를 만들어 준다
		Node root = new Node(Integer.parseInt(br.readLine()));

		String temp;
		// N이나 종료시점이 없으므로, null이 들어올때까지 입력 받는다
		while ((temp = br.readLine()) != null && temp.length() != 0) {
			// root 값과 비교하면서 노드를 추가해준다
			putNode(root, new Node(Integer.parseInt(temp)));
		}

		postOrder(root, postList);

		for (int i = 0; i < postList.size(); i++) {
			sb.append(postList.get(i)).append("\n");
		}
		System.out.print(sb.toString());

	} // end of main

	// root를 받고 후위순회(LRV) 하는 메소드
	public static void postOrder(Node node, ArrayList<Integer> postList) {
		if (node.left != null) // 왼쪽먼저 가고
			postOrder(node.left, postList);
		if (node.right != null) // 오른쪽 가고
			postOrder(node.right, postList);
		postList.add(node.key); // 마지막에 자신을 본다
	} // postOrder()

	// tree에 node 추가
	public static void putNode(Node node, Node newNode) {
		// 새로 들어온 애 키가 더 작은 경우
		if (node.key > newNode.key) {
			// left 쪽으로 보내야 함
			if (node.left == null) { // left가 비어있으면
				node.left = newNode; // 새 노드가 그 자리로
			} else { // left가 비어있지 않다면
				putNode(node.left, newNode); // left로 따라가서 다시 삽입 검사
			}
		}
		// 같은 키를 가진 노드는 없으니 else 사용 가능
		// 키가 더 큰 경우
		else {
			// right 쪽으로 보내야 함
			if (node.right == null) { // right가 비어있으면
				node.right = newNode; // 새 노드가 그 자리로
			} else { // left가 비어있지 않다면
				putNode(node.right, newNode); // left로 따라가서 다시 삽입 검사
			}
		}
	} // end of putNode()

	// node 구조
	public static class Node {
		int key;
		Node left;
		Node right;

		public Node(int key) {
			this.key = key;
		}
	} // end of Node class

} // end of class
