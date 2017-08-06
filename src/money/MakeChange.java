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
		
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		cost = roundDouble(cost);
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = verifyCash(keyboard, amountTendered, cost);
		amountTendered = roundDouble(amountTendered);
		
		System.out.println(cost + "\t" + amountTendered);
		
		keyboard.close();
		
	}
	
	public static double getCleanDouble(Scanner sc, String prompt) {
		System.out.print(prompt);
		while(!sc.hasNextDouble()) {
			System.out.println(prompt);
			sc.next();
		}
		return sc.nextDouble();
	}
	
	public static double roundDouble(double num) {
		return Math.floor(num*100)/100;
	}
	
	public static double verifyCash(Scanner sc, double amount, double cost) {
		while (amount-cost < 0) {
			amount = getCleanDouble(sc, "That's not enough. Please enter enough to cover cost: ");
		}
		return amount;
	}
	
//	public static double getChangeDue(double cash, double cost) {
//		double answer = cash - cost;
//		
//	}

}
	

