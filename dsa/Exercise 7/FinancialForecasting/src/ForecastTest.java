public class ForecastTest {

    public static void main(String[] args) {

        System.out.println("Financial Forecasting Tool");
        System.out.println("\n");

        double presentValue = 10000.0;
        double growthRate   = 0.08;
        int    years        = 10;

        System.out.printf("Present Value : Rs. %.2f%n", presentValue);
        System.out.printf("Growth Rate   : %.0f%%%n", growthRate * 100);
        System.out.printf("Years         : %d%n%n", years);

        System.out.println("Year-by-Year Forecast (Recursive):");
        for (int y = 0; y <= years; y++) {
            double fv = FinancialForecast.futureValueRecursive(presentValue, growthRate, y);
            System.out.printf("Year %2d -> Rs. %10.2f%n", y, fv);
        }

        System.out.println();
        double recursiveResult = FinancialForecast.futureValueRecursive(presentValue, growthRate, years);
        System.out.printf("Recursive  Future Value after %d years: Rs. %.2f%n", years, recursiveResult);

        FinancialForecast.clearMemo();
        double memoizedResult = FinancialForecast.futureValueMemoized(presentValue, growthRate, years);
        System.out.printf("Memoized   Future Value after %d years: Rs. %.2f%n", years, memoizedResult);

        double iterativeResult = FinancialForecast.futureValueIterative(presentValue, growthRate, years);
        System.out.printf("Iterative  Future Value after %d years: Rs. %.2f%n%n", years, iterativeResult);

        System.out.println("Performance: Large n (n=5000)");

        FinancialForecast.clearMemo();
        long start = System.nanoTime();
        FinancialForecast.futureValueMemoized(presentValue, growthRate, 5000);
        long end = System.nanoTime();
        System.out.println("Memoized  time: " + (end - start) + " ns");

        start = System.nanoTime();
        FinancialForecast.futureValueIterative(presentValue, growthRate, 5000);
        end = System.nanoTime();
        System.out.println("Iterative time: " + (end - start) + " ns");

        System.out.println("\nComplexity Analysis");
        System.out.println("Plain Recursive : Time O(n) | Space O(n) call stack");
        System.out.println("Memoized        : Time O(n) | Space O(n) memo table ");
        System.out.println("Iterative       : Time O(n) | Space O(1) ");
        System.out.println("\nConclusion: For large n, use Iterative or Memoized to avoid");
        System.out.println("StackOverflowError caused by deep recursion.");
    }
}
