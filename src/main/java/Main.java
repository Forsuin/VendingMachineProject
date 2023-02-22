import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        System.out.println(vendingMachine.showInventory());


        String input;
        while(run){
            input = scanner.nextLine();

            if(!isValidInput(input)){
                System.out.println("Invalid command");
                continue;
            }

            String[] splitInput = input.split(" ");
            String[] lowercaseInput = {splitInput[0].toLowerCase(), ""};

            if(splitInput.length == 1){
                //can only be "help"/"h" since isValidInput() has already verified input
                showHelp();
            }
            else {
                lowercaseInput[1] = splitInput[1].toLowerCase();

                switch (lowercaseInput[0]){
                    case "i", "insert" -> {
                        Coin coin = Coin.valueOf(lowercaseInput[1]);
                        vendingMachine.insertCoin(coin);
                    }
                    case "b", "buy" -> {
                        if(!vendingMachine.isValidSelection(Integer.parseInt(lowercaseInput[1]))){
                            System.out.println("Invalid selecetion input");
                        }
                        else{
                            vendingMachine.buy(lowercaseInput[1]);
                        }
                    }
                    case "s", "show" -> {
                        switch (lowercaseInput[1]){
                            case "i", "inv", "inventory" -> {
                                System.out.println(vendingMachine.showInventory());
                            }
                            case "h", "help" -> {
                                showHelp();
                            }
                        }
                    }
                    case "login" -> {
                        if(!vendingMachine.checkPassword(splitInput[1])){
                            System.out.println("Incorrect password");
                        }
                        else {
                            vendingMachine.login();
                        }
                    }
                    case "exit", "logoff" -> {
                        vendingMachine.logoff();
                    }
                    case "q", "quit" -> {
                        run = false;
                    }

                }
            }

        }
    }

    private static boolean isValidInput(String input){
        String[] inputs = input.split(" ");

        ArrayList<String> validCoins = new ArrayList<>(
                Arrays.asList("penny", "p", "nickel", "n", "dime", "d", "quarter", "q")
        );

        if(inputs.length == 1){
            switch (inputs[0]){
                case "h", "help", "logoff", "exit" -> {
                    return true;
                }
                case "login" -> {
                    System.out.println("login missing argument: \"login [password]\"");
                    return false;
                }
            }
        }
        else {
            switch (inputs[0].toLowerCase()) {
                case "i", "insert" -> {
                    return validCoins.contains(inputs[1].toLowerCase());
                }
                case "b", "buy", "q", "quit", "l", "login" -> {
                    return true;
                }
                case "s", "show" -> {
                    switch (inputs[1].toLowerCase()) {
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
        //should be unreachable, but here to make compiler happy
        return false;
    }

    private static void showHelp(){
        System.out.println("Show help");
    }
}
