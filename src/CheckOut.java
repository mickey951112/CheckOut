// CheckOut.java
import java.util.HashMap;
import java.util.Map;

// Checkout class to handle scanning items and calculating the total price
public class CheckOut {
    private Map<Character, PricingRule> pricingRules;
    private Map<Character, Integer> items;

    // Constructor to initialize the Checkout with pricing rules
    public CheckOut(Map<Character, PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
        this.items = new HashMap<>();
    }

    // Method to scan an item and update the count
    public void scan(char item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    // Method to calculate the total price based on scanned items and pricing rules
    public int total() {
        int totalPrice = 0;

        // Iterate through scanned items
        for (Map.Entry<Character, Integer> entry : items.entrySet()) {
            char item = entry.getKey();
            int count = entry.getValue();

            // Check if there is a pricing rule for the item
            if (pricingRules.containsKey(item)) {
                totalPrice += pricingRules.get(item).calculatePrice(count);
            } else {
                // Default to individual unit price if no special pricing rule
                totalPrice += new DefaultPricingRule(50).calculatePrice(count);
            }
        }

        return totalPrice;
    }

    // Example main method demonstrating the usage of the Checkout class
    public static void main(String[] args) {
        // Example usage with pricing rules
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new SpecialPricingRule(45, 2, 80));
        pricingRules.put('B', new SpecialPricingRule(30, 3, 70));
        pricingRules.put('C', new SpecialPricingRule(20, 3, 50));
        pricingRules.put('D', new DefaultPricingRule(15));
        pricingRules.put('E', new SpecialPricingRule(50, 2, 90));

        CheckOut co = new CheckOut(pricingRules);
        co.scan('A');
        co.scan('B');
        co.scan('A');
        co.scan('A');
        co.scan('B');
        co.scan('C');
        co.scan('D');
        co.scan('E');
        co.scan('C');
        co.scan('E');
        co.scan('C');
        co.scan('D');
        int price = co.total();

        System.out.println("Total Price: " + price);
    }
}