package salestaxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private List<Product> products;
    private BigDecimal totalTaxes;
    private BigDecimal totalCosts;

    public Bill() {
        this.products = new ArrayList<>();
        this.totalTaxes = new BigDecimal(0);
        this.totalCosts = new BigDecimal(0);
    }

    public void addProduct(Product newProduct) {
        products.add(newProduct);

        totalTaxes = totalTaxes.add(newProduct.calculateTaxesOnly());
        totalCosts = totalCosts.add(newProduct.calculateTotalPrice());
    }

    public String addProductByString(String line) {
        int idx_last = line.lastIndexOf(' ');
        int idx_first = line.indexOf(' ');

        //Parse line to get the details of a product
        int product_quantity = Integer.valueOf(line.substring(0, idx_first));
        String product_details = (line.substring(idx_first+1, idx_last - 3)); // idx-3 cuts the 'at' in the string
        BigDecimal product_price = new BigDecimal(line.substring(idx_last + 1));

        if(product_details.contains("imported"))
            product_details = correctImportedPositionInDescription(product_details);

        Product newProduct =  new Product(product_quantity, product_details, product_price);
        addProduct(newProduct);

        return newProduct.print();
    }

    public String correctImportedPositionInDescription(String description) {
        return "imported " + description.replace("imported ", "");
    }

    public void print() {
        for(Product product : products) {
            System.out.println(product.print());
        }
        System.out.println("Sales Taxes: " + totalTaxes);
        System.out.println("Total: " + totalCosts);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public BigDecimal getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(BigDecimal totalCosts) {
        this.totalCosts = totalCosts;
    }
}
