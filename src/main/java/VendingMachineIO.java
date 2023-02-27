import java.util.Scanner;

/**
 * Serves as the primary I/O system for the vending machine.
 * Handles verifying input and displaying output
 */
public class VendingMachineIO {


    /**
     * Reads input from standard input, verifies it, then forwards it to the
     * vending machine proper
     */
    public void getInput() {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        System.out.print("Enter a password for the vending machine: ");

        String password = scanner.nextLine();
        System.out.printf("Your password will be: %s\n", password);

        VendingMachine vendingMachine = new VendingMachine(password);

        ItemInfo[] items = {new ItemInfo("Mountain Dew", 1.75),
                new ItemInfo("Diet Mountain Dew", 1.75),
                new ItemInfo("Dr. Pepper Zero", 1.75),
                new ItemInfo("Lemonade", 1.75), new ItemInfo("Pepsi", 1.75),
                new ItemInfo("Dr. Pepper", 1.75), new ItemInfo("Water", 2.25),
                new ItemInfo("Energy Drink", 3.00)};

        vendingMachine.login(password);

        for (ItemInfo item : items) {
            vendingMachine.addItem(item);
        }

        System.out.println(vendingMachine.showInventory());
        showHelp();

        String input;
        while (run) {
            input = scanner.nextLine();

            if (!isValidInput(input)) {
                System.out.println("Invalid command");
                continue;
            }

            String[] splitInput = input.split(" ");
            String[] lowercaseInput = {splitInput[0].toLowerCase(), ""};

            if (splitInput.length == 1) {
                switch (lowercaseInput[0]) {
                    case "h", "help" -> showHelp();
                    case "logoff", "exit" -> vendingMachine.logoff();
                }
            } else {
                lowercaseInput[1] = splitInput[1].toLowerCase();

                switch (lowercaseInput[0]) {
                    case "i", "insert" -> {
                        try {
                            Coin coin = Coin.fromString(lowercaseInput[1]);
                            vendingMachine.insertCoin(coin);
                        } catch (IllegalArgumentException e) {
                            System.out.printf("%s is not a valid coin\n",
                                    lowercaseInput[1]);
                        }
                    }
                    case "b", "buy" -> {
                        try {
                            if (!vendingMachine.isValidSelection(
                                    Integer.parseInt(lowercaseInput[1]))) {
                                System.out.println("Invalid product number");
                            } else {
                                vendingMachine.buy(lowercaseInput[1]);
                            }
                        } catch (NumberFormatException e) {
                            System.out.printf(
                                    "%s is not in the range of acceptable " +
                                            "inputs\n", lowercaseInput[1]);
                        }
                    }
                    case "show" -> {
                        switch (lowercaseInput[1]) {
                            case "i", "inv", "inventory" -> System.out.println(
                                    vendingMachine.showInventory());
                        }
                    }
                    case "stock" -> {
                        if (!vendingMachine.isValidSelection(
                                Integer.parseInt(lowercaseInput[1]))) {
                            System.out.println("Invalid product number");
                        } else {
                            vendingMachine.stockItem(lowercaseInput[1]);
                        }
                    }
                    case "setprice" -> {
                        if (splitInput.length < 3) {
                            System.out.println("Not enough arguments");
                        } else if (!vendingMachine.isValidSelection(
                                Integer.parseInt(lowercaseInput[1]))) {
                            System.out.println("Invalid product number");
                        } else {
                            vendingMachine.setPrice(lowercaseInput[1],
                                    Double.parseDouble(splitInput[2]));
                        }
                    }
                    case "remove" -> {
                        try {
                            Coin coin = Coin.fromString(lowercaseInput[1]);
                            vendingMachine.removeCoins(coin);
                        } catch (IllegalArgumentException e) {
                            System.out.printf("%s is not a valid coin\n",
                                    lowercaseInput[1]);
                        }
                    }
                    case "login" -> vendingMachine.login(splitInput[1]);
                    case "exit", "logoff" -> vendingMachine.logoff();
                    case "q", "quit" -> run = false;

                }
            }
        }
    }

    /**
     * @param input String to check to if valid input to vending machine
     * @return boolean
     */
    private boolean isValidInput(String input) {
        String[] inputs = input.split(" ");


        if (inputs.length == 1) {
            switch (inputs[0]) {
                case "h", "help", "logoff", "exit" -> {
                    return true;
                }
                case "login" -> {
                    System.out.println(
                            "login missing argument: \"login [password]\"");
                    return false;
                }
            }
        } else if (inputs.length == 2) {
            switch (inputs[0].toLowerCase()) {
                case "i", "insert", "b", "buy", "q", "quit", "l", "login",
                        "stock", "setprice", "remove" -> {
                    return true;
                }
                case "show" -> {
                    switch (inputs[1].toLowerCase()) {
                        case "inventory", "inv", "i" -> {
                            return true;
                        }
                    }
                }
            }
        }
        //should be unreachable, but here to make compiler happy
        return false;
    }


    /**
     * Displays information about valid commands, their syntax, and a brief
     * description
     */
    private void showHelp() {

        String output =
                """
                        help, h - Displays all valid inputs and arguments
                        login [password] - Attempts to log into operator mode using the inputted password
                        logoff - Exits out of operator mode back into user mode
                        exit - Alias for logoff
                        insert/i [coin type] - Inserts a single coin of inputted type into the machine. Valid types are quarters(q), dimes(d), nickels(n), and pennies(p)
                        buy/b [item number] - Attempts to buy the specified item
                        quit - Exits the program
                        show inventory/inv/i - Shows the inventory of the vending machine
                        stock [item number] - Adds one item to the machine's inventory
                        setprice [item number] [price] - Set's the price of item to inputted value
                        remove [coin type] - Removes all coins of inputted type from machine
                        """;

        System.out.println(output);
    }
}
