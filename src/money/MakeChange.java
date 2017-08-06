/*In the cash register we will calculate the amount of change returned to a customer 
 * based on the purchase price and the amount tendered. We will also notify the 
 * attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) 
 * is needed to make the change for the customer. Change should be provided using the largest 
 * bill and coin denominations as possible. Denominations that are not used should not 
 * be displayed.
 * 
 * Saving this version and uploading to GitHub. It works except for rounding error
 * Need to rewrite with Big Decimal*/

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
		
		
		/* Prompt for cost and round */
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		cost = roundDouble(cost);
		
		/* Prompt for amount tendered and verify it is enough; then round*/
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = verifyCash(keyboard, amountTendered, cost);
		//amountTendered = roundDouble(amountTendered);
		
		/* Calculate change due */
		changeDue = getChangeDue(amountTendered, cost);
		
		changeBreakdown = getChangeBreakdown(amountTendered, cost);
		printChangeDue(changeBreakdown, changeDue);
		
		keyboard.close();
		
	}
	
	/* 
	 * getCleanDouble forces user to enter only a number; returns a double
	 */
	public static double getCleanDouble(Scanner sc, String prompt) {
		System.out.print(prompt);
		while(!sc.hasNextDouble()) {
			System.out.print(prompt);
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
	
	/*
	 * getChangeDue just calculates the total amount
	 */
	public static double getChangeDue(double cash, double cost) {
		double answer = cash - cost;
		answer = roundDouble(answer);
		return answer;
	}
	
	/*
	 * getChangeBreakdown returns an int array with the number of 20s, 10s, 5s
	 * 1s, quarters, dimes, nickels and pennies to return to user in the change due.
	 * Array is in this order {20, 10, 5, 1, quarter, dime, nickel, penny}
	 * divides change due by currency value, stores that in int array
	 * then adjusts change due to change%currencyValue and moves to next currencyValue
	 */
	
	public static int[] getChangeBreakdown(double cash, double cost) {
		int[] answer = new int[8]; //initialize array to return
		double change = getChangeDue(cash, cost); //calculate change
		/* double[] currencyValue for values; same indexes as changeBreakdown */
		double[] currencyValue = {20.0, 10.0, 5.0, 1.0, .25, .10, .05, .01};
		for (int i = 0; i < answer.length; i++) {
			answer[i] = (int)(change/currencyValue[i]);
			change = change%currencyValue[i];
			//System.out.println(i + ":  " + answer[i] + "\t" + currencyValue[i] + "\tchange: " + change);
		}
		return answer;
	}
	
	public static void printChangeDue (int[] changeArr, double change) {
		String [] values = {"Twenties", "Tens", "Fives", "Ones", 
							"Quarters", "Dimes", "Nickels", "Pennies"};
		String [] value = {"Twenty", "Ten", "Five", "One", "Quarter", "Dime", "Nickel", "Penny"};
		System.out.println("Your change due is " + change + ". Here is:");
		for (int i = 0; i < values.length; i++) {
			if (changeArr[i] == 0) {
			} // print nothing if this denomination isn't returned
			else if (changeArr[i] == 1) {
				System.out.println(changeArr[i] + " " + value[i]);
			}
			else {
				System.out.println(changeArr[i] + " " + values[i]);
			}
		}
	}

}
	

