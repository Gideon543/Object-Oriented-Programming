package STUDENT_CODE;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


class EssentialsStoreTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final Map<String, Integer> scoreMap = new HashMap<>();

    static {
        // Initialize test scores
        scoreMap.put("sanity", 1); // Each sanity test is worth 1 point
        scoreMap.put("comprehensive", 3); // Each comprehensive test is worth 3 points
        scoreMap.put("robustness", 3); // Each robustness test is worth 5 points
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

    // Sanity Checks
    @Test
    void sanityTestOne() {
        assertEquals(0.00, EssentialsStore.computeChangeBreakdown(100.00, 100.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString(""));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestTwo() {
        assertEquals(50.00, EssentialsStore.computeChangeBreakdown(150.00, 200.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("1 ₵50 note"));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestThree() {
        assertEquals(0.10, EssentialsStore.computeChangeBreakdown(99.90, 100.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("1 10p coin"));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestFour() {
        assertEquals(5.00, EssentialsStore.computeChangeBreakdown(195.00, 200.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("1 ₵5 note"));
        totalScore += scoreMap.get("sanity");
    }

    @Test
    void sanityTestFive() {
        assertEquals(40.00, EssentialsStore.computeChangeBreakdown(60.00, 100.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("2 ₵20 notes"));
        totalScore += scoreMap.get("sanity");
    }

    // Comprehensive Checks

    @Test
    void comprehensiveTestOne() {
        assertEquals(211.10, EssentialsStore.computeChangeBreakdown(288.90, 500.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("1 ₵200 note"));
        assertThat(output, containsString("1 ₵10 note"));
        assertThat(output, containsString("1 ₵1 note"));
        assertThat(output, containsString("1 10p coin"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestTwo() {
        assertEquals(199.90, EssentialsStore.computeChangeBreakdown(0.10, 200.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵50 note"));
        assertThat(output, containsString("2 ₵20 notes"));
        assertThat(output, containsString("1 ₵5 note"));
        assertThat(output, containsString("2 ₵2 notes"));
        assertThat(output, containsString("1 50p coin"));
        assertThat(output, containsString("2 20p coins"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestThree() {
        assertEquals(176.55, EssentialsStore.computeChangeBreakdown(123.45, 300.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵50 note"));
        assertThat(output, containsString("1 ₵20 note"));
        assertThat(output, containsString("1 ₵5 note"));
        assertThat(output, containsString("1 ₵1 note"));
        assertThat(output, containsString("1 50p coin"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestFour() {
        assertEquals(0.01, EssentialsStore.computeChangeBreakdown(19.99, 20.00));
        assertEquals("", outputStreamCaptor.toString().trim());
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestFive() {
        assertEquals(800.01, EssentialsStore.computeChangeBreakdown(199.99, 1000.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("4 ₵200 notes"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestSix() {
        assertEquals(499.01, EssentialsStore.computeChangeBreakdown(0.99, 500.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("2 ₵200 notes"));
        assertThat(output, containsString("1 ₵50 note"));
        assertThat(output, containsString("2 ₵20 notes"));
        assertThat(output, containsString("1 ₵5 note"));
        assertThat(output, containsString("2 ₵2 notes"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestSeven() {
        assertEquals(750.00, EssentialsStore.computeChangeBreakdown(250.00, 1000.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("3 ₵200 notes"));
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵50 note"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestEight() {
        assertEquals(1111.56, EssentialsStore.computeChangeBreakdown(123.00, 1234.56));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("5 ₵200 notes"));
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵10 note"));
        assertThat(output, containsString("1 ₵1 note"));
        assertThat(output, containsString("1 50p coin"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestNine() {
        assertEquals(0.89, EssentialsStore.computeChangeBreakdown(1.11, 2.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("1 50p coin"));
        assertThat(output, containsString("1 20p coin"));
        assertThat(output, containsString("1 10p coin"));
        totalScore += scoreMap.get("comprehensive");
    }

    @Test
    void comprehensiveTestTen() {
        assertEquals(400.90, EssentialsStore.computeChangeBreakdown(99.10, 500.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("2 ₵200 notes"));
        assertThat(output, containsString("1 50p coin"));
        assertThat(output, containsString("2 20p coins"));
        totalScore += scoreMap.get("comprehensive");
    }

    // Robustness Tests

    @Test
    void robustnessTestOne() {
        assertEquals(1000000.00, EssentialsStore.computeChangeBreakdown(1000000.00, 2000000.00));
        assertThat(outputStreamCaptor.toString().trim(), containsString("5000 ₵200 notes"));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestTwo() {
        assertEquals(9999.99, EssentialsStore.computeChangeBreakdown(0.01, 10000.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("49 ₵200 notes"));
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵50 note"));
        assertThat(output, containsString("2 ₵20 notes"));
        assertThat(output, containsString("1 ₵5 note"));
        assertThat(output, containsString("2 ₵2 notes"));
        assertThat(output, containsString("1 50p coin"));
        assertThat(output, containsString("2 20p coins"));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestThree() {
        assertEquals(0.01, EssentialsStore.computeChangeBreakdown(999999.99, 1000000.00));
        assertEquals("", outputStreamCaptor.toString().trim());
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestFour() {
        assertEquals(499999.50, EssentialsStore.computeChangeBreakdown(500000.50, 1000000.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("2499 ₵200 notes"));
        assertThat(output, containsString("1 ₵100 note"));
        assertThat(output, containsString("1 ₵50 note"));
        assertThat(output, containsString("2 ₵20 note"));
        assertThat(output, containsString("1 ₵5 note"));
        assertThat(output, containsString("2 ₵2 notes"));
        assertThat(output, containsString("1 50p coin"));
        totalScore += scoreMap.get("robustness");
    }

    @Test
    void robustnessTestFive() {
        assertEquals(3765432.11, EssentialsStore.computeChangeBreakdown(1234567.89, 5000000.00));
        String output = outputStreamCaptor.toString().trim();
        assertThat(output, containsString("18827 ₵200 notes"));
        assertThat(output, containsString("1 ₵20 note"));
        assertThat(output, containsString("1 ₵10 note"));
        assertThat(output, containsString("1 ₵2 note"));
        totalScore += scoreMap.get("robustness");
    }

    @AfterAll
    public static void printTotalScore()
    {
        System.out.println("Essentials Store test score: " + totalScore +"/50");
    }
}
