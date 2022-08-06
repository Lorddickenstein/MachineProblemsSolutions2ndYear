package machineProblem2pkg;

/*
 * 
 * Classes: 
 * 		Node, LinkedList
 * 
 */

class Node{
	int data;
	Node next;
	Node prev;
}


public class LinkedList{

	Node head;
	Node tail;
	
	public void insert(int data){
		
		// initialize node
		Node node = new Node();
		node.data = data;
		node.next = null;
		
		// if first insert make node head
		if(head == null){
			head = node;
			head.prev = null;
		
		}else{
			Node current = head;
			while(current.next != null){
				current.next.prev = current;
				current = current.next;
			}
			current.next = node;
			node.prev = current;
		}
		tail = node;
	}
	
	// prints the content according to how it was inserted in the list
	public void show(){
		
		Node current = head;
		while(current.next != null){
			System.out.print(current.data);
			current = current.next;
		}
		System.out.println(current.data);
	}
	
	//returns the number of digit 
	public int counter(Node head){
		
		int ctr = 1;
		Node current = head;
		while(current.next != null){
			current=current.next;
			ctr++;
		}
		return ctr;
	}
	
	//shows the reverse order of number
	public void showRev(){
		
		Node current = tail;
		while(current.prev != null){
			System.out.print(current.data);
			current = current.prev;
		}
		System.out.print(current.data);
	}
	

}