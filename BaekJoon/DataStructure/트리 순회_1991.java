import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static Node rootNode = new Node('A', null, null);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			setTree(rootNode, root, left, right);
		}
		preOrder(rootNode);
		System.out.println();
		inOrder(rootNode);
		System.out.println();
		postOrder(rootNode);
	}

	static void setTree(Node node, char root, char left, char right){
		if(node.root == root){
			node.left = left == '.' ? null : new Node(left, null, null);
			node.right = right == '.' ? null : new Node(right, null, null);
		}else{
			if(node.left != null) setTree(node.left, root, left, right);
			if(node.right != null) setTree(node.right, root, left, right);
		}
	}

	static void preOrder(Node node){
		if(node == null) return;
		System.out.print(node.root);
		preOrder(node.left);
		preOrder(node.right);
	}

	static void inOrder(Node node){
		if(node == null) return;
		inOrder(node.left);
		System.out.print(node.root);
		inOrder(node.right);
	}

	static void postOrder(Node node){
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.root);
	}

}


class Node{
	char root;
	Node left;
	Node right;

	public Node(char root, Node left, Node right) {
		this.root = root;
		this.left = left;
		this.right = right;
	}
}
