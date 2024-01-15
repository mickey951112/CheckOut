import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

// JUnit test class for CheckOut functionality
public class TestCheckOut {

    // Test case to verify total prices based on provided pricing rules
    @Test
    public void testTotals() {
        // Set up pricing rules for items A, B, C, D, and E
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(45, 2, 80));
        pricingRules.put('B', new PricingRule(30, 3, 70));
        pricingRules.put('C', new PricingRule(20, 3, 50));
        pricingRules.put('D', new PricingRule(15));
        pricingRules.put('E', new PricingRule(50, 2, 90));

        // Verify total prices for different combinations of items
        assertEquals(0, price("", pricingRules));
        assertEquals(45, price("A", pricingRules));
        assertEquals(75, price("AB", pricingRules));
        assertEquals(110, price("CDBA", pricingRules));

        assertEquals(80, price("AA", pricingRules));
        assertEquals(125, price("AAA", pricingRules));
        assertEquals(160, price("AAAA", pricingRules));
        assertEquals(205, price("AAAAA", pricingRules));
        assertEquals(240, price("AAAAAA", pricingRules));

        assertEquals(60, price("BB", pricingRules));
        assertEquals(70, price("BBB", pricingRules));
        assertEquals(100, price("BBBB", pricingRules));
        assertEquals(130, price("BBBBB", pricingRules));
        assertEquals(140, price("BBBBBB", pricingRules));

        assertEquals(155, price("AAAB", pricingRules));
        assertEquals(185, price("AAABB", pricingRules));
        assertEquals(200, price("AAABBD", pricingRules));
        assertEquals(250, price("AAABBDE", pricingRules));
        assertEquals(290, price("AAABBDEE", pricingRules));
        assertEquals(300, price("AABBCCDDEE", pricingRules));
    }

    // Test case to verify incremental total calculation
    @Test
    public void testIncremental() {
        // Set up pricing rules for items A, B, C, and D
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(45, 2, 80));
        pricingRules.put('B', new PricingRule(30, 3, 70));
        pricingRules.put('C', new PricingRule(20, 3, 50));
        pricingRules.put('D', new PricingRule(15));
        pricingRules.put('E', new PricingRule(50, 2, 90));

        // Create a new CheckOut instance with pricing rules
        CheckOut co = new CheckOut(pricingRules);

        // Verify total prices after scanning different items incrementally
        assertEquals(0, co.total());
        co.scan('A');
        assertEquals(45, co.total());
        co.scan('B');
        assertEquals(75, co.total());
        co.scan('A');
        assertEquals(110, co.total());
        co.scan('A');
        assertEquals(155, co.total());
        co.scan('B');
        assertEquals(185, co.total());
        co.scan('A');
        assertEquals(220, co.total());
        co.scan('B');
        assertEquals(230, co.total());
    }

    // Helper method to calculate total price based on goods and pricing rules
    private int price(String goods, Map<Character, PricingRule> pricingRules) {
        // Create a new CheckOut instance with pricing rules
        CheckOut co = new CheckOut(pricingRules);

        // Scan items based on the provided goods string
        goods.chars().mapToObj(c -> (char) c).forEach(co::scan);

        // Return the calculated total price
        return co.total();
    }
}
