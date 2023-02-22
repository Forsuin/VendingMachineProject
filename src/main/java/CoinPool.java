import java.util.EnumMap;
import java.util.Map;

public class CoinPool {
    Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

    CoinPool(){
        coins.put(Coin.PENNY, 10);
        coins.put(Coin.NICKEL, 10);
        coins.put(Coin.DIME, 10);
        coins.put(Coin.QUARTER, 10);
    }

    public void add(Coin coin){
        coins.put(coin, coins.get(coin) + 1);
    }
}
