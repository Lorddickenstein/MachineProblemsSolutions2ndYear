package machineProblem2pkg;
/**
 *
 * @author John Ramil Reyes
 * @author Jerson Destacamento
 * @BSCS 2-3 Aug. 22, 2019
 *
 * @side note: please compile all the files before running
 * @side note: try to include even large negative integer numbers
 * 
 */

import java.util.Scanner;

public class MP02{
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		LinkedList listA = new LinkedList();
		LinkedList listB = new LinkedList();
		String strInputA, strInputB;
		int intDigitA, intDigitB;
		
		// inputs
		System.out.print("Enter large integer number: ");
		strInputA = input.nextLine();
		System.out.print("Enter another large integer number: ");
		strInputB = input.nextLine();
		
		// convert String inputs to LinkedLists
		convertToList(strInputA, listA);
		convertToList(strInputB, listB);
		
		// count how many digits each input has
		intDigitA = listA.counter(listA.head);
		intDigitB = listB.counter(listB.head);
		
		System.out.println("Input A number of digits: " + intDigitA + "\nInput B number of digits: " + intDigitB);
		
		Node hasMoreDigits;
		Node hasLessDigits;
		LinkedList list;
		
		if (intDigitA == intDigitB || intDigitA > intDigitB) {
			list = listA;
			hasMoreDigits = listA.head;
			hasLessDigits = listB.head;
		}else {
			list = listB;
			hasMoreDigits = listB.head;
			hasLessDigits = listA.head;
		}
		
		add(hasMoreDigits, hasLessDigits, list);
		
		System.out.print("Result: ");
		list.showRev();
	}
	
	// convert the string input into a LinkedList
	public static void convertToList(String str, LinkedList list) {
		
		for (int i = str.length() - 1; i >= 0; i--)
			list.insert(Character.getNumericValue(str.charAt(i)));
	}
	
	// performs the addition
	public static void add(Node hasMoreDigits, Node hasLessDigits, LinkedList newList) {
		
		// "overflow" means "value over 9", i.e. double digits
		
		// iterate through the lesser digit input until the last digit where next node is null
		while(hasLessDigits.next != null) {
			
			// add number and store at the greater digit input
			hasMoreDigits.data += hasLessDigits.data;
			
			// check...
			checkForOverflow(newList, hasMoreDigits);
			
			// move to the next digit
			hasMoreDigits = hasMoreDigits.next;
			hasLessDigits = hasLessDigits.next;
		}
		
		// add the remaining data
		hasMoreDigits.data += hasLessDigits.data;
		
		// check...
		checkForOverflow(newList, hasMoreDigits);
		
		// iterate through the greater digit input to see if there is an overflow
		if (hasMoreDigits.next != null) {
			while(hasMoreDigits.next != null) {
				checkForOverflow(newList, hasMoreDigits);
				hasMoreDigits = hasMoreDigits.next;
			}
		}
	}
	

	// check for overflow  
	public static void checkForOverflow(LinkedList l, Node node) {
		
		if (node.data >= 10) {
			// subtract 10 to current digit to preserve a singular digit
			node.data -= 10;
			
			// add 1 to the next digit
			if (node.next != null)
				node.next.data += 1;
				
			// declare another node as a new digit with value of 1
			else
				l.insert(1);
		}
	}
}