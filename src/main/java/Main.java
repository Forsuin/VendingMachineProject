import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

       // System.out.println(vendingMachine.showInventory());

        String input;
        while(run){
            input = scanner.nextLine();

            if(!isValidInput(input)){
                System.out.println("Command not found");
            }



        }
    }

    private static boolean isValidInput(String input){
        String[] inputs = input.split(" ");

        ArrayList<String> validCoins = new ArrayList<>(
                Arrays.asList("penny", "p", "nickel", "n", "dime", "d", "quarter", "q")
        );

        if(inputs.length == 1){
            return inputs[0].equals("help") || inputs[0].equals("h");
        }
        else {
            switch (inputs[0]) {
                case "i", "insert" -> {
                    return validCoins.contains(inputs[1]);
                }
                case "b", "buy" -> {
                    return true;
                }
                case "s", "show" -> {
                    switch (inputs[1]) {
                        case "inventory", "inv", "i", "help", "h" -> {
                            return true;
                        }
                        default -> {return false;}
                    }
                }
                default -> {
                    return false;
                }
            }
        }
    }
}
