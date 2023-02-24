public enum Coin {
    PENNY("Penny", 0.01),
    NICKEL("Nickel", 0.05),
    DIME("Dime", 0.10),
    QUARTER("Quarter", 0.25);

    private final String name;
    private final double value;

    Coin(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public static Coin fromString(String name) {
        try {
            return Coin.valueOf(name);
        } catch (IllegalArgumentException e) {
            switch (name) {
                case "q" -> {
                    return Coin.valueOf("QUARTER");
                }
                case "d" -> {
                    return Coin.valueOf("DIME");
                }
                case "n" -> {
                    return Coin.valueOf("NICKEL");
                }
                case "p" -> {
                    return Coin.valueOf("PENNY");
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }
}
