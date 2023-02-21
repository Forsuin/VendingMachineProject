import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class CoinPool {
    Map<Coin, Integer> coins = new HashMap<>(Map.of(
            Coin.PENNY, 10,
            Coin.NICKEL, 10,
            Coin.DIME, 10,
            Coin.QUARTER, 10
    ));


    public int getNumCoins(Coin coin){
        Integer amount = coins.get(coin);

        if(amount == null){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in CoinPool", coin.toString()));
        }

        return amount;
    }

    public void addCoins(Coin coin, int amount){
        if(!coins.containsKey(coin)){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in CoinPool", coin.toString()));
        }

        int newAmount = coins.get(coin) + amount;

        coins.put(coin, Math.min(newAmount, 10));
    }

    public void removeCoins(Coin coin, int amount){
        if(!coins.containsKey(coin)){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in CoinPool", coin.toString()));
        }

        int newAMount = coins.get(coin) + amount;

        coins.put(coin, Math.max(newAMount, 0));
    }
}
