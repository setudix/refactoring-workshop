package workshop;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
    public static String say(int number) {

        if (canDivide(number, 15))
            return "FizzBuzz";
        else if (canDivide(number, 3))
            return "Fizz";
        else if (canDivide(number, 5))
            return "Buzz";
        else
            return String.valueOf(number);

    }

    public static boolean canDivide(int dividend, int divisor){
        if (dividend % divisor == 0)
            return true;
        else
            return false;
    }
}
