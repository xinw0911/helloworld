import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Calculator Tests")
class CalculatorTest {
    
    private Calculator calculator;
    
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    @DisplayName("Addition should work correctly")
    void testAddition() {
        double result = calculator.calculate(5, 3, "+");
        assertThat(result).isEqualTo(8.0);
    }
    
    @Test
    @DisplayName("Subtraction should work correctly")
    void testSubtraction() {
        double result = calculator.calculate(10, 4, "-");
        assertThat(result).isEqualTo(6.0);
    }
    
    @Test
    @DisplayName("Multiplication should work correctly")
    void testMultiplication() {
        double result = calculator.calculate(6, 7, "*");
        assertThat(result).isEqualTo(42.0);
    }
    
    @Test
    @DisplayName("Division should work correctly")
    void testDivision() {
        double result = calculator.calculate(15, 3, "/");
        assertThat(result).isEqualTo(5.0);
    }
    
    @Test
    @DisplayName("Power operation should work correctly")
    void testPower() {
        double result = calculator.calculate(2, 3, "^");
        assertThat(result).isEqualTo(8.0);
    }
    
    @Test
    @DisplayName("Square root should work correctly")
    void testSquareRoot() {
        double result = calculator.calculate(16, 0, "sqrt");
        assertThat(result).isEqualTo(4.0);
    }
    
    @Test
    @DisplayName("Division by zero should throw ArithmeticException")
    void testDivisionByZero() {
        assertThatThrownBy(() -> calculator.calculate(10, 0, "/"))
            .isInstanceOf(ArithmeticException.class)
            .hasMessage("Cannot divide by zero");
    }
    
    @Test
    @DisplayName("Square root of negative number should throw ArithmeticException")
    void testSquareRootOfNegative() {
        assertThatThrownBy(() -> calculator.calculate(-4, 0, "sqrt"))
            .isInstanceOf(ArithmeticException.class)
            .hasMessage("Cannot calculate square root of negative number");
    }
    
    @Test
    @DisplayName("Invalid operation should throw IllegalArgumentException")
    void testInvalidOperation() {
        assertThatThrownBy(() -> calculator.calculate(5, 3, "%"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid operation: %");
    }
    
    @Test
    @DisplayName("Individual operation methods should work correctly")
    void testIndividualMethods() {
        assertThat(calculator.add(2, 3)).isEqualTo(5.0);
        assertThat(calculator.subtract(10, 3)).isEqualTo(7.0);
        assertThat(calculator.multiply(4, 5)).isEqualTo(20.0);
        assertThat(calculator.divide(20, 4)).isEqualTo(5.0);
        assertThat(calculator.power(3, 2)).isEqualTo(9.0);
        assertThat(calculator.sqrt(25)).isEqualTo(5.0);
    }
}
