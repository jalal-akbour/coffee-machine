package fr.coffee_machine.services;

public interface EmailNotifier {
	void notifyMissingDrink(String drink);
}