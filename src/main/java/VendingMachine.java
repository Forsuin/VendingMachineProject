public class VendingMachine {
    private final Inventory inventory;
    private final CoinPool coins;
    private final String password;
    private VendingMachineState state;
    private double inputCoinAmount;

    private int numItems;

    public VendingMachine(String password) {
        inventory = new Inventory();
        coins = new CoinPool();
        this.password = password;
        inputCoinAmount = 0;
        numItems = 0;
    }

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

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    public void login(String password) {
        if (checkPassword(password)) {
            state = VendingMachineState.OPERATOR;
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

    public void logoff() {
        state = VendingMachineState.CUSTOMER;
    }

    public void insertCoin(Coin coin) {
        inputCoinAmount += coin.getValue();
        coins.add(coin);
        System.out.printf("Inserted %s\n", coin.getName());
    }

    public void addItem(ItemInfo item) {
        if (state != VendingMachineState.OPERATOR) {
            System.out.println(
                    "You must be in Operator mode to add items to the vending" +
                            " machine");
            return;
        }

        inventory.addItem(item, ++numItems);
    }

    public boolean isValidSelection(int index) {
        return 0 <= index && index <= inventory.getNumItems();
    }

    public void dispenseChange(double inputCoinAmount) {
        CoinAmounts change = calculateChange(inputCoinAmount);

        System.out.printf(
                "Your change is: %d quarters, %d dimes, %d nickels, %" +
                        "d pennies\n", change.quarters, change.dimes,
                change.nickels, change.pennies);
    }

    public CoinAmounts calculateChange(double amount) {
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

    public boolean hasChange(double amount) {
        CoinAmounts amounts = calculateChange(amount);

        return !(coins.getNumCoin(Coin.QUARTER) < amounts.quarters) &&
                !(coins.getNumCoin(Coin.DIME) < amounts.dimes) &&
                !(coins.getNumCoin(Coin.NICKEL) < amounts.nickels) &&
                !(coins.getNumCoin(Coin.PENNY) < amounts.pennies);
    }


    private record CoinAmounts(int quarters, int dimes, int nickels,
                               int pennies) {

    }
}
