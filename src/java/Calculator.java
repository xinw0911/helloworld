import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Scanner;

@Command(name = "calculator", mixinStandardHelpOptions = true, version = "1.0.0",
         description = "A simple calculator with enhanced features")
public class Calculator implements Runnable {
    
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Option(names = {"-j", "--json"}, description = "Output results in JSON format")
    private boolean jsonOutput = false;
    
    @Option(names = {"-p", "--precision"}, description = "Number of decimal places (default: 2)")
    private int precision = 2;
    
    public static void main(String[] args) {
        int exitCode = new CommandLine(new Calculator()).execute(args);
        System.exit(exitCode);
    }
    
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        logger.info("Updated at 2025/01/09 14:16 PST - Dummy change to trigger ECR update");
        logger.info("Starting Calculator application");
        logger.info("Starting Calculator application");
        System.out.println("=== Enhanced Java Calculator ===");
        System.out.println("Operations: +, -, *, /, ^, sqrt, exit");
        System.out.println("JSON output: " + (jsonOutput ? "enabled" : "disabled"));
        System.out.println("Precision: " + precision + " decimal places");
        System.out.println("Precision: " + precision + " decimal places");
        System.out.println("Precision: " + precision + " decimal places");
        System.out.println("Precision: " + precision + " decimal places");
        System.out.println("Precision: " + precision + " decimal places");

        while (true) {
            System.out.print("\nEnter first number (or 'exit' to quit): ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                logger.info("Calculator session ended");
                System.out.println("Calculator closed. Goodbye!");
                break;
            }
            
            try {
                double num1 = Double.parseDouble(input);
                
                System.out.print("Enter operation (+, -, *, /, ^, sqrt): ");
                String operation = scanner.nextLine();
                
                double num2 = 0;
                if (!operation.equals("sqrt")) {
                    System.out.print("Enter second number: ");
                    num2 = Double.parseDouble(scanner.nextLine());
                }
                
                double result = calculate(num1, num2, operation);
                double roundedResult = Precision.round(result, precision);
                
                logger.debug("Calculation: {} {} {} = {}", num1, operation, num2, roundedResult);
                
                if (jsonOutput) {
                    outputJson(num1, num2, operation, roundedResult);
                } else {
                    if (operation.equals("sqrt")) {
                        System.out.println("Result: sqrt(" + num1 + ") = " + roundedResult);
                    } else {
                        System.out.println("Result: " + num1 + " " + operation + " " + num2 + " = " + roundedResult);
                    }
                }
                
            } catch (NumberFormatException e) {
                logger.warn("Invalid number format entered");
                System.out.println("Error: Please enter valid numbers.");
            } catch (ArithmeticException e) {
                logger.warn("Arithmetic error: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid operation: {}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    public double calculate(double num1, double num2, String operation) {
        switch (operation) {
            case "+":
                return add(num1, num2);
            case "-":
                return subtract(num1, num2);
            case "*":
                return multiply(num1, num2);
            case "/":
                return divide(num1, num2);
            case "^":
                return power(num1, num2);
            case "sqrt":
                return sqrt(num1);
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public double subtract(double a, double b) {
        return a - b;
    }
    
    public double multiply(double a, double b) {
        return a * b;
    }
    
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
    
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    public double sqrt(double number) {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(number);
    }
    
    private void outputJson(double num1, double num2, String operation, double result) {
        try {
            ObjectNode json = objectMapper.createObjectNode();
            json.put("operation", operation);
            json.put("operand1", num1);
            if (!operation.equals("sqrt")) {
                json.put("operand2", num2);
            }
            json.put("result", result);
            json.put("precision", precision);
            System.out.println(objectMapper.writeValueAsString(json));

            System.out.println(objectMapper.writeValueAsString(json));
            System.out.println(objectMapper.writeValueAsString(json));
            System.out.println(objectMapper.writeValueAsString(json));
        } catch (Exception e) {
            logger.error("Error creating JSON output", e);
            System.out.println("Error creating JSON output: " + e.getMessage());
        }
    }
}
