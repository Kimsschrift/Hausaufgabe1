package htw.berlin.prog2.ha1;

import com.google.errorprone.annotations.Var;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     Teilaufgabe 1: Schreiben Sie einen neuen zusätzlichen Test, der eine bisher nicht getestete
     Funktionalität abdeckt, die bereits funktioniert und der daher direkt grün wird. **/
    @Test
    @DisplayName("should reset screen on first press and all values on second press of clear key")
    void testPressClearKey() {
        Calculator calc = new Calculator();

        // Test first press
        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);

        calc.pressClearKey();

        calc.pressDigitKey(1);
        calc.pressEqualsKey();

        String expected = "6";
        String actual = calc.readScreen();
        assertEquals(expected, actual);

        // Test second press
        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(5);

        calc.pressClearKey();
        calc.pressClearKey();

        expected = "0";
        actual = calc.readScreen();
        assertEquals(expected, actual);
    }

    /**
     Teilaufgabe 2: Schreiben Sie zwei weitere zusätzliche Tests, die zwei unterschiedliche
     Fehlerkategorien aufdecken (d.h. deren Fehlerursachen in unterschiedlichen Methoden liegen) und
     somit fehlschlagen. **/

    @Test
    @DisplayName("should repeat the last operation when equals key is pressed consecutively")
    void testConsecutiveEqualsKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressEqualsKey(); // wenn man zweimal Equalskey gedrückt hat.

        String expected = "15"; // 9 + 3 + 3 = 15
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    } // die Methode pressEqualsKey() wird verändert und jetzt erfolgreich funktioniert.
    // TeilAufgabe3 o

    @Test
    @DisplayName("The value after the decimal point should be displayed continuously on the screen.")
    void testContinueScreen() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(7);

        String expected = "0.7";
        String actual = calc.readScreen();

        assertEquals(expected, actual);


        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("+");
        expected = "8.7";   // prüfen Zwischenergebnis
        actual = calc.readScreen();
        assertEquals(expected, actual);

        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        expected = "10.7"; // prüfen kontinuierliche Berechnungen 0.7 + 8 + 2 = 10.7
        actual = calc.readScreen();
        assertEquals(expected, actual);

    }
    // die Methode pressDigitKey() und pressBinaryOperationKey() werden verändert und jetzt erfolgreich funktioniert.
    // TeilAufgabe3 o

    /**
     Teilaufgabe 3: Schreiben Sie zwei Bugfixes (also Änderungen der Implementierungsklasse
     Calculator), so dass die zwei zuvor hinzugefügten Tests erfolgreich durchlaufen. Falls Sie dabei das
     Verhalten so erweitern, dass es über das im JavaDoc spezifizierte hinausgeht (aber zum Verhalten
     des Online Calculator passt), dann erweitern Sie bitte auch das JavaDoc entsprechend. **/




    /**
     Fushen Sie Ihre Tests & Änderungen auf Ihr eigenes, geforktes Repository,
     und zwar strukturiert in mindestens drei Commits:
     z.B. 1. Commit für neuer grüner Test, 2. Commit für neuer roter Test,
     3. Commit für den Fix zu diesem Test, 4. Commit für weiteren neuen roter Test,
     5. Commit für Fix zu diesem Test.
     Stellen Sie zum Abschluss einen Pull Request auf das obere Repository,
     und zwar vor der oben genannten Deadline.
     **/


}

