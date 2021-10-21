package salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    int quantity;
    String description;
    BigDecimal price;
    int tax;
    boolean imported;

    public Product(int quantity, String description, BigDecimal price) {
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        checkTaxes(description);
    }

    public void checkTaxes(String description) {
        if (description.contains("imported")) {
            tax += 5;
            imported = true;
        }
        if (!(description.matches("(.*)(book|chocolate|pill)(.*)"))) {
            tax += 10;
        }
    }

    public BigDecimal calculateTaxesOnly() {
        BigDecimal temp = price.multiply(new BigDecimal(tax)).divide(new BigDecimal(100));
        return roundBigDecimalToNearest5Cents(temp, new BigDecimal("0.05"), RoundingMode.UP);
    }

    public static BigDecimal roundBigDecimalToNearest5Cents(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
        BigDecimal divided = value.divide(increment, 0, roundingMode);
        return divided.multiply(increment);
    }

    public BigDecimal calculateTotalPrice() {
        return price.add(calculateTaxesOnly()).multiply(new BigDecimal(quantity));
    }

    public String print() {
        return quantity + " " + description + ": " + calculateTotalPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}

