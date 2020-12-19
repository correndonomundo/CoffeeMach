package com.company;

public interface ConsoleOutput {

    abstract void printMainMenu();
    abstract void printCoffeeMenu();
    abstract void printWaterQprompt();
    abstract void printMilkQprompt();
    abstract void printCoffeeQprompt();
    abstract void printCupsQprompt();
    abstract void printTakeMoneyPrompt(int amountOfMoney);
    abstract void printStatus(int waterQuantity, int milkQuantity, int coffeeQuantity, int noOfCups, int amountOfMoney);






}
