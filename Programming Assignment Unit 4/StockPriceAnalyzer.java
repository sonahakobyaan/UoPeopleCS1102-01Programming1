import java.util.ArrayList;
import java.util.Arrays;

/**
 * StockPriceAnalyzer performs common analytical operations on a set
 * of daily stock prices, using both an array and an ArrayList to
 * compare the two data structures.
 */
public class StockPriceAnalyzer {

    /**
     * Calculates the average stock price using an array.
     * @param prices an array of stock prices
     * @return the average price
     */
    public static double calculateAveragePrice(float[] prices) {
        float total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i];
        }
        return total / prices.length;
    }

    /**
     * Calculates the average stock price using an ArrayList.
     * @param prices an ArrayList of stock prices
     * @return the average price
     */
    public static double calculateAveragePrice(ArrayList<Float> prices) {
        float total = 0;
        for (float price : prices) {
            total += price;
        }
        return total / prices.size();
    }

    /**
     * Finds the maximum stock price using an array.
     * @param prices an array of stock prices
     * @return the maximum price
     */
    public static float findMaximumPrice(float[] prices) {
        float max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        return max;
    }

    /**
     * Finds the maximum stock price using an ArrayList.
     * @param prices an ArrayList of stock prices
     * @return the maximum price
     */
    public static float findMaximumPrice(ArrayList<Float> prices) {
        float max = prices.get(0);
        for (float price : prices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    /**
     * Counts how many times a target price occurs in an array.
     * @param prices an array of stock prices
     * @param targetPrice the price to search for
     * @return the number of occurrences of targetPrice
     */
    public static int countOccurrences(float[] prices, float targetPrice) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == targetPrice) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes the cumulative sum of stock prices using an ArrayList.
     * @param prices an ArrayList of stock prices
     * @return a new ArrayList containing the running total at each position
     */
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumulativeSums = new ArrayList<Float>();
        float runningTotal = 0;
        for (int i = 0; i < prices.size(); i++) {
            runningTotal += prices.get(i);
            cumulativeSums.add(runningTotal);
        }
        return cumulativeSums;
    }

    public static void main(String[] args) {

        // 10 days of opening stock prices, stored in an array of float
        float[] priceArray = { 102.5f, 105.0f, 101.75f, 108.25f, 105.0f,
                                110.5f, 107.0f, 103.25f, 105.0f, 109.75f };

        // The same data, stored in an ArrayList of Float
        ArrayList<Float> priceList = new ArrayList<Float>();
        for (float price : priceArray) {
            priceList.add(price);
        }

        System.out.println("=== Stock Price Analysis ===\n");

        System.out.println("Array of prices:     " + Arrays.toString(priceArray));
        System.out.println("ArrayList of prices: " + priceList);
        System.out.println();

        // Average price
        System.out.printf("Average price (array):     %.2f%n", calculateAveragePrice(priceArray));
        System.out.printf("Average price (ArrayList): %.2f%n", calculateAveragePrice(priceList));
        System.out.println();

        // Maximum price
        System.out.printf("Maximum price (array):     %.2f%n", findMaximumPrice(priceArray));
        System.out.printf("Maximum price (ArrayList): %.2f%n", findMaximumPrice(priceList));
        System.out.println();

        // Occurrence count
        float target = 105.0f;
        int occurrences = countOccurrences(priceArray, target);
        System.out.println("Occurrences of " + target + " in array: " + occurrences);
        System.out.println();

        // Cumulative sum
        ArrayList<Float> cumulativeSums = computeCumulativeSum(priceList);
        System.out.println("Cumulative sum (ArrayList): " + cumulativeSums);
    }
}