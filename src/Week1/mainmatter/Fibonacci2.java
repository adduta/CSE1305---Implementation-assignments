package Week1.mainmatter;

class Fibonacci2 {
    /**
     * Computes the nth number in the Fibonacci sequence.
     * @param n - the index of the number in the Fibonacci sequence.
     * @return nth number in the Fibonacci sequence
     */
    public static int fibonacci(int n) {
        int acc1 = 1;
        int acc2 = 1;
        return fibonacci_helper(n, acc1, acc2);
    }

    /**
     * Helper function for computing the nth number in the Fibonacci sequence.
     * @param n - the index of the number in the Fibonacci sequence.
     * @param acc1 - accumulator for the previous number in the Fibonacci sequence.
     * @param acc2 - accumulator for the number before the previous one in the Fibonacci sequence.
     * @return
     */
    public static int fibonacci_helper(int n, int acc1, int acc2) {
        if ( n == 0 ) return 0;
        if ( n == 1 ) return acc1;
        if ( n == 2 ) return acc2;
        return fibonacci_helper(n-1, acc2, acc1 + acc2);
    }
}
