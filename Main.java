/* (Financial: credit card number validation) Credit card numbers follow certain patterns.
 * A credit card number must have between 13 and 16 digits. It must start with:
 *
 * ■ 4 for Visa cards
 * ■ 5 for Master cards
 * ■ 37 for American Express cards
 * ■ 6 for Discover cards
 *
 * In 1954, Hans Luhn of IBM proposed an algorithm for validating credit card
 * numbers. The algorithm is useful to determine whether a card number is entered
 * correctly or whether a credit card is scanned correctly by a scanner. Credit card
 * numbers are generated following this validity check, commonly known as the
 * Luhn check or the Mod 10 check, which can be described as follows (for illustration,
 * consider the card number 4388576018402626):
 *
 * 1. Double every second digit from right to left. If doubling of a digit results in a
 *    two-digit number, add up the two digits to get a single-digit number.
 *
 * 2. Now add all single-digit numbers from Step 1.
 *                 4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37
 *
 * 3. Add all digits in the odd places from right to left in the card number.
 *                 6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38
 *
 * 4. Sum the results from Step 2 and Step 3.
 *                 37 + 38 = 75
 *
 * 5. If the result from Step 4 is divisible by 10, the card number is valid; otherwise,
 *    it is invalid. For example, the number 4388576018402626 is invalid, but the
 *    number 4388576018410707 is valid.
 *
 * Write a program that prompts the user to enter a credit card number as a long
 * integer. Display whether the number is valid or invalid. Design your program to
 * use the following methods:
 *
 *         /** Return true if the card number is valid
 *         public static boolean isValid(long number)-done
 *
 *         /** Get the result from Step 2 */
/*         public static int sumOfDoubleEvenPlace(long number)-done
 *
 *         /** Return this number if it is a single digit, otherwise,
 *         * return the sum of the two digits */
/*         public static int getDigit(int number)-done
 *
 *         /** Return sum of odd-place digits in number */
/*         public static int sumOfOddPlace(long number)-done
 *
 *         /** Return true if the digit d is a prefix for number */
/*         public static boolean prefixMatched(long number, int d)
 *
 *         /** Return the number of digits in d */
/*         public static int getSize(long d)-done
 *
 *         /** Return the first k number of digits from number. If the
 *         * number of digits in number is less than k, return number. */
/*         public static long getPrefix(long number, int k)
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a number- ");
        long number = scanner.nextLong();

        System.out.println(number+" is "+(isValid(number) ? "valid":"invalid"));

        //System.out.println("sum of odd places "+sumOfOddPlaces(number));
        //System.out.println("sum of even places "+sumOfDoubleEvenPlaces(number));
        //boolean a =(((sumOfOddPlaces(number)+sumOfDoubleEvenPlaces(number))%10)==0);
        //System.out.println("Is sum of odd and even places divisible by 10- "+a);
        //System.out.println("is prefix found -"+prefixMatched(number,4));
    }
    public static boolean isValid(long number){
        boolean valid = (get_size(number)>=13 && get_size(number)<=16);
        boolean checksum = ((sumOfOddPlaces(number) + sumOfDoubleEvenPlaces(number)) %10 == 0);
        boolean validprefix = prefixMatched(number,4)|| prefixMatched(number,5)||
                prefixMatched(number,37)|| prefixMatched(number,6);

        return valid && checksum && validprefix;
    }
    public static int sumOfOddPlaces(long number){
        int sum=0;
        for (int i=1;number>0;i+=2){
            sum+=number%10;
            number/=100;
        }
        return  sum;
    }
    public static int sumOfDoubleEvenPlaces(long number){
        int sum=0;
        number=number/10;
        for (int i=2;number>0;i+=2){
            sum+=getdigit((int) (2* ( number % 10)));
            number/=100;
        }
        return  sum;
    }
    public static int getdigit(int number){
        if (number>9)
            return (number/10) + (number%10);
        return number;
    }
    public static int get_size(long number){
        //int length
        //for (int i=number;i>0;number/10,length++) return length

        int length = 0;
        while (number>0) {
            length++;
            number/=10;
        }
        return length;
    }

    public static boolean prefixMatched(long number, int d){
        return getprefix(number,get_size(d))==d;
    }
    public static long getprefix(long number,int k){
        if (get_size(number)<k)
            return number;
        return (long)(number/Math.pow(10,(get_size(number)-k)));

    }
}