/*In the cash register we will calculate the amount of change returned to a customer 
 * based on the purchase price and the amount tendered. We will also notify the 
 * attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) 
 * is needed to make the change for the customer. Change should be provided using the largest 
 * bill and coin denominations as possible. Denominations that are not used should not 
 * be displayed.
 * 
 * Rounding errors occur when using double; import Big Decimal. I did this in prework 
 * JavaByTheByte. Referring to my prework solution and modifying to include arrays*/

package money;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MakeChange {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		/* Take input in doubles, so I can work with Scanner class */
		double cost, amountTendered;
		
		/* Use big decimal to avoid rounding losses that occur with doubles */
		BigDecimal changeDue;
		
		/* BigDecimal [] changeBreakdown. Will be stored in this order
		 * [0] numTwenty, [1] numTen, [2] numFive, [3]numOne;
		 * [4] numQuarter, [5] numDime, [6] numNickel, [7] numPenny;
		 */
		BigDecimal[] changeBreakdown;
		
		/* Prompt for cost */
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		
		/* Prompt for amount tendered and verify it is enough*/
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = verifyCash(keyboard, amountTendered, cost);
		
		/* Calculate change due. This is getting returned as Big Decimal */
		changeDue = getChangeDue(amountTendered, cost);
		
		/*Returns an array of BigDecimals, the number of each denomination in change */
		changeBreakdown = getChangeBreakdown(changeDue);
		
		/*Prints out message regarding change due & # of each denominations */
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
	 * getChangeBreakdown returns a BigDecimal array with the number of 20s, 10s, 5s
	 * 1s, quarters, dimes, nickels and pennies to return to user in the change due.
	 * Array is in this order {20, 10, 5, 1, quarter, dime, nickel, penny}
	 * divides change due by currency value, stores that in BigDecimal array
	 * then adjusts change due to change%currencyValue and moves to next currencyValue
	 */
	public static BigDecimal[] getChangeBreakdown(BigDecimal change) {
		BigDecimal[] answer = new BigDecimal[8]; //initialize array to return
		/* BigDecimal[] currencyValue for values; same indexes as changeBreakdown */
		BigDecimal[] currencyValue = new BigDecimal[8];
		currencyValue[0] = 	new BigDecimal("20.00");
		currencyValue[1] = 	new BigDecimal("10.00");
		currencyValue[2] = 	new BigDecimal("5.00");
		currencyValue[3] = 	new BigDecimal("1.00");
		currencyValue[4] = 	new BigDecimal("0.25");
		currencyValue[5] = 	new BigDecimal("0.10");
		currencyValue[6] = 	new BigDecimal("0.05");
		currencyValue[7] = 	new BigDecimal("0.01");
		/* iterate through array, counting # of each denomination to return
		 * Do this by dividing change due by currency value (BigDecimal .divide)
		 * then getting remainder with the BigDecimal mod% method .remainder */
		for (int i = 0; i < answer.length; i++) {
			answer[i] = change.divide(currencyValue[i]).setScale(0, RoundingMode.FLOOR);
			change = change.remainder(currencyValue[i]);
		}
		return answer;
	}
	
	/*
	 * printChangeDue iterates through array and 
	 * prints nothing if # to return is zero
	 * prints singular version of word if # to return is one
	 * prints plural version of word if # to return is plural
	 */
	public static void printChangeDue (BigDecimal[] changeArr, BigDecimal change) {
		String [] values = {"Twenties", "Tens", "Fives", "Ones", 
							"Quarters", "Dimes", "Nickels", "Pennies"};
		String [] value = {"Twenty", "Ten", "Five", "One", "Quarter", "Dime", "Nickel", "Penny"};
		System.out.println("Your change due is " + change + ". Here is:");
		for (int i = 0; i < values.length; i++) {
			if (changeArr[i].equals(BigDecimal.valueOf(0))){
			} // print nothing if this denomination isn't returned
			else if (changeArr[i].equals(BigDecimal.valueOf(1))) {
				System.out.println(changeArr[i] + " " + value[i]);
			}
			else {
				System.out.println(changeArr[i] + " " + values[i]);
			}
		}
		System.out.println("Thank you for your business!");
	}

}
	

