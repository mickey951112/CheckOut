// SpecialPricingRule.java

// This class implements the PricingRule interface and represents a special pricing rule.
// Special pricing rules provide a discounted price for a specific quantity of items.
public class SpecialPricingRule implements PricingRule {
    // The unit price for the item
    private int unitPrice;

    // The quantity required for the special pricing
    private int specialQuantity;

    // The discounted price for the special quantity
    private int specialPrice;

    // Constructor to initialize the SpecialPricingRule with unit price, special quantity, and special price
    public SpecialPricingRule(int unitPrice, int specialQuantity, int specialPrice) {
        this.unitPrice = unitPrice;
        this.specialQuantity = specialQuantity;
        this.specialPrice = specialPrice;
    }

    // Implementation of the calculatePrice method from the PricingRule interface.
    // It calculates the total price based on the special pricing rule and the quantity of items.
    @Override
    public int calculatePrice(int quantity) {
        int totalPrice = 0;

        // Check if the special pricing rule is applicable (special quantity is greater than 0)
        if (specialQuantity > 0 && quantity >= specialQuantity) {
            // Calculate the number of special sets and remaining items
            int specialSets = quantity / specialQuantity;
            int remainingItems = quantity % specialQuantity;

            // Calculate the total price using the special pricing formula
            totalPrice = specialSets * specialPrice + remainingItems * unitPrice;
        } else {
            // If the special pricing rule is not applicable, use the default pricing
            totalPrice = quantity * unitPrice;
        }

        // Return the calculated total price
        return totalPrice;
    }
}
