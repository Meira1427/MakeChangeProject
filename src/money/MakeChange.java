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
		double cost, amountTendered;
		/* 
		 * Change this to int array
		 * numTwenty, numTen, numFive, numOne;
		 * numQuarter, numDime, numNickel, numPenny;
		 */
		int [] changeBreakdown = new int[8];
		
		cost = getCleanDouble(keyboard, "Enter the cost of your item: ");
		cost = roundDouble(cost);
		amountTendered = getCleanDouble(keyboard, "Enter amount tendered: ");
		amountTendered = roundDouble(amountTendered);
		System.out.println(cost + "\t" + amountTendered);
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

}
	

