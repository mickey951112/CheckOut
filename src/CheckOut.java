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
                totalPrice += count * 50; // Assuming default unit price is 50 cents
            }
        }

        return totalPrice;
    }

    // Example main method demonstrating the usage of the Checkout class
    public static void main(String[] args) {
        // Example usage with pricing rules
        Map<Character, PricingRule> pricingRules = new HashMap<>();
        pricingRules.put('A', new PricingRule(50, 3, 130));
        pricingRules.put('B', new PricingRule(30, 2, 45));
        pricingRules.put('C', new PricingRule(20));
        pricingRules.put('D', new PricingRule(15));

        CheckOut co = new CheckOut(pricingRules);
        co.scan('A');
        co.scan('B');
        co.scan('A');
        int price = co.total();

        System.out.println("Total Price: " + price);
    }
}

// PricingRule class to represent the pricing details for an item
class PricingRule {
    private int unitPrice;
    private int specialQuantity;
    private int specialPrice;

    // Constructor for a pricing rule with a special offer
    public PricingRule(int unitPrice, int specialQuantity, int specialPrice) {
        this.unitPrice = unitPrice;
        this.specialQuantity = specialQuantity;
        this.specialPrice = specialPrice;
    }

    // Constructor for a pricing rule without a special offer
    public PricingRule(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Method to calculate the total price based on the quantity and pricing rule
    public int calculatePrice(int quantity) {
        int totalPrice = 0;

        // Check if there is a special offer and the quantity is sufficient
        if (specialQuantity > 0 && quantity >= specialQuantity) {
            int specialSets = quantity / specialQuantity;
            int remainingItems = quantity % specialQuantity;

            // Calculate the total price with the special offer
            totalPrice = specialSets * specialPrice + remainingItems * unitPrice;
        } else {
            // Calculate the total price without a special offer
            totalPrice = quantity * unitPrice;
        }

        return totalPrice;
    }
}