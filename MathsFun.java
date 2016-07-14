import java.math.BigInteger;

public class MathsFun {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger EIGHT = BigInteger.valueOf(8);

    public static void main(String[] args) {
        int numNumbers = Integer.parseInt(args[0]);
        int numDigits = Integer.parseInt(args[1]);
        System.out.println(String.format("Generating %d square-triangle numbers with at least %d digits", numNumbers, numDigits));

        // Get least square number greater than or equal to 10^numDigits-1
        BigInteger min = BigInteger.TEN.pow(numDigits-1);
        BigInteger i = BigInteger.ONE;

        while (i.pow(2).compareTo(min) < 0) {
            i = i.add(BigInteger.ONE);
        }

        int numbersGenerated = 0;
        while (numbersGenerated < numNumbers) {
           if (isNumberTriangle(i.pow(2))) {
               numbersGenerated++;
               System.out.println(String.format("Square-triangle #%d: \t%s", numbersGenerated, i.pow(2).toString()));
           }
           i = i.add(BigInteger.ONE);
        }

        System.out.println("Done.");
    }

    public static boolean isNumberTriangle(BigInteger candidate) {
        // If n is triangular, then 8n+1 is square
        return isNumberSquare(candidate.multiply(EIGHT).add(BigInteger.ONE));
    }

    public static boolean isNumberSquare(BigInteger candidate) {
        return integerSqrt(candidate).pow(2).compareTo(candidate) == 0;
    }

    // Code taken from http://stackoverflow.com/questions/2685524/check-if-biginteger-is-not-a-perfect-square
    public static BigInteger integerSqrt(BigInteger n) {
        if (n.signum() >= 0) {
            final int bitLength = n.bitLength();
            BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

            while (!isIntegerSqrt(n, root)) {
                root = root.add(n.divide(root)).divide(TWO);
            }

            return root;
        }
        else
        {
            throw new ArithmeticException("square root of negative number");
        }
    }

    private static boolean isIntegerSqrt(BigInteger n, BigInteger root) {
        final BigInteger lowerBound = root.pow(2);
        final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);
        return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
    }
}
