package fr.coffee_machine.logic;

public enum DrinkType {
    TEA("T", 0.4),
    COFFEE("C", 0.6),
    CHOCOLATE("H", 0.5),
    ORANGE("O", 0.6);

    public final String value;
    public final double price;

    DrinkType(String value, double price) {
        this.value = value;
        this.price = price;
    }
}
