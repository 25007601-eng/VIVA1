import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int parcelsProcessed = 0;
        int heaviestWeight = 0;
        double totalShippingFees = 0;

        while (true) {
            System.out.print("Enter destination postcode (or EXIT to stop): ");
            String postcode = input.nextLine().trim();

            if (postcode.equalsIgnoreCase("EXIT")) {
                break;
            }

            postcode = postcode.replace(" ", "").replace("-", "").toUpperCase();

            if (postcode.startsWith("MY")) {
                postcode = postcode.substring(2);
            }

            String zone;
            double firstKg;
            double additionalKg;
            boolean international = false;

            if (postcode.matches("[A-Z]{2}.*")) {
                international = true;
                zone = "International (" + postcode.substring(0, 2) + ")";
                firstKg = 35.0;
                additionalKg = 35.0;

            } else if (postcode.matches("[0-9]{5}")) {
                int area = Integer.parseInt(postcode.substring(0, 2));

                if (area >= 50 && area <= 60) {
                    zone = "Klang Valley";
                    firstKg = 6.0;
                    additionalKg = 1.5;

                } else if ((area >= 1 && area <= 49) ||
                           (area >= 61 && area <= 86)) {
                    zone = "Peninsular";
                    firstKg = 8.0;
                    additionalKg = 2.0;

                } else if (area >= 87 && area <= 98) {
                    zone = "East Malaysia";
                    firstKg = 14.0;
                    additionalKg = 4.5;

                } else {
                    System.out.println("Error: Invalid postcode. Parcel skipped.");
                    continue;
                }

            } else {
                System.out.println("Error: Invalid postcode. Parcel skipped.");
                continue;
            }

            int grams;

            while (true) {
                System.out.print("Enter parcel weight (g): ");
                String weightText = input.nextLine().trim();

                try {
                    grams = Integer.parseInt(weightText);

                    if (grams > 0) {
                        break;
                    }

                    System.out.println("Error: Weight must be greater than 0.");

                } catch (NumberFormatException ex) {
                    System.out.println("Error: Weight must be an integer.");
                }
            }

            double length, width, height;

            while (true) {
                System.out.print("Enter dimensions LxWxH (cm): ");
                String dimensions = input.nextLine().trim();

                if (!dimensions.matches("[0-9]+x[0-9]+x[0-9]+")) {
                    System.out.println("Error: Use the format LxWxH with positive numbers, e.g. 30x20x10.");
                    continue;
                }

                String[] parts = dimensions.split("x");

                try {
                    length = Integer.parseInt(parts[0]);
                    width = Integer.parseInt(parts[1]);
                    height = Integer.parseInt(parts[2]);

                    if (length > 0 && width > 0 && height > 0) {
                        break;
                    }

                } catch (NumberFormatException ex) {
                    // Invalid dimensions
                }

                System.out.println("Error: Use the format LxWxH with positive numbers, e.g. 30x20x10.");
            }

            double actualKg = grams / 1000.0;
            double volumetricKg = (length * width * height) / 5000.0;

            int chargeableKg = (int) Math.ceil(
                Math.max(actualKg, volumetricKg)
            );

            double fee;

            if (international) {
                fee = chargeableKg * 35.0;
            } else {
                fee = firstKg + (chargeableKg - 1) * additionalKg;
            }

            String adjustment = "None";

            if (chargeableKg >= 5 && chargeableKg <= 10) {
                fee *= 0.95;
                adjustment = "5% bulk discount";

            } else if (chargeableKg > 10) {
                fee *= 1.15;
                adjustment = "15% heavy parcel surcharge";
            }

            System.out.println();
            System.out.println("Shipping Summary");
            System.out.println("Postcode: " + postcode);
            System.out.println("Zone: " + zone);

            System.out.printf(
                "Actual Weight: %.2f kg | Volumetric Weight: %.2f kg%n",
                actualKg, volumetricKg
            );

            System.out.println("Chargeable Weight: " + chargeableKg + " kg");
            System.out.println("Adjustment: " + adjustment);
            System.out.printf("Shipping Fee: RM %.2f%n", fee);
            System.out.println();

            parcelsProcessed++;
            totalShippingFees += fee;

            if (chargeableKg > heaviestWeight) {
                heaviestWeight = chargeableKg;
            }
        }

        System.out.println("Parcels Processed: " + parcelsProcessed);
        System.out.printf("Total Shipping Fees: RM %.2f%n", totalShippingFees);
        System.out.println("Heaviest Chargeable Weight: " + heaviestWeight + " kg");

        input.close();
    }
}

