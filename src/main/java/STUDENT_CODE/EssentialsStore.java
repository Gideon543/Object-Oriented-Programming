package STUDENT_CODE;
/**
 * @author Phiwayinkhosi P. Lukhele
 * @date 3/02/2024
 * @reference https://codereview.stackexchange.com/questions/118006/converting-money-into-change
 **/

import java.util.Scanner;

public class EssentialsStore {
    public static void main (String[]args){ // main method that takes in user inputs
        Scanner input = new Scanner (System.in);



        System.out.println("Please enter the cost of items purchased : ");
        double costOfItemsPurchased = input.nextDouble();

        System.out.println("Please enter the amount paid : ");
        double amountPaid = input.nextDouble();

        //checking if the user has paid enough money before calculating the change
        if (amountPaid<costOfItemsPurchased) {
            System.out.println("Error : Amount paid is less that total cost.");
            return ;
        }
        //Printing the breakdown of the change into the Ghanaian denominations.
        System.out.println("The change in denominations is : " + computeChangeBreakdown(costOfItemsPurchased,amountPaid));

        System.out.println("Thank you!");
    }

    /**
     * A method that prints out the total change.
     *      Converts the calculated change from cedis to pesewas for uniformity and easy calculation.
     *      Then it has two arrays created in it, the first holds all the denomination in pesewas, the second holds string names that match to each item in the other array
     *      A for loop is then used to itarate througgh the firt array and each time the values are assigned to the ones in the secod array through a variable
     *@param  remainingChange stores remainders from the division of the change with each value in the first array which is done repeatedly
     * A conditional is used to make sure no 0 is printed out or returned
     * @return result of concatenating the number of pesewas and the string of denomination names
     */

    public static String computeChangeBreakdown  (double cost, double cash){
        String result= "";
        char ghanaCedi= '\u20B5';

        double change = cash - cost;
        System.out.printf("Total Change is Gh:  %.2f%n  ", change);

        int remainingChange = (int) (change*100);

        int [] denominations = {20000,10000,5000,2000,1000,500,100,20,10,5,1};

        String [] denominationNames = {"200", "100", "50", "20", "10", "5", "1", "20p", "10p", "5p", "1p"};

        for (int i= 0; i<denominations.length;i++ ) // loops through the two arrays in an almost simulteneous way
        {
            int currentDenomination = denominations[i];

            String currentDenominationName = ghanaCedi + denominationNames[i];

            int numberOfCoins =  remainingChange/currentDenomination; // division of change with each denomination

            remainingChange %=currentDenomination;


            if (numberOfCoins!=0){  // checks if the number of coins is not equals to zero before returning it

                result+= numberOfCoins + " " + currentDenominationName + "\n";

            }
        }


        return result; // return the final result since the method is not void


    }
}
