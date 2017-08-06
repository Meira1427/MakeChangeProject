/*In the cash register we will calculate the amount of change returned to a customer 
 * based on the purchase price and the amount tendered. We will also notify the 
 * attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) 
 * is needed to make the change for the customer. Change should be provided using the largest 
 * bill and coin denominations as possible. Denominations that are not used should not 
 * be displayed.*/

package money;

import java.util.Scanner;

public class MakeChange {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		double cost, amountTendered, changeDue;
		
		/* int [] changeBreakdown
		 * [0] numTwenty, [1] numTen, [2] numFive, [3]numOne;
		 * [4] numQuarter, [5] numDime, [6] numNickel, [7] numPenny;
		 */
		int [] changeBreakdown = new int[8];
		
		/* double[] currencyValue for values; same indexes as changeBreakdown */
		double[] currencyValue = {20.0, 10.0, 5.0, 1.0, .25, .10, .05, .05};
		
		/* Prompt for cost and round */
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		cost = roundDouble(cost);
		
		/* Prompt for amount tendered and verify it is enough; then round*/
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = verifyCash(keyboard, amountTendered, cost);
		amountTendered = roundDouble(amountTendered);
		
		/* Calculate change due */
		changeDue = getChangeDue(amountTendered, cost);
		
		System.out.println(cost + "\t" + amountTendered + "\t" + changeDue);
		
		keyboard.close();
		
	}
	
	/* 
	 * getCleanDouble forces user to enter only a number; returns a double
	 */
	public static double getCleanDouble(Scanner sc, String prompt) {
		System.out.print(prompt);
		while(!sc.hasNextDouble()) {
			System.out.println(prompt);
			sc.next();
		}
		return sc.nextDouble();
	}
	
	/*
	 * roundDouble - to tone down those extra decimal places.
	 */
	public static double roundDouble(double num) {
		return Math.floor(num*100)/100;
	}
	
	/*
	 * verifyCash takes amount tendered and cost and continues looping with error
	 * message if amount tendered is too low. If it is good the first time around,
	 * user won't see error message
	 */
	public static double verifyCash(Scanner sc, double amount, double cost) {
		while (amount-cost < 0) {
			amount = getCleanDouble(sc, "That's not enough. Please enter enough to cover cost: ");
		}
		return amount;
	}
	
	public static double getChangeDue(double cash, double cost) {
		double answer = cash - cost;
		answer = roundDouble(answer);
		return answer;
	}

}
	

