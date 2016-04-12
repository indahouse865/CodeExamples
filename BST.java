import java.util.Random;
import java.util.ArrayList;

public class BST {
	
	 public static ArrayList<Integer> stack = new ArrayList <>();

 private class BinaryNode {
	int key;
	BinaryNode left=null, right=null;
	BinaryNode parent = null;
	

	//BinaryNode Constructor
	BinaryNode(int k) {
	  key=k;
	  
	}
	//BinaryNode Constructor
		BinaryNode(int k, BinaryNode u) {
		  key=k;
		  parent = u;
		}
 }
 

 // member
 BinaryNode root=null;
 int size=0;
 int failed=0;     // complement of "size" telling us how many times
 					// an insertion failed.

 // returns a node: if the tree is non-empty, the node is non-null
 // and either contains key k or is the immediate successor or predecessor
 // of k.
 public BinaryNode search(int k){
	if (root == null) return null;
	BinaryNode u = root;
	
	while (u.key != k) { //invariant: u is non-null
	   if (u.key > k) {
	       if (u.left == null) return u;
	       else u=u.left;
	   } else {
	       if (u.right == null) return u;
	       else u=u.right;
	   }
	}
	return u;
 }

 // adds a new key to the tree (if possible)
 public void add(int k){
	if (root == null) {
	    root = new BinaryNode(k);
	    size = 1;
	    return;
	}
	BinaryNode u = search(k);
	if (u.key == k) {
	    failed++;
	    return; 	// cannot add
	}
	size++;
	if (u.key > k)
	    u.left = new BinaryNode(k, u);
	else
	    u.right = new BinaryNode(k, u);
	
	if (k % 2 == 1) { //if the key is odd then add it to the array list.
		stack.add(k);
	}
 }//add

 // inorder traversal:
 public void inorder(BinaryNode u){
	if (u == null) return;
	inorder(u.left);
	System.out.printf(" %d", u.key);
	inorder(u.right);
 }//inorder
 public void inorder(){
	System.out.printf("\n ===============\n");
	inorder(root);
	System.out.printf("\n ===============\n");
 }
 
 public void preorder(BinaryNode u) {
	if (u == null) return;
	System.out.printf(" %d", u.key);
	preorder(u.left);
	preorder(u.right);
 }
 public void preorder() {
	System.out.printf("\n ===============\n");
	preorder(root);
	System.out.printf("\n ===============\n");
 }
 
 public void postorder(BinaryNode u) {
		if (u == null) return;
		postorder(u.left);
		postorder(u.right);
		System.out.printf(" %d", u.key);
	 }
	 public void postorder() {
		System.out.printf("\n ===============\n");
		postorder(root);
		System.out.printf("\n ===============\n");
	 }
 
 public boolean remove (int key) {
	BinaryNode u = search(key);
	if (u != null) {
		remove(u);
		return true;
	}
	return false;
	 
 }
 
 public boolean remove(BinaryNode u) {
	 if (u.left == null||u.right==null){
		 cut(u);
		 return false;
	 } else {
		 BinaryNode x = u.right;
		 while(x.left!=null){
			 x=x.left;
		 }
		 u.key = x.key;
		 cut(x);
		 return true;
	 }
 }
	
 public void cut (BinaryNode u) {
	 if(u.right==null&&u.left==null){
			if(u.parent.right==u){
				u.parent.right=null;
			} else if(u.parent.left==u){
				u.parent.left=null;
			} // close inner if
			
		}else if(u.left!=null&&u.right==null){
			if(u.parent.right==u){
				u.parent.right=u.left;
				u.left.parent=u.parent;
			} else if(u.parent.left==u){
				u.parent.left=u.left;
				u.left.parent=u.parent;
			}//close inner if
		} else if(u.left==null&&u.right!=null){
			if(u.parent.left==u){
				u.parent.left=u.right;
				u.right.parent=u.parent;
			} else if(u.parent.right==u){
				u.parent.right=u.right; 
				u.right.parent=u.parent;
			} // close inner iff
		}
 } // end cut
 
 public static void main(String[] args) {
		
	int ss = 111;
	int nn = 20;
	if (args.length>0) ss = Integer.parseInt(args[0]);
	if (args.length>1) nn = Integer.parseInt(args[1]);
	
	Random rg = new Random(ss);

	System.out.printf("BST!\n");

	BST t = new BST();

	for (int i=0; i<nn; i++){
	    int a = rg.nextInt(nn);
		t.add(a);
		System.out.printf("Added %d \n", a);
	}
	t.inorder();
	t.postorder();
	System.out.printf("Size = %d\n", t.size);
	System.out.printf("Failed = %d\n", t.failed);
	
	System.out.printf("Now removing all odd numbered keys..."); //for each element in the array list
	System.out.println(stack);
	for (int i=0; i<stack.size(); i++) { //call the remove method to remove the key then the node and cut a successor.
		int key1 = i;
		System.out.println(key1);;
		t.remove(stack.get(i));
	}//for loop
	
	t.inorder();
	t.preorder();
	
 }//main

}
