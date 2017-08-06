/*In the cash register we will calculate the amount of change returned to a customer 
 * based on the purchase price and the amount tendered. We will also notify the 
 * attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) 
 * is needed to make the change for the customer. Change should be provided using the largest 
 * bill and coin denominations as possible. Denominations that are not used should not 
 * be displayed.
 * 
 * Rounding errors with double; import Big Decimal. I did this in prework JavaByTheByte
 * Referring to my prework solution and modifying to include arrays*/

package money;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MakeChange {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		double cost, amountTendered;
		BigDecimal changeDue;
		BigDecimal[] changeBreakdown;
		
		/* int [] changeBreakdown
		 * [0] numTwenty, [1] numTen, [2] numFive, [3]numOne;
		 * [4] numQuarter, [5] numDime, [6] numNickel, [7] numPenny;
		 */
		
		/* Prompt for cost */
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		
		/* Prompt for amount tendered and verify it is enough*/
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = verifyCash(keyboard, amountTendered, cost);
		
		/* Calculate change due */
		changeDue = getChangeDue(amountTendered, cost);
		
		changeBreakdown = getChangeBreakdown(amountTendered, cost);
		printChangeDue(changeBreakdown, changeDue);
		
//		for (int i = 0; i < changeBreakdown.length; i++) {
//			System.out.println(changeBreakdown[i]);
//		}
		
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
	 * getChangeDue just calculates the total amount due back converted to BigDecimal
	 */
	public static BigDecimal getChangeDue(double cash, double cost) {
		BigDecimal answer = BigDecimal.valueOf(cash-cost);
		answer = answer.setScale(2, RoundingMode.HALF_UP);
		return answer;
	}
	
	/*
	 * getChangeBreakdown returns an int array with the number of 20s, 10s, 5s
	 * 1s, quarters, dimes, nickels and pennies to return to user in the change due.
	 * Array is in this order {20, 10, 5, 1, quarter, dime, nickel, penny}
	 * divides change due by currency value, stores that in int array
	 * then adjusts change due to change%currencyValue and moves to next currencyValue
	 */
	
	public static BigDecimal[] getChangeBreakdown(double cash, double cost) {
		BigDecimal[] answer = new BigDecimal[8]; //initialize array to return
		BigDecimal change = getChangeDue(cash, cost); //calculate change
		/* double[] currencyValue for values; same indexes as changeBreakdown */
		BigDecimal[] currencyValue = new BigDecimal[8];
		currencyValue[0] = 	new BigDecimal("20.00");
		currencyValue[1] = 	new BigDecimal("10.00");
		currencyValue[2] = 	new BigDecimal("5.00");
		currencyValue[3] = 	new BigDecimal("1.00");
		currencyValue[4] = 	new BigDecimal("0.25");
		currencyValue[5] = 	new BigDecimal("0.10");
		currencyValue[6] = 	new BigDecimal("0.05");
		currencyValue[7] = 	new BigDecimal("0.01");
		for (int i = 0; i < answer.length; i++) {
			answer[i] = change.divide(currencyValue[i]).setScale(0, RoundingMode.FLOOR);
			change = change.remainder(currencyValue[i]);
		}
		return answer;
	}
	
	public static void printChangeDue (BigDecimal[] changeArr, BigDecimal change) {
		String [] values = {"Twenties", "Tens", "Fives", "Ones", 
							"Quarters", "Dimes", "Nickels", "Pennies"};
		String [] value = {"Twenty", "Ten", "Five", "One", "Quarter", "Dime", "Nickel", "Penny"};
		System.out.println("Your change due is " + change + ". Here is:");
		for (int i = 0; i < values.length; i++) {
			if (changeArr[i].equals(BigDecimal.valueOf(0.0))){
			} // print nothing if this denomination isn't returned
			else if (changeArr[i].equals(BigDecimal.valueOf(1.0))) {
				System.out.println(changeArr[i] + " " + value[i]);
			}
			else {
				System.out.println(changeArr[i] + " " + values[i]);
			}
		}
	}

}
	

