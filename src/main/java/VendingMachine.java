public class VendingMachine {
    private Inventory inventory;
    private CoinPool coins;
    private String password;
    private VendingMachineState state;

    public String showInventory(){
        StringBuilder output = new StringBuilder();
        int i = 1;

        for (ItemInfo info : inventory) {
            output.append(String.format("%-10s %-4.2f", info.getItem().getName(), info.getPrice()));
        }

        return output.toString();
    }

    public void buy(String itemID){
        inventory.removeItem(itemID);
    }

    public boolean checkPassword(String input){
        return input.equals(password);
    }

    public void login(){
        state = VendingMachineState.OPERATOR;
    }

    public void logoff(){
        state = VendingMachineState.CUSTOMER;
    }

    public void insertCoin(Coin coin){
        coins.add(coin);
    }

    public boolean isValidSelection(int index){
        return 0 <= index && index <= inventory.getNumItems();
    }



}
