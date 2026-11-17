import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X, Y, B;

        do {
            System.out.print("Enter width X: ");
            X = input.nextInt();

            System.out.print("Enter height Y: ");
            Y = input.nextInt();

            System.out.print("Enter block size B: ");
            B = input.nextInt();

            if (X < 7 || X > 29 || X % 2 == 0 ||
                Y < 7 || Y > 29 || Y % 2 == 0 ||
                B < 1 || B > 3) {
                System.out.println("Error: X and Y must be odd numbers between 7 and 29, and B must be between 1 and 3.");
            }

        } while (X < 7 || X > 29 || X % 2 == 0 ||
                 Y < 7 || Y > 29 || Y % 2 == 0 ||
                 B < 1 || B > 3);

        int centerRow = Y / 2;
        int centerCol = X / 2;

        for (int r = 0; r < Y; r++) {
            int diag1 = (int) Math.round(
                (double) r * (X - 1) / (Y - 1)
            );
            int diag2 = (X - 1) - diag1;

            for (int c = 0; c < X; c++) {
                char ch;

                if ((r == 0 || r == Y - 1) &&
                    (c == 0 || c == X - 1)) {
                    ch = '+';
                }
                else if (r == 0 || r == Y - 1) {
                    ch = '=';
                }
                else if (c == 0 || c == X - 1) {
                    ch = 'I';
                }
                else if (r == centerRow && c == centerCol) {
                    ch = '@';
                }
                else if (c == diag1 || c == diag2) {
                    ch = 'X';
                }
                else if (r == centerRow) {
                    ch = '-';
                }
                else if (c == centerCol) {
                    ch = '|';
                }
                else if ((r / B + c / B) % 2 == 0) {
                    ch = '.';
                }
                else {
                    ch = 'o';
                }

                System.out.print(ch);
            }

            System.out.println();
        }

        input.close();
    }
}
