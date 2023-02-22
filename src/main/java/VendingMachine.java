public class VendingMachine {
    private Inventory inventory;
    private CoinPool coins;
    private String password;
    private VendingMachineState state;

    public String showInventory(){
        StringBuilder output = new StringBuilder();
        int i = 1;

        for (Item item : inventory) {
            output.append(String.format("%-10s %-4.2f", item.getName(), item.getPrice()));
        }

        return output.toString();
    }

    public void buy(String itemID){

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
        //0 - 10?
        //A - J?
        return true;
    }



}
