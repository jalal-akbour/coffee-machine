package fr.coffee_machine.logic;

import fr.coffee_machine.maker.DrinkMaker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {

    @Mock
    private DrinkMaker drinkMaker;
    @InjectMocks
    private Order order = new Order();

    @Before
    public void setDefaultValues(){
        order.setGivingMoney(1);
    }
    @Test
    public void should_makeTeaWithoutSugarAndWithoutStick_when_onlyTeaOrder() {
        //Given
        order.setDrinkType(DrinkType.TEA);
        order.setNumSugar(0);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("T::");
    }

    @Test
    public void should_makeCoffeeWithoutSugarAndWithoutStick_when_onlyCoffeeOrder() {
        //Given
        order.setDrinkType(DrinkType.COFFEE);
        order.setNumSugar(0);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("C::");
    }

    @Test
    public void should_makeChocolateWithoutSugarAndWithoutStick_when_onlyChocolateOrder() {
        //Given
        order.setDrinkType(DrinkType.CHOCOLATE);
        order.setNumSugar(0);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("H::");
    }

    @Test
    public void should_makeTeaWithOneSugarAndStick_when_teaAndOneSugarOrder() {
        //Given
        order.setDrinkType(DrinkType.TEA);
        order.setNumSugar(1);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("T:1:0");
    }

    @Test
    public void should_makeCoffeeWithTwoSugarAndStick_when_coffeeAndTwoSugarOrder() {
        //Given
        order.setDrinkType(DrinkType.COFFEE);
        order.setNumSugar(2);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("C:2:0");
    }

    @Test
    public void should_makeChocolateWithOneSugarAndStick_when_chocolateAndOneSugarOrder() {
        //Given
        order.setDrinkType(DrinkType.CHOCOLATE);
        order.setNumSugar(1);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("H:1:0");
    }


    @Test
    public void should_sendMessage_when_chocolateAndOneSugarOrder() {
        //Given
        order.setDrinkType(DrinkType.CHOCOLATE);
        order.setNumSugar(1);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("H:1:0");
        Mockito.verify(drinkMaker).make("M:Preparation of CHOCOLATE with 1 sugars and stick");
    }

    @Test
    public void should_sendMessage_when_onlyTeaOrder() {
        //Given
        order.setDrinkType(DrinkType.TEA);
        order.setNumSugar(0);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("T::");
        Mockito.verify(drinkMaker).make("M:Preparation of TEA without sugar and without stick");
    }

    @Test
    public void should_sendMessageOfMissingAmount_when_onlyTeaAndNotEnoughMoneyOrder() {
        //Given
        order.setDrinkType(DrinkType.TEA);
        order.setNumSugar(0);
        order.setGivingMoney(0.2);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("M:You must add 0.2 euro");
    }

    @Test
    public void should_sendMessageOfMissingAmount_when_onlyCoffeeAndNotEnoughMoneyOrder() {
        //Given
        order.setDrinkType(DrinkType.COFFEE);
        order.setNumSugar(0);
        order.setGivingMoney(0.5);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("M:You must add 0.1 euro");
    }

    @Test
    public void should_sendMessageOfMissingAmount_when_onlyChocolateAndNotEnoughMoneyOrder() {
        //Given
        order.setDrinkType(DrinkType.CHOCOLATE);
        order.setNumSugar(0);
        order.setGivingMoney(0.1);

        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("M:You must add 0.4 euro");
    }

}
