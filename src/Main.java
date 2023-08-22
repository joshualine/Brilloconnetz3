import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int totalTime = 0;
        int[] pancakesMade = new int[12]; // Pancakes made by shopkeeper for each interval
        int[][] pancakesEaten = new int[3][12]; // Pancakes eaten by each user for each interval
        int[][] usersRequests = new int[3][12]; // User requests for each interval
        boolean[] metNeeds = new boolean[12]; // Whether needs were met for each interval
        int[] wastedPancakes = new int[12]; // Wasted pancakes for each interval
        int[] unmetOrders = new int[12]; // Unmet orders for each interval

        Random random = new Random();

        while (totalTime < 3600) { // Simulate for 1 hour (3600 seconds)
            int interval = totalTime / 30;

            pancakesMade[interval] = Math.min(random.nextInt(13), 12);

            for (int i = 0; i < 3; i++) {
                usersRequests[i][interval] = random.nextInt(6);
                usersRequests[i][interval] = Math.min(usersRequests[i][interval], 5);
                pancakesEaten[i][interval] = usersRequests[i][interval];
            }

            int totalPancakesEaten = usersRequests[0][interval] + usersRequests[1][interval] + usersRequests[2][interval];
            metNeeds[interval] = totalPancakesEaten <= pancakesMade[interval];

            wastedPancakes[interval] = metNeeds[interval] ? (pancakesMade[interval] - totalPancakesEaten) : pancakesMade[interval];

            if (!metNeeds[interval]) {
                unmetOrders[interval] = totalPancakesEaten - pancakesMade[interval];
            }

            System.out.println("Time Slot: " + totalTime + "s - " + (totalTime + 30) + "s");
            System.out.println("Pancakes made by shopkeeper: " + pancakesMade[interval]);
            System.out.println("Pancakes eaten by User 1: " + pancakesEaten[0][interval]);
            System.out.println("Pancakes eaten by User 2: " + pancakesEaten[1][interval]);
            System.out.println("Pancakes eaten by User 3: " + pancakesEaten[2][interval]);
            System.out.println("Shopkeeper met users' needs: " + metNeeds[interval]);
            System.out.println("Wasted pancakes: " + wastedPancakes[interval]);
            if (!metNeeds[interval]) {
                System.out.println("Unmet pancake orders: " + unmetOrders[interval]);
            }
            System.out.println("------------------------------------");

            totalTime += 30; // Move to the next time slot
        }
    }
}