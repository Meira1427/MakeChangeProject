# Make Change Project
## Week 1 homework for Skill Distillery

### Summary

In this project we were to prompt the user to give us:
1. the cost of the item.
2. cash tendered

Output:
1. If the cash tendered is less than expected they will see an error message.
2. If the cash is exact, a message to this effect.
3. If change is due, they will see a message telling them their change due as well as 
how that change is broken down into # of twenties, tens, fives, ones, quarters, dimes,
nickels, and pennies.

### Goals

1. Must pass these test cases:
Amount: .67, Tendered: .50, Result: Error message
Amount: .67, Tendered: 1.00, Result: 1 quarter, 1 nickel, 3 pennies.
Amount: .59, Tendered: 1.00, Result: 1 quarter, 1 dime, 1 nickel, 1 penny.
Amount: 3.96, Tendered: 20.00, Result: 1 ten, 1 five, 1 one, 4 pennies.

2. I would like to use arrays and methods in this solution.

### Methods

getCleanDouble(Scanner sc, String prompt)
	Loops until user actually enters a number that can be read as a double.
	Returns a double.
	
verifyCash(Scanner sc, double amount, double cost)
	Gives user an error message if amount tendered (amount) is less than cost.
	Loops until user enters an amount equal to or more than cost.
	Returns a double. This is new amount tendered.
	
getChangeDue(double cash, double cost)
	calculates cash minus cost
	Returns a Big Decimal
	
getChangeBreakdown(BigDecimal change)
	Calculates # of a certain denomination due back to customer.
	changeDue divided by value of currency
	changeDue then becomes remainder of changeDue%value-of-currency
	loop through array from 20s to pennies
	Array will be in this order Number of: {20s, 10s, 5s, 1s, quarters, dimes, nickels, pennies}
	Returns an array of Big Decimals, the number of each denomination to be returned
	
printChangeDue (BigDecimal[] changeArr, BigDecimal change)
	Loops through array and prints out number of each denomination to be returned
	1. prints nothing if "0"
	2. prints in singular language if "1"
	3. prints in plural language if plural
	
askUserPlayAgain(Scanner sc)
	Prompts if player wants to purchase another item.
	Loops until player enters y, yes, Y, YES, n, no, N, or NO
	Then returns true if y, yes, Y, YES
	otherwise returns false
	
### What I Learned

I did a version of this exercise during my pre-work when I finished the 'Learn Java by 
the Byte' Udemy course given to me before the Skill Distillery assessment test. At that 
time, I found working with doubles and currency very frustrating. If I imported rounding 
and used the RoundingMode.HALF_UP with doubles instead of Math.floor, I may have been able 
to get it to work, but I encountered a lot of people on Stack Overflow recommending 
the BigDecimal class when working with money to get the nice round numbers that always 
had two decimal places, so that is the route I used during pre-work. When I noticed that 
I was failing the test cases during this exercise, I decided to go for the BigDecimal 
solution rather than a rounding solution. 

### Author and Date
Meira Pentermann
August 6, 2017