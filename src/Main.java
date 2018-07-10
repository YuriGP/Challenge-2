import java.util.List;


public class Main {

    public static void main( String[] args ) throws Exception {
        List<Product> products = ProductLoader.loadCSVProducts( "data/products.csv" );
        Warehouse warehouse = new Warehouse( products );
        Tote tote = warehouse.findMostValuableTote();

        int sum = tote.getProductList()
                      .stream()
                      .map( product -> product.getId() )
                      .reduce( ( a, b ) -> a + b )
                      .get();

        System.out.println( sum + "@redmart.com" );
    }
}
