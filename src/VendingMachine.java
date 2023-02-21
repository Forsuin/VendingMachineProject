import java.time.zone.ZoneRulesException;
import java.util.Stack;

public class VendingMachine {
    private CoinPool coins;
    private Inventory items;
    private VendingMachineState state;
    private double inputCoinAmount;
    private String password;

    public VendingMachine(){
        coins = new CoinPool();
        items = new Inventory();
        state = VendingMachineState.Customer;
        this.password = "Password";
    }

    public VendingMachine(String password){
        coins = new CoinPool();
        items = new Inventory();
        state = VendingMachineState.Customer;
        this.password = password;
    }

    public void insertCoin(Coin coin){
        inputCoinAmount += coin.getValue();
        coins.addCoins(coin, 1);
    }

    public void buyItem(Item item) throws OutOfStockException, InsufficientCoinsException {
        if(items.getNumItems(item) == 0){
            throw new OutOfStockException(String.format("The item [%s] is out of of stock\n", item.toString()));
        }

        checkPrice(item);
        items.removeItems(item, 1);
        giveChange();
        inputCoinAmount = 0;
    }

    public void logIn(String password) throws IncorrectPasswordException {
        if(!this.password.equals(password)){
            throw new IncorrectPasswordException();
        }

        state = VendingMachineState.Operator;
    }

    public void logOff(){
        state = VendingMachineState.Customer;
    }

    public void addItems(Item item, int amount){
        items.addItems(item, amount);
    }

    public void removeCoins(Coin coin, int amount){
        coins.removeCoins(coin, amount);
    }

    public int getNumCoins(Coin coin){
        return coins.getNumCoins(coin);
    }

    private void checkPrice(Item item) throws InsufficientCoinsException {
        if(item.getPrice() > inputCoinAmount){
            throw new InsufficientCoinsException();
        }
    }

    private void giveChange(){
        while(inputCoinAmount > 0){
            if(inputCoinAmount >= Coin.QUARTER.getValue()){
                inputCoinAmount -= Coin.QUARTER.getValue();
            }
            else if (inputCoinAmount >= Coin.DIME.getValue()){
                inputCoinAmount -= Coin.DIME.getValue();
            }
            else if (inputCoinAmount >= Coin.NICKEL.getValue()){
                inputCoinAmount -= Coin.NICKEL.getValue();
            }
            else {
                inputCoinAmount -= Coin.PENNY.getValue();
            }
        }
    }
}
