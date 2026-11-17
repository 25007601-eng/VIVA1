import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter robot commands: ");
        String commands = input.nextLine();

        for (int i = 0; i < commands.length(); i++) {
            char c = commands.charAt(i);

            if ("FBLRfblr".indexOf(c) == -1) {
                System.out.println("Invalid Command String");
                input.close();
                return;
            }
        }

        int xP = 0, yP = 0, dirP = 0;
        int xQ = 0, yQ = 0, dirQ = 0;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        char[] headings = {'N', 'E', 'S', 'W'};

        int meetings = 0;
        int originReturns = 0;
        int maxGap = 0;

        StringBuilder simplified = new StringBuilder();

        for (int i = 0; i < commands.length(); i++) {
            char c = commands.charAt(i);

            boolean robotP = Character.isUpperCase(c);
            char move = Character.toUpperCase(c);
            boolean moved = false;

            if (robotP) {
                if (move == 'R') {
                    dirP = (dirP + 1) % 4;
                }
                else if (move == 'L') {
                    dirP = (dirP + 3) % 4;
                }
                else if (move == 'F') {
                    xP += dx[dirP];
                    yP += dy[dirP];
                    moved = true;
                }
                else if (move == 'B') {
                    xP -= dx[dirP];
                    yP -= dy[dirP];
                    moved = true;
                }
            }
            else {
                if (move == 'R') {
                    dirQ = (dirQ + 1) % 4;
                }
                else if (move == 'L') {
                    dirQ = (dirQ + 3) % 4;
                }
                else if (move == 'F') {
                    xQ += dx[dirQ];
                    yQ += dy[dirQ];
                    moved = true;
                }
                else if (move == 'B') {
                    xQ -= dx[dirQ];
                    yQ -= dy[dirQ];
                    moved = true;
                }
            }

            if (moved) {
                if (xP == xQ && yP == yQ) {
                    meetings++;
                }

                if (robotP && xP == 0 && yP == 0) {
                    originReturns++;
                }
                else if (!robotP && xQ == 0 && yQ == 0) {
                    originReturns++;
                }

                int gap = Math.abs(xP - xQ)
                        + Math.abs(yP - yQ);

                maxGap = Math.max(maxGap, gap);
            }

            int n = simplified.length();
            char opposite = ' ';

            if (c == 'F') opposite = 'B';
            else if (c == 'B') opposite = 'F';
            else if (c == 'L') opposite = 'R';
            else if (c == 'R') opposite = 'L';
            else if (c == 'f') opposite = 'b';
            else if (c == 'b') opposite = 'f';
            else if (c == 'l') opposite = 'r';
            else if (c == 'r') opposite = 'l';

            if (n > 0 &&
                simplified.charAt(n - 1) == opposite) {
                simplified.deleteCharAt(n - 1);
            }
            else {
                simplified.append(c);
                n = simplified.length();

                if ((c == 'L' || c == 'R' ||
                     c == 'l' || c == 'r') &&
                    n >= 4 &&
                    simplified.charAt(n - 1) == c &&
                    simplified.charAt(n - 2) == c &&
                    simplified.charAt(n - 3) == c &&
                    simplified.charAt(n - 4) == c) {

                    simplified.delete(n - 4, n);
                }
            }
        }

        System.out.println("Meetings: " + meetings);
        System.out.println("Origin Returns: " + originReturns);
        System.out.println("Maximum Gap: " + maxGap);

        System.out.println("Robot P Final: (" +
            xP + "," + yP + ") facing " + headings[dirP]);

        System.out.println("Robot Q Final: (" +
            xQ + "," + yQ + ") facing " + headings[dirQ]);

        System.out.println("Simplified Commands: " +
            (simplified.length() == 0 ?
            "EMPTY" : simplified.toString()));

        input.close();
    }
}
