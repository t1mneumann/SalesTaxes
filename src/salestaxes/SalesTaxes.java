package salestaxes;

import java.util.Scanner;

public class SalesTaxes {
    private static Bill bill = new Bill();

    public static void main(String[] args) {
        System.out.println("Zur Anzeige der Rechnung, bitte eine Leerzeile eingeben.");
        System.out.println("\nEinkauf ohne Steuern zeilenweise eingeben:");
        Scanner input = new Scanner(System.in);

        while(input.hasNextLine()) {
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            }
            bill.addProductByString(line);
        }
        bill.print();
    }
}
