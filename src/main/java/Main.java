import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    case "s", "show" -> {
                        switch (lowercaseInput[1]) {
                            case "i", "inv", "inventory" -> System.out.println(
                                    vendingMachine.showInventory());
                            case "h", "help" -> showHelp();
                        }
                    }
                    case "login" -> vendingMachine.login(splitInput[1]);
                    case "exit", "logoff" -> vendingMachine.logoff();
                    case "q", "quit" -> run = false;

                }
            }

        }
    }

    private static boolean isValidInput(String input) {
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
        } else {
            switch (inputs[0].toLowerCase()) {
                case "i", "insert", "b", "buy", "q", "quit", "l", "login" -> {
                    return true;
                }
                case "s", "show" -> {
                    switch (inputs[1].toLowerCase()) {
                        case "inventory", "inv", "i", "help", "h" -> {
                            return true;
                        }
                        default -> {
                            return false;
                        }
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

    private static void showHelp() {
        System.out.println("Show help");
    }
}
