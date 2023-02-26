import java.util.EnumMap;
import java.util.Map;

/**
 * Manages all the coins in the vending machine
 */
public class CoinPool {
    Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

    /**
     * Creates new instance of class
     */
    CoinPool() {
        coins.put(Coin.PENNY, 10);
        coins.put(Coin.NICKEL, 10);
        coins.put(Coin.DIME, 10);
        coins.put(Coin.QUARTER, 10);
    }

    /**
     * Increments quanity of coin
     * @param coin Type of coin to increment
     */
    public void add(Coin coin) {
        coins.put(coin, coins.get(coin) + 1);
    }

    /**
     * Removes all coins of type from pool
     * @param coin Type of coin to remove
     */
    public void remove(Coin coin) {
        coins.put(coin, 0);
    }

    /**
     * Gets the number of coins from pool
     * @param coin Type of coin to get number of
     * @return int, Number of coins
     */
    public int getNumCoin(Coin coin) {
        return coins.get(coin);
    }
}
