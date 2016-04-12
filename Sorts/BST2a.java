import java.util.Random;
import java.util.ArrayList;

public class BST2a {

private class BinNode {
	int key;
	BinNode left=null, right=null, parent=null;

	//BinaryNode Constructor
	BinNode(int k) {
	  key=k;
	}
}

////////////////////////////////////////////////////
// MEMBERS
////////////////////////////////////////////////////
	BinNode root=null;
	int size=0;
	int failed=0;     // complement of "size" telling us how many times
					// an insertion failed.

////////////////////////////////////////////////////
// METHODS
////////////////////////////////////////////////////

// returns a node: if the tree is non-empty, the node is non-null
// and either contains key k or is the immediate successor or predecessor
// of k.
public BinNode search(int k){
	if (root == null) return null;
	BinNode u = root;
	
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
public boolean add(int k){
	if (root == null) {
	    root = new BinNode(k);
	    size = 1;
	    return true;
	}
	BinNode u = search(k);
	if (u.key == k) {
	    failed++;
	    return false; 	// cannot add
	}
	size++;
	if (u.key > k) {
	    u.left = new BinNode(k);
	    u.left.parent = u;}
	else {
	    u.right = new BinNode(k);
	    u.right.parent = u;}
	return true;
}//add

// inorder traversal:
public void inorder(BinNode u){
	if (u == null) return;
	System.out.printf("(");
	inorder(u.left);
	System.out.printf("%d", u.key);
	inorder(u.right);
	System.out.printf(")");
}//inorder

public void inorder(int verbose){
	if (verbose>0) 	System.out.printf("\n ===============\n");
	inorder(root);
	if (verbose>0) 	System.out.printf("\n ===============\n");
}
public void inorder(){
	inorder(root);
}//inorder
		
// min(u): where u is non-null 
private BinNode min(BinNode u) {
   while (u.left != null) u = u.left;
   return u;
}//min

// cut (u): where u is non-null and has at most one child 
private void cut(BinNode u){
   BinNode w; // if u has a non-null child, it is w!
   if (u.left == null) w=u.right;
   else w = u.left;
   if (u.parent == null) {
	   root = w; 
	   if (w!=null) w.parent=null;
	   }
   else {// u.parent != null
	   if (u.parent.left == u) {
		   u.parent.left = w;
		   if (w!=null) w.parent=u.parent;
	   } else {
		   u.parent.right = w;
		   if (w!=null) w.parent=u.parent;
	   }
   }
   
}//cut

// remove(key): returns true if key was removed
//
public boolean remove(int k){
   BinNode u = search(k);
   if (u == null || u.key != k) return false;
   remove(u);
   return true;
}//remove(key)

// remove(node): 
public void remove(BinNode u){
   BinNode v = u; // v is the node we want to cut!
   if (u.left != null && u.right != null) {
   	  v = min(u.right);
      u.key = v.key;
   }
   cut(v);
   size--;
}//remove(node)

// diagnostics
public void diag(String msg, int verbose){
	System.out.println(msg);
	if (verbose>0){
		if (root==null) 
			System.out.println("DIAG: empty tree\n");
		else {
			System.out.printf("DIAG:  root=%d, size=%d \n", root.key, size);
	if (verbose>1 && root.left!=null)
		System.out.printf("DIAG: right=%d,\n", root.right.key );
			System.out.printf("DIAG: tree="); inorder();
		}
	}
}

////////////////////////////////////////////////////
// MAIN METHOD
////////////////////////////////////////////////////
public static void main(String[] args) {
	int n = 15;
	if (args.length>0) n = Integer.parseInt(args[0]);
	int seed = 0;
	if (args.length>1) seed = Integer.parseInt(args[1]);
	
	Random rg;
	if (seed==0) rg = new Random();
	else rg = new Random(seed);

	System.out.printf("=======> BST2: nn = %d, ss = %d\n\n", n, seed);

	ArrayList<Integer> aList = new ArrayList<Integer>();
	BST2a t = new BST2a();


	// n random additions:
	for (int i=0; i<n; i++){
	    	int a = rg.nextInt(2*n);
		t.add(a);
		System.out.printf("Added %d; ", a);
		//
		if (a%2 == 1) 
		    aList.add(new Integer(a));
	}
	//t.diag("\n ======== After n random additions: ",1);
	//System.out.printf("\nDIAG: Failed = %d\n\n", t.failed);


	t.diag("\n ========= Inorder BST: \n", 0);
	t.inorder();
	t.diag("\n ", 0);

	java.util.Iterator<Integer> it = aList.iterator();
	while (it.hasNext()) {
	    int a = (it.next()).intValue();
	    t.diag("\n Removing " + a, 0);
	    t.remove(a);
	}

	t.diag("\n ========= Inorder BST: \n", 0);
	t.inorder();
	t.diag("\n ", 0);

	// n random removals:
	/* **************************************************
	for (int i=0; i<n; i++){
	    int a = rg.nextInt(2*n);
		System.out.printf("\nTrying to remove %d; ", a);
	    if (t.remove(a))
	        t.diag("Success!", 1);
	    else
		t.diag("Failure!",0);
	}
	t.diag("\n ======== After n random removals: ",1);
	t.diag("\n",0);
	************************************************** */

}//main

}//BST2a


