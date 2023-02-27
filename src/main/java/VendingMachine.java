/**
 * Represents the actual vending machine and its components
 */
public class VendingMachine {
    private final Inventory inventory;
    private final CoinPool coins;
    private final String password;
    private VendingMachineState state;
    private double inputCoinAmount;

    private int numItems;

    /**
     * Creates new instance of a vending machine
     * @param password Sets the password to enter into operator mode
     */
    public VendingMachine(String password) {
        inventory = new Inventory();
        coins = new CoinPool();
        this.password = password;
        inputCoinAmount = 0;
        numItems = 0;
    }

    public VendingMachineState getState() {
        return state;
    }

    /**
     * @return String containing information about the inventory of the vending machine
     */
    public String showInventory() {
        StringBuilder output = new StringBuilder();
        int i = 1;

        for (ItemInfo info : inventory) {
            output.append(
                    String.format("[%d] %-20s $%-4.2f  ",
                            inventory.getID(info.getItem().name()),
                            info.getItem().name(),
                            info.getPrice()));
            if (i % 4 == 0) {
                output.append("\n");
            }
            i++;
        }

        return output.toString();
    }


    /**
     * @param itemID The itemID (1 - numItems) that should be purchased
     */
    public void buy(String itemID) {
        double itemPrice = inventory.getPrice(itemID);

        if (inputCoinAmount < itemPrice) {
            System.out.printf("Unable to buy %s. Please insert more coins\n",
                    inventory.getName(itemID));
            return;
        }

        if (!hasChange(inputCoinAmount - itemPrice)) {
            System.out.printf("Unable to buy %s. Not enough coins for change\n",
                    inventory.getName(itemID));
            return;
        }

        inventory.removeItem(itemID);
        inputCoinAmount -= itemPrice;

        if (itemPrice > 0) {
            dispenseChange(inputCoinAmount);
        }
    }


    /**
     * @param password Password input to be checked
     * @return Boolean stating whether password is correct or not
     */
    private boolean checkPassword(String password) {
        return password.equals(this.password);
    }


    /**
     * @param password Attempts to log the user into Operator mode using password
     */
    public void login(String password) {
        if (checkPassword(password)) {
            state = VendingMachineState.OPERATOR;
            System.out.println("Successfully logged in");
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    /**
     * Exits the user from Operator mode back into Customer mode
     */
    public void logoff() {
        state = VendingMachineState.CUSTOMER;
        System.out.println("Successfully logged out");
    }

    /**
     * Allows the user to input a coin (Quarter, Dime, Nickel, Penny)
     * @param coin The coin to be inputted
     */
    public void insertCoin(Coin coin) {
        inputCoinAmount += coin.getValue();
        coins.add(coin);
        System.out.printf("Inserted %s\n", coin.getName());
    }

    /**
     * Removes all coins of specified type from machine
     * @param coin Coin type to be removed
     */
    public void removeCoins(Coin coin){
        coins.remove(coin);
        System.out.printf("Removed all %ss", coin.getName());
    }

    /**
     * Adds new items to machine, only accessible at startup
     * @param item Item to be added to machine's inventory
     */
    public void addItem(ItemInfo item) {
        if (state != VendingMachineState.OPERATOR) {
            System.out.println(
                    "You must be in Operator mode to add items to the vending" +
                            " machine");
            return;
        }

        inventory.addItem(item, ++numItems);
    }

    /**
     * Increase quanitity of specified item by 1
     * @param itemID Item to be restocked
     */
    public void stockItem(String itemID){
        inventory.stockItem(itemID);
    }

    /**
     * Changes the price of item to inputted value
     * @param itemID Item to change price of
     * @param price The new price
     */
    public void setPrice(String itemID, double price){
        inventory.setPrice(itemID, price);
        System.out.printf("Set price of %s to %.2f\n", inventory.getName(itemID), price);
    }

    /**
     * Determines whether ID of selected item is in inventory
     * @param ID ID of item to check
     * @return Boolean stating whether in range or not
     */
    public boolean isValidSelection(int ID) {
        return 0 <= ID && ID <= inventory.getNumItems();
    }

    /**
     * Output change for user
     * @param inputCoinAmount Amount to pull change for
     */
    private void dispenseChange(double inputCoinAmount) {
        CoinAmounts change = calculateChange(inputCoinAmount);

        System.out.printf(
                "Your change is: %d quarters, %d dimes, %d nickels, %" +
                        "d pennies\n", change.quarters, change.dimes,
                change.nickels, change.pennies);
    }


    /**
     * @param amount Calculate change for this amount of money
     * @return CoinAmounts stating how many of each coin is needed
     */
    private CoinAmounts calculateChange(double amount) {
        int numQuarters = 0;
        int numDimes = 0;
        int numNickels = 0;
        int numPennies = 0;

        while (amount > 0) {
            if (amount >= 0.25) {
                numQuarters++;
                amount -= 0.25;
            } else if (amount >= 0.10) {
                numDimes++;
                amount -= 0.10;
            } else if (amount >= 0.05) {
                numNickels++;
                amount -= 0.05;
            } else {
                numPennies++;
                amount -= 0.01;
            }
        }

        return new CoinAmounts(numQuarters, numDimes, numNickels, numPennies);
    }

    /**
     * Determines whether machine has enough coins for change
     * @param amount Dollar amount to be checked for change
     * @return Boolean stating whether the machine has change
     */
    private boolean hasChange(double amount) {
        CoinAmounts amounts = calculateChange(amount);

        return !(coins.getNumCoin(Coin.QUARTER) < amounts.quarters) &&
                !(coins.getNumCoin(Coin.DIME) < amounts.dimes) &&
                !(coins.getNumCoin(Coin.NICKEL) < amounts.nickels) &&
                !(coins.getNumCoin(Coin.PENNY) < amounts.pennies);
    }

    /**
     * Wrapper for the number of coins in an amount
     * @param quarters Number of quarters
     * @param dimes Number of dimes
     * @param nickels Number of nickels
     * @param pennies Number of pennies
     */
    private record CoinAmounts(int quarters, int dimes, int nickels,
                               int pennies) {

    }
}
