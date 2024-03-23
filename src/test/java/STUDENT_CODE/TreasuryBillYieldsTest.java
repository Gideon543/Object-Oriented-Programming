package STUDENT_CODE;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CORRECT_CODE.TreasuryBillYields;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.is;

class TreasuryBillYieldsTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final PrintStream standardOut = System.out;

    private static final Map<String, Integer> testCriteria = new HashMap<>();

    static {
        // Initialize test scores
        testCriteria.put("sanity", 1); // Each sanity test is worth 1 point
        testCriteria.put("comprehensive", 3); // Each comprehensive test is worth 3 points
        testCriteria.put("robustness", 3); // Each robustness test is worth 5 points
    }

    static int studentScore = 0;


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(standardOut);
    }

    @Test
    void sanityTestOne() {
        double result = TreasuryBillYields.computeInvestmentValue(1000, 5, 1, 0, 1);
        assertThat(result, is(1050.00));
        studentScore += testCriteria.get("sanity");
    }

    @Test
    void sanityTestTwo() {
        double result = TreasuryBillYields.computeInvestmentValue(1000, 3, 4, 100, 4);
        assertThat(result, is(1334.86));
        studentScore += testCriteria.get("sanity");
    }

    @Test
    void sanityTestThree() {
        double result = TreasuryBillYields.computeInvestmentValue(500, 2, 12, 50, 12);
        assertThat(result, is(1065.62));
        studentScore += testCriteria.get("sanity");
    }

    @Test
    void sanityTestFour() {
        double result = TreasuryBillYields.computeInvestmentValue(2000, 10, 2, 0, 2);
        assertThat(result, is(2205.00));
        studentScore += testCriteria.get("sanity");
    }

    @Test
    void sanityTestFive() {
        double result = TreasuryBillYields.computeInvestmentValue(1500, 4, 1, 200, 1);
        assertThat(result, is(1560.00));
        studentScore += testCriteria.get("sanity");
    }

    @Test
    void comprehensiveTestOne() {
        double result = TreasuryBillYields.computeInvestmentValue(5000, 7, 12, 500, 12);
        assertThat(result, is(11057.74));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestTwo() {
        double result = TreasuryBillYields.computeInvestmentValue(10000, 8, 4, 200, 8);
        assertThat(result, is(13233.19));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestThree() {
        double result = TreasuryBillYields.computeInvestmentValue(2500, 6, 1, 0, 10);
        assertThat(result, is(4477.12));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestFour() {
        double result = TreasuryBillYields.computeInvestmentValue(3000, 9, 12, 300, 24);
        assertThat(result, is(11145.78));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestFive() {
        double result = TreasuryBillYields.computeInvestmentValue(1000, 5, 365, 100, 365);
        assertThat(result, is(38376.54));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestSix() {
        double result = TreasuryBillYields.computeInvestmentValue(750, 4.5, 52, 75, 104);
        assertThat(result, is(8903.68));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestSeven() {
        double result = TreasuryBillYields.computeInvestmentValue(600, 3.5, 365, 10, 730);
        assertThat(result, is(8194.69));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestEight() {
        double result = TreasuryBillYields.computeInvestmentValue(2000, 10, 1, 1000, 5);
        assertThat(result, is(8326.12));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestNine() {
        double result = TreasuryBillYields.computeInvestmentValue(500, 8, 12, 50, 60);
        assertThat(result, is(4368.77));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void comprehensiveTestTen() {
        double result = TreasuryBillYields.computeInvestmentValue(100, 12, 4, 25, 20);
        assertThat(result, is(827.37));
        studentScore += testCriteria.get("comprehensive");
    }

    @Test
    void robustnessTestOne() {
        double result = TreasuryBillYields.computeInvestmentValue(100000, 15, 12, 10000, 12);
        assertThat(result, is(234679.07));
        studentScore += testCriteria.get("robustness");
    }

    @Test
    void robustnessTestTwo() {
        double result = TreasuryBillYields.computeInvestmentValue(500000, 10, 4, 50000, 8);
        assertThat(result, is(996007.24));
        studentScore += testCriteria.get("robustness");
    }

    @Test
    void robustnessTestThree() {
        double result = TreasuryBillYields.computeInvestmentValue(1000000, 5, 1, 0, 50);
        assertThat(result, is(11467399.79));
        studentScore += testCriteria.get("robustness");
    }

    @Test
    void robustnessTestFour() {
        double result = TreasuryBillYields.computeInvestmentValue(250000, 20, 365, 25000, 365);
        assertThat(result, is(10378782.5));
        studentScore += testCriteria.get("robustness");
    }

    @Test
    void robustnessTestFive() {
        double result = TreasuryBillYields.computeInvestmentValue(50000, 12, 52, 5000, 104);
        assertThat(result, is(645489.9));
        studentScore += testCriteria.get("robustness");
    }

    @AfterAll
    static void printStudentScores() {
        System.out.println("Treasury Bill test score: " + studentScore +"/50");
    }

}



