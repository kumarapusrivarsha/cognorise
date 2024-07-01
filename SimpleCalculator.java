import java.util.Scanner;

public class SimpleCalculator {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter first number: ");
    double num1 = scanner.nextDouble();

    System.out.print("Enter an operator (+, -, *, /): ");
    char operator = scanner.next().charAt(0);

    System.out.print("Enter second number: ");
    double num2 = scanner.nextDouble();

    double result = calculate(num1, num2, operator);

    if (result == Double.MAX_VALUE) {
      System.out.println("Error: Invalid operator or division by zero.");
    } else {
      System.out.println(num1 + " " + operator + " " + num2 + " = " + result);
    }
  }

  public static double calculate(double num1, double num2, char operator) {
    switch (operator) {
      case '+':
        return num1 + num2;
      case '-':
        return num1 - num2;
      case '*':
        return num1 * num2;
      case '/':
        if (num2 == 0) {
          return Double.MAX_VALUE; // Or another value to indicate error
        } else {
          return num1 / num2;
        }
      default:
        return Double.MAX_VALUE; // Or another value to indicate error
    }
  }
}
