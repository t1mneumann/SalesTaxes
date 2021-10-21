package salestaxes;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.math.BigDecimal;

public class ProductTest {
    Product testProduct_tax0;
    Product testProduct_tax10;
    Product testProduct_tax15;

    @Before
    public void setUp() {
        testProduct_tax0 = new Product(1, "book", new BigDecimal("14.99"));
        testProduct_tax10 = new Product(1, "music CD", new BigDecimal("7.99"));
        testProduct_tax15 = new Product(1, "imported music CD", new BigDecimal("7.99"));
    }

    @Test
    public void checkCreatedProductTaxes0() {
        Assert.assertEquals(1, testProduct_tax0.getQuantity());
        Assert.assertEquals("book", testProduct_tax0.getDescription());
        Assert.assertEquals(0, new BigDecimal("14.99").compareTo(testProduct_tax0.getPrice()));
        Assert.assertEquals(0, testProduct_tax0.getTax());
        Assert.assertFalse(testProduct_tax0.isImported());
    }

    @Test
    public void checkCreatedProductTaxes10() {
        Assert.assertEquals(10, testProduct_tax10.getTax());
        Assert.assertFalse(testProduct_tax10.isImported());
    }

    @Test
    public void checkCreatedProductTaxes15() {
        Assert.assertEquals(15, testProduct_tax15.getTax());
        Assert.assertTrue(testProduct_tax15.isImported());
    }

    @Test
    public void cCalculateTaxesOnly0() {
        Assert.assertEquals(0, new BigDecimal("0.00").compareTo(testProduct_tax0.calculateTaxesOnly()));
    }

    @Test
    public void calculateTaxesOnly10() {
        Assert.assertEquals(0, new BigDecimal("0.80").compareTo(testProduct_tax10.calculateTaxesOnly()));
    }

    @Test
    public void calculateTaxesOnly15() {
        Assert.assertEquals(0, new BigDecimal("1.20").compareTo(testProduct_tax15.calculateTaxesOnly()));
    }

    @Test
    public void calculateTotalPriceTax0() {
        Assert.assertEquals(0, new BigDecimal("14.99").compareTo(testProduct_tax0.calculateTotalPrice()));
    }

    @Test
    public void calculateTotalPriceTax10() {
        Assert.assertEquals(0, new BigDecimal("8.79").compareTo(testProduct_tax10.calculateTotalPrice()));
    }

    @Test
    public void calculateTotalPriceTax15() {
        Assert.assertEquals(0, new BigDecimal("9.19").compareTo(testProduct_tax15.calculateTotalPrice()));
    }

    @Test
    public void printProduct_Tax0() {
        Assert.assertEquals("1 book: 14.99", testProduct_tax0.print());
    }

    @Test
    public void printProduct_Tax10() {
        Assert.assertEquals("1 music CD: 8.79", testProduct_tax10.print());
    }

    @Test
    public void printProduct_Tax15() {
        Assert.assertEquals("1 imported music CD: 9.19", testProduct_tax15.print());
    }
}
