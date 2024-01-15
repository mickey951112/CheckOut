import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;

// JUnit test class for CheckOut functionality
public class TestCheckOut {

    // Test case to verify total prices based on provided pricing rules
    @Test
    public void testTotals() {
        // Set up pricing rules for items A, B, C, and D
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(50, 3, 130));
        pricingRules.put('B', new PricingRule(30, 2, 45));
        pricingRules.put('C', new PricingRule(20));
        pricingRules.put('D', new PricingRule(15));

        // Verify total prices for different combinations of items
        assertEquals(0, price("", pricingRules));
        assertEquals(50, price("A", pricingRules));
        assertEquals(80, price("AB", pricingRules));
        assertEquals(115, price("CDBA", pricingRules));

        assertEquals(100, price("AA", pricingRules));
        assertEquals(130, price("AAA", pricingRules));
        assertEquals(180, price("AAAA", pricingRules));
        assertEquals(230, price("AAAAA", pricingRules));
        assertEquals(260, price("AAAAAA", pricingRules));

        assertEquals(160, price("AAAB", pricingRules));
        assertEquals(175, price("AAABB", pricingRules));
        assertEquals(190, price("AAABBD", pricingRules));
        assertEquals(190, price("DABABA", pricingRules));
    }

    // Test case to verify incremental total calculation
    @Test
    public void testIncremental() {
        // Set up pricing rules for items A, B, C, and D
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(50, 3, 130));
        pricingRules.put('B', new PricingRule(30, 2, 45));
        pricingRules.put('C', new PricingRule(20));
        pricingRules.put('D', new PricingRule(15));

        // Create a new CheckOut instance with pricing rules
        CheckOut co = new CheckOut(pricingRules);

        // Verify total prices after scanning different items incrementally
        assertEquals(0, co.total());
        co.scan('A');
        assertEquals(50, co.total());
        co.scan('B');
        assertEquals(80, co.total());
        co.scan('A');
        assertEquals(130, co.total());
        co.scan('A');
        assertEquals(160, co.total());
        co.scan('B');
        assertEquals(175, co.total());
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
