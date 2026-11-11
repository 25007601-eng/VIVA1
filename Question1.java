import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Original Phrase: ");
        String phrase = input.nextLine();

        // First Pass: Shift Key
        int sum = 0;

        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);

            if (c >= '0' && c <= '9') {
                sum += c - '0';
            }
        }

        int key = sum % 26;

        if (key == 0) {
            key = 13;
        }

        System.out.println("Shift Key: " + key);

        // Second Pass
        // 小写算法 (Lowercase letters)
        String encoded = "";

        for (int i = 0; i < phrase.length(); i++) {
            char c = phrase.charAt(i);

            if (c >= 'a' && c <= 'z') {
                c += key;

                if (c > 'z') {
                    c -= 26;
                }

                encoded += c;
            }

            // 大写算法 (Uppercase letters)
            else if (c >= 'A' && c <= 'Z') {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    encoded += "*" + Character.toLowerCase(c);
                }

                else {
                    if (c == 'Z') {
                        c = 'a';
                        encoded += c;
                    }

                    else {
                        encoded += Character.toLowerCase((char)(c + 1));
                    }
                }
            }

            // 数字 (Digits)
            else if (c >= '0' && c <= '9') {
                encoded += (int)((c - '0') * (c - '0')) % 10;
            }

            // 空格（Spaces）
            else if (c == ' '){
                encoded += '.';
            }

            // 字符（Symbols）
            String symbols = "!@#$%";
            int index = symbols.indexOf(c);

            if (index != -1){
                 int newIndex = (index + 2) % 5;
                encoded += symbols.charAt(newIndex);
              }
             else{
                encoded += c;
             }

            // 反转（Reverse）
            String whole = "";
            for (int i = 0; i < encoded.length(); i++){
                char c = encoded.charAt((encoded.length() - i));
                whole += c;
            }
        }
        

        System.out.println("Encoded Letters: " + encoded);
        System.out.println("Whole encoded string: " + whole);

        input.close();
    }
}

