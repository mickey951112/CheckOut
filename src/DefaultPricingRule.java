// DefaultPricingRule.java

// This class implements the PricingRule interface and represents a default pricing rule.
// Default pricing rules calculate the total price based on the unit price and quantity.
public class DefaultPricingRule implements PricingRule {
    // The unit price for the item
    private int unitPrice;

    // Constructor to initialize the DefaultPricingRule with a given unit price
    public DefaultPricingRule(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // Implementation of the calculatePrice method from the PricingRule interface.
    // It calculates the total price based on the unit price and the quantity of items.
    @Override
    public int calculatePrice(int quantity) {
        // Total price is calculated by multiplying the unit price by the quantity
        return quantity * unitPrice;
    }
}
