public class Loop {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long sum = 0;
        for (long i = 0; i < 1_000_000_000L; i++) {
            sum += i;
        }
        long endTime = System.nanoTime();
        System.out.println("Sum: " + sum);
        System.out.println("Execution Time: " + (endTime - startTime));
    }
}
