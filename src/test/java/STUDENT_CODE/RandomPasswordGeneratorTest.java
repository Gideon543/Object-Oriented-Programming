package STUDENT_CODE;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CORRECT_CODE.RandomPasswordGenerator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomPasswordGeneratorTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final Map<String, Integer> scoreMap = new HashMap<>();

    static {
        // Initialize test scores
        scoreMap.put("sanity", 2); // Each sanity test is worth 1 point
        scoreMap.put("comprehensive", 4); // Each comprehensive test is worth 3 points
        scoreMap.put("robustness", 4); // Each robustness test is worth 5 points
    }

    static int totalScore = 0;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(standardOut);
    }

    // Sanity Tests
    @Test
    void sanityTestPasswordBasicValidity() {
        String password = RandomPasswordGenerator.generatePassword(8, 2, 2, 2, "!@#$");
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestPasswordMinimumLength() {
        String password = RandomPasswordGenerator.generatePassword(8, 1, 1, 1, "!@#$");
        assertTrue(password.length() >= 8);
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestPasswordWithNoSpecialChars() {
        String password = RandomPasswordGenerator.generatePassword(10, 2, 2, 0, "");
        assertTrue(masterCheckPassword(10, 2, 2, 0, "", password));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestPasswordWithNoDigits() {
        String password = RandomPasswordGenerator.generatePassword(10, 2, 0, 2, "!@#$");
        assertTrue(masterCheckPassword(10, 2, 0, 2, "!@#$", password));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestPasswordWithNoUpperCase() {
        String password = RandomPasswordGenerator.generatePassword(10, 0, 2, 2, "!@#$");
        assertTrue(masterCheckPassword(10, 0, 2, 2, "!@#$", password));
        totalScore += scoreMap.get("sanity");
    }

    // Comprehensive Tests
    @Test
    void comprehensiveTestHighUpperCount() {
        String password = RandomPasswordGenerator.generatePassword(12, 6, 2, 2, "!@#$");
        assertTrue(masterCheckPassword(12, 6, 2, 2, "!@#$", password));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestHighDigitCount() {
        String password = RandomPasswordGenerator.generatePassword(12, 2, 6, 2, "!@#$");
        assertTrue(masterCheckPassword(12, 2, 6, 2, "!@#$", password));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestHighSpecialCount() {
        String password = RandomPasswordGenerator.generatePassword(12, 2, 2, 6, "!@#$");
        assertTrue(masterCheckPassword(12, 2, 2, 6, "!@#$", password));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestLongPassword() {
        String password = RandomPasswordGenerator.generatePassword(20, 5, 5, 5, "!@#$");
        assertTrue(masterCheckPassword(20, 5, 5, 5, "!@#$", password));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestExactLength() {
        String password = RandomPasswordGenerator.generatePassword(15, 3, 3, 3, "!@#$");
        assertTrue(masterCheckPassword(15, 3, 3, 3, "!@#$", password));
        assertTrue(password.length() >= 15);
        totalScore += scoreMap.get("comprehensive");
    }

    // More comprehensive tests would similarly vary parameters to test the generation logic under different conditions.

    // Robustness Tests
    @Test
    void robustnessTestVeryLongPassword() {
        String password = RandomPasswordGenerator.generatePassword(50, 10, 10, 10, "!@#$%^&*()");
        assertTrue(masterCheckPassword(50, 10, 10, 10, "!@#$%^&*()", password));
        totalScore += scoreMap.get("robustness");
    }


    @Test
    void robustnessTestPasswordWithAllSpecialCharacters() {
        String password = RandomPasswordGenerator.generatePassword(12, 2, 2, 2, "!@#$%^&*()");
        assertTrue(masterCheckPassword(12, 2, 2, 2, "!@#$%^&*()", password));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestPasswordWithMinimalRequirements() {
        String password = RandomPasswordGenerator.generatePassword(5, 1, 1, 1, "!");
        assertTrue(masterCheckPassword(5, 1, 1, 1, "!", password));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestMaximalMixedCharacters() {
        String password = RandomPasswordGenerator.generatePassword(30, 10, 10, 10, "!@#$%^&*()");
        assertTrue(masterCheckPassword(30, 10, 10, 10, "!@#$%^&*()", password));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestEdgeCaseWithOneOfEachType() {
        String password = RandomPasswordGenerator.generatePassword(4, 1, 1, 1, "!");
        assertTrue(masterCheckPassword(4, 1, 1, 1, "!", password));
        totalScore += scoreMap.get("robustness");
    }

    @AfterAll
    public static void printTotalScore()
    {
        System.out.println("Random Password Generator test score: " + totalScore +"/50");
    }

    public static boolean masterCheckPassword(int minLength, int minUpper, int minDigits, int minSpecial, String specialCharacters, String password)
    {
        final String DIGITS = "123456789";
        final String UPPER_CASES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int specialCount = 0;
        int upperCount = 0;
        int digitsCount = 0;

        if (password.length() < minLength) return false;

        for (int i = 0; i < password.length(); i++) {

            char character = password.charAt(i);

            //check for special character
            if (specialCharacters.indexOf(character) != -1) specialCount++;

                //check for uppercase
            else if (UPPER_CASES.indexOf(character) != -1) upperCount++;

                //check for digits
            else if (DIGITS.indexOf(character) != -1) digitsCount++;

        }

        return specialCount >= minSpecial && upperCount >= minUpper && digitsCount >= minDigits;
    }
}
