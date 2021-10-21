package salestaxes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BillTest {
    Bill bill;
    Product testProduct_tax10;
    Product testProduct_tax15;

    @Before
    public void setUp() throws Exception {
        bill = new Bill();
        testProduct_tax10 = new Product(1, "music CD", new BigDecimal("14.99"));
        testProduct_tax15 = new Product(1, "imported music CD", new BigDecimal("7.99"));
    }

    @Test
    public void billAddOneProduct() {
        bill.addProduct(testProduct_tax10);

        Assert.assertEquals(1,bill.getProducts().size());
        Assert.assertEquals("1 music CD: 16.49", bill.getProducts().get(0).print());
    }

    @Test
    public void billAddTwoProducts() {
        bill.addProduct(testProduct_tax10);
        bill.addProduct(testProduct_tax15);

        Assert.assertEquals(2,bill.getProducts().size());
        Assert.assertEquals("1 music CD: 16.49", bill.getProducts().get(0).print());
        Assert.assertEquals("1 imported music CD: 9.19", bill.getProducts().get(1).print());
    }

    @Test
    public void checkAddProductByStringWorks() {
        Assert.assertEquals("1 music CD: 10.75", bill.addProductByString("1 music CD at 9.75"));
    }

    @Test
    public void correctImportedPositionInDescription() {
        Assert.assertEquals("imported box of chocolates", bill.correctImportedPositionInDescription("box of imported chocolates"));
    }

    @Test
    public void totalTaxesCalculatedCorrectly() {
        bill.addProduct(testProduct_tax10);
        bill.addProduct(testProduct_tax15);

        Assert.assertTrue(new BigDecimal("2.70").compareTo(bill.getTotalTaxes()) == 0);
    }

    @Test
    public void totalCostsCalculatedCorrectly() {
        bill.addProduct(testProduct_tax10);
        bill.addProduct(testProduct_tax15);

        Assert.assertTrue(new BigDecimal("25.68").compareTo(bill.getTotalCosts()) == 0);
    }

    @Test
    public void testInput_1() {
        Assert.assertEquals("1 book: 12.49", bill.addProductByString("1 book at 12.49"));
        Assert.assertEquals("1 music CD: 16.49", bill.addProductByString("1 music CD at 14.99"));
        Assert.assertEquals("1 chocolate bar: 0.85", bill.addProductByString("1 chocolate bar at 0.85"));

        Assert.assertTrue(bill.getTotalTaxes().compareTo(new BigDecimal("1.50")) == 0);
        Assert.assertTrue(bill.getTotalCosts().compareTo(new BigDecimal("29.83")) == 0);
    }

    @Test
    public void testInput_2() {
        Assert.assertEquals("1 imported box of chocolates: 10.50", bill.addProductByString("1 imported box of chocolates at 10.00"));
        Assert.assertEquals("1 imported bottle of perfume: 54.65", bill.addProductByString("1 imported bottle of perfume at 47.50"));

        Assert.assertTrue(bill.getTotalTaxes().compareTo(new BigDecimal("7.65")) == 0);
        Assert.assertTrue(bill.getTotalCosts().compareTo(new BigDecimal("65.15")) == 0);
    }

    @Test
    public void testInput_3() {
        Assert.assertEquals("1 imported bottle of perfume: 32.19", bill.addProductByString("1 imported bottle of perfume at 27.99"));
        Assert.assertEquals("1 bottle of perfume: 20.89", bill.addProductByString("1 bottle of perfume at 18.99"));
        Assert.assertEquals("1 packet of headache pills: 9.75", bill.addProductByString("1 packet of headache pills at 9.75"));
        Assert.assertEquals("1 imported box of chocolates: 11.85", bill.addProductByString("1 box of imported chocolates at 11.25"));

        Assert.assertTrue(bill.getTotalTaxes().compareTo(new BigDecimal("6.70")) == 0);
        Assert.assertTrue(bill.getTotalCosts().compareTo(new BigDecimal("74.68")) == 0);
    }
}
