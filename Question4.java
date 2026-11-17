import java.util.Scanner;
import java.util.Locale;

public class Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println();

        String[] names = {"Compact Hatch", "Family SUV", "Delivery Van", "E-Scooter"};
        int[] capacities = {40, 75, 90, 4};
        int[] maxPowers = {50, 120, 80, 3};
        boolean[] charged = new boolean[4];

        double hubEnergy = 150.0;
        double totalDelivered = 0.0;
        double totalRevenue = 0.0;
        int vehiclesCharged = 0;

        while (hubEnergy > 0.000000001) {
            System.out.println("===== Solar EV Charging Hub =====");
            System.out.printf(Locale.US, "Hub Battery: %.2f kWh%n", hubEnergy);

            for (int i = 0; i < names.length; i++) {
                System.out.print((i + 1) + ". " + names[i]
                    + " (" + capacities[i] + " kWh, max " + maxPowers[i] + " kW)");
                if (charged[i]) {
                    System.out.print(" [DONE]");
                }
                System.out.println();
            }
            System.out.println("0. Close hub");

            int choice;
            System.out.print("Select a vehicle (0-4): ");
            String choiceText = input.nextLine().trim();

            try {
                choice = Integer.parseInt(choiceText);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter an integer from 0 to 4.");
                System.out.println();
                continue;
            }

            if (choice < 0 || choice > 4) {
                System.out.println("Error: Invalid vehicle selection.");
                System.out.println();
                continue;
            }

            if (choice == 0) {
                System.out.println("Hub closed by operator.");
                System.out.println();
                break;
            }

            int index = choice - 1;

            if (charged[index]) {
                System.out.println("Error: This vehicle has already been charged.");
                System.out.println();
                continue;
            }

            int current;

            while (true) {
                System.out.print("Enter current battery level (0-99): ");

                try {
                    current = Integer.parseInt(input.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Current level must be between 0 and 99.");
                    continue;
                }

                if (current >= 0 && current <= 99) {
                    break;
                }

                System.out.println("Error: Current level must be between 0 and 99.");
            }

            int target;

            while (true) {
                System.out.print("Enter target battery level (" + (current + 1) + "-100): ");

                try {
                    target = Integer.parseInt(input.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Target must be higher than the current level and at most 100.");
                    continue;
                }

                if (target > current && target <= 100) {
                    break;
                }

                System.out.println("Error: Target must be higher than the current level and at most 100.");
            }

            double delivered = capacities[index] * (target - current) / 100.0;
            double drawn = delivered / 0.90;
            boolean depleted = drawn > hubEnergy;
            double reached = target;

            if (depleted) {
                drawn = hubEnergy;
                delivered = drawn * 0.90;
                reached = current + delivered / capacities[index] * 100.0;
            }

            int power = Math.min(maxPowers[index], 60);
            double minutes = delivered / power * 60.0;

            double cost = delivered * 1.20;

            if (delivered > 30.0) {
                cost *= 0.95;
            }

            hubEnergy -= drawn;

            if (hubEnergy < 0.000000001) {
                hubEnergy = 0.0;
            }

            charged[index] = true;
            vehiclesCharged++;
            totalDelivered += delivered;
            totalRevenue += cost;

            System.out.println();
            System.out.println("--- Charging Summary ---");
            System.out.println("Vehicle: " + names[index]);
            System.out.println("Battery: " + current + "% -> " + target + "%");
            System.out.println("Charging Power: " + power + " kW");

            System.out.printf(Locale.US,
                "Energy Delivered: %.2f kWh (drawn from hub: %.2f kWh)%n",
                delivered, drawn);

            System.out.printf(Locale.US, "Charging Time: %.2f minutes%n", minutes);
            System.out.printf(Locale.US, "Cost: RM %.2f%n", cost);

            if (depleted) {
                System.out.printf(Locale.US,
                    "Hub Battery Depleted! Vehicle only reached %.2f%%.%n", reached);
            } else {
                System.out.printf(Locale.US,
                    "Remaining Hub Battery: %.2f kWh%n", hubEnergy);
            }

            System.out.println("------------------------");
            System.out.println();

            if (hubEnergy == 0.0) {
                break;
            }
        }

        System.out.println("===== Hub Report =====");
        System.out.println("Vehicles Charged: " + vehiclesCharged);
        System.out.printf(Locale.US,
            "Total Energy Delivered: %.2f kWh%n", totalDelivered);
        System.out.printf(Locale.US,
            "Total Revenue: RM %.2f%n", totalRevenue);
        System.out.printf(Locale.US,
            "Hub Battery Left: %.2f kWh%n", hubEnergy);

        input.close();
    }
}
