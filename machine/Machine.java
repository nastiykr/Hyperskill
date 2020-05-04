package machine;

import java.util.Scanner;

public class Machine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    Scanner scanner = new Scanner(System.in);

    public Machine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public void action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        switch (action) {
            case "buy":
                buy();
                action();
                break;
            case "fill":
                fill();
                action();
                break;
            case "take":
                take();
                action();
                break;
            case "remaining":
                displayRemainingIngredients();
                action();
                break;
            case "exit":
                break;
        }
    }

    public void displayRemainingIngredients() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", beans);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("%d of money\n\n", money);
    }

    public void take() {
        System.out.printf("I gave you $%d\n\n", money);
        money = 0;
    }

    public void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        int countOfAddingWater = scanner.nextInt();
        water += countOfAddingWater;
        System.out.println("Write how many ml of milk do you want to add:");
        int countOfAddingMilk = scanner.nextInt();
        milk += countOfAddingMilk;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        int countOfAddingBeans = scanner.nextInt();
        beans += countOfAddingBeans;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int countOfAddingCups = scanner.nextInt();
        cups += countOfAddingCups;
        System.out.println();
    }

    public void buy() {
        while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            String selectedCoffee = scanner.next();
            if (selectedCoffee.equalsIgnoreCase("back")) {
                break;
            }
            if (selectedCoffee.equals("1") || selectedCoffee.equals("2") || selectedCoffee.equals("3")) {
                int select = Integer.parseInt(selectedCoffee);
                System.out.println(makeCoffee(select));
                break;
            }
        }
    }

    private String makeCoffee(int selectedCoffee) {
        String coffee = "";
        for (Coffee c : Coffee.values()) {
            if (c.getId() == selectedCoffee) {
                coffee = c.name();
                break;
            }
        }
        if ((water - Coffee.valueOf(coffee).getWater() >= 0) && (milk - Coffee.valueOf(coffee).getMilk() >= 0) && (beans - Coffee.valueOf(coffee).getBeans() >= 0)) {
            water -= Coffee.valueOf(coffee).getWater();
            milk -= Coffee.valueOf(coffee).getMilk();
            beans -= Coffee.valueOf(coffee).getBeans();
            cups--;
            money += Coffee.valueOf(coffee).getCost();
            return "I have enough resources, making you a coffee!";
        }
        if (water - Coffee.valueOf(coffee).getWater() < 0) {
            return "Sorry, not enough water!";
        }
        if (milk - Coffee.valueOf(coffee).getMilk() < 0) {
            return "Sorry, not enough milk!";
        }
        if (beans - Coffee.valueOf(coffee).getBeans() < 0) {
            return "Sorry, not enough beans!";
        }
        if (cups - 1 < 0) {
            return "Sorry, not enough cups!";
        }
        return "Something is wrong :(";
    }

    enum Coffee {
        ESPRESSO(1, 250, 0, 16, 4),
        LATTE(2, 350, 75, 20, 7),
        CAPPUCCINO(3, 200, 100, 12, 6);

        int id;
        int water;
        int milk;
        int beans;
        int cost;

        Coffee(int id, int water, int milk, int beans, int cost) {
            this.id = id;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cost = cost;
        }

        public int getId() {
            return id;
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getBeans() {
            return beans;
        }

        public int getCost() {
            return cost;
        }
    }

}