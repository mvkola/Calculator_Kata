import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Введи в строку математическое выражение: a знак b." +
                "\nИспользуй две арабские или две римские цифры от 1 до 10 и знаки +; -; *; /;");

        Scanner console = new Scanner(System.in);
        String text = console.nextLine();
        text = text.replaceAll("\\s+", "");

        int minus = text.indexOf("-");
        int plus = text.indexOf("+");
        int mul = text.indexOf("*");
        int div = text.indexOf("/");

        int[] findIndex = new int[]{minus, plus, mul, div};
        int index = 0;
        for (int i = 0; i < findIndex.length; i++) {
            if (findIndex[i] > 0) {
                index = findIndex[i];
            }
        }
        char operation;
        operation = text.charAt(index);

        String str1;
        str1 = text.substring(0, index);
        String str2;
        str2 = text.substring(index + 1);

        boolean kata;
        while (kata = true) {
            final int r1, r2;
            try {
                r1 = RomanNumeral.valueOf(str1).ordinal() + 1;
                r2 = RomanNumeral.valueOf(str2).ordinal() + 1;

            } catch (IllegalArgumentException e) {
                System.out.println("N.B. введены знаки, не являющиеся римскими цифрами");
                break;
            }
            if ((r1 < 1) || (r1 > 10) || (r2 < 1) || (r2 > 10)) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception //т.к. введены римские числа вне заданного диапазона");
                    return;
                }
            }
            int roman_result = Integer_Calc(r1, r2, operation);
            if (roman_result < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception // в римской системе нет отрицательных чисел");
                    return;
                }
            } else {
                System.out.println("Ответ:" + RomanNumeral.values()[roman_result - 1]);
                return;
            }
        }
        if (kata == true) {
            int a1, a2;
            try {
                a1 = Integer.parseInt(str1);
                a2 = Integer.parseInt(str2);
            } catch (NumberFormatException e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления" +
                        "\n или формат математической операции не удовлетворяет заданию: два числа и определённый знак");
                return;
            }
            if ((a1 < 1) || (a1 > 10) || (a2 < 1) || (a2 > 10)) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception //т.к. введены арабские числа вне заданного диапазона"); // ОК
                    return;
                }
            }
            int arab_result = Integer_Calc(a1, a2, operation);
            System.out.println("Ответ: " + arab_result);
            return;
        }
    }
    public static int Integer_Calc(int arg1, int arg2, char operation) {
        int result = -1;
        switch (operation) {
            case '+':
                result = arg1 + arg2;
                break;
            case '-':
                result = arg1 - arg2;
                break;
            case '*':
                result = arg1 * arg2;
                break;
            case '/':
                result = arg1 / arg2;
            default:
        }
        return result;
    }
}