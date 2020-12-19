package com.company;

public class CoffeeMachine {


    private int waterQuantity;
    private int milkQuantity;
    private int coffeeQuantity;
    private int noOfCups;
    private int amountOfMoney;
    private boolean isRunning;
    private MachineState machineState;
    private ConsoleOutput output;

    public CoffeeMachine() {
        waterQuantity = 400;
        milkQuantity = 540;
        coffeeQuantity = 120;
        noOfCups = 9;
        amountOfMoney = 550;
        isRunning = true;
        output = new EnglishConsoleOutput();
        machineState = MachineState.CHOOSING_LANGUAGE;
            }

    public void setConsoleOutput(String input){
             switch(input){
            case "en":
                output = new EnglishConsoleOutput();
                break;
            case "ro":
                output = new RomanianConsoleOutput();
                break;
        }
    }

    public int getWaterQuantity() {
        return waterQuantity;
    }

    public void setWaterQuantity(int waterQuantity) {
        this.waterQuantity = waterQuantity;
    }

    public int getMilkQuantity() {
        return milkQuantity;
    }

    public void setMilkQuantity(int milkQuantity) {
        this.milkQuantity = milkQuantity;
    }

    public int getCoffeeQuantity() {
        return coffeeQuantity;
    }

    public void setCoffeeQuantity(int coffeeQuantity) {
        this.coffeeQuantity = coffeeQuantity;
    }

    public int getNoOfCups() {
        return noOfCups;
    }

    public void setNoOfCups(int noOfCups) {
        this.noOfCups = noOfCups;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public void processUserInput(String userInput) {
        machineState.process(this, userInput);
    }

    public void checkAndBuy(int requestedWater, int requestedCoffee, int requestedMilk, int price, CoffeeType coffeType) {
        boolean checkWater = checkIfIsEnough(requestedWater, waterQuantity);
        boolean checkMilk = checkIfIsEnough(requestedMilk, milkQuantity);
        boolean checkCoffe = checkIfIsEnough(requestedCoffee, coffeeQuantity);
        boolean checkCups = checkNoOfCups();

        if (checkWater && checkCoffe && checkMilk && checkCups) {
            waterQuantity = waterQuantity - requestedWater;
            milkQuantity = milkQuantity - requestedMilk;
            coffeeQuantity = coffeeQuantity - requestedCoffee;
            noOfCups = noOfCups - 1;
            amountOfMoney = amountOfMoney + price;
            System.out.println("Here's your " + coffeType);
        } else {
            System.out.println("Some ingredients missing");
        }

    }

    private boolean checkIfIsEnough(int requested, int available) {
        return requested < available;
    }

    private boolean checkNoOfCups() {
        return noOfCups >= 1;

    }
    public void transitionToChoosing_option() {
        machineState = MachineState.CHOOSING_OPTION;
        output.printMainMenu();

    }

    // This is very similar to a setter method
    public void transitionToChoosingCoffeeType() {
        machineState = MachineState.CHOOSING_COFFEE_TYPE;
        output.printCoffeeMenu();
    }

    public void transitionToInputWater() {
        machineState = MachineState.FILL_WATER;
        output.printWaterQprompt();
    }

    public void transitionToInputMilk() {
        machineState = MachineState.FILL_MILK;
      output.printMilkQprompt();
    }

    public void transitionToInputCoffee() {
        machineState = MachineState.FILL_COFFEE;
        output.printCoffeeQprompt();
    }

    public void transitionToInputCups() {
        machineState = MachineState.FILL_CUPS;
        output.printCupsQprompt();
    }

    public void transitionToTakeMoney() {
        machineState = MachineState.TAKING_MONEY;
        output.printTakeMoneyPrompt(amountOfMoney);
        amountOfMoney = 0;
        machineState.process(this, "");
    }

    public void transitionToPrintStatus() {
        machineState = MachineState.PRINTING_STATUS;
        output.printStatus( waterQuantity,  milkQuantity,  coffeeQuantity,  noOfCups,  amountOfMoney);
        machineState.process(this, "");
    }

    public boolean isRunning() {
               return isRunning;
    }

}