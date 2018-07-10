import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tote implements Comparable<Tote> {

    public static final int MAX_VOLUME = 45 * 30 * 35;

    private List<Product> productList;
    private int volume;
    private int weight;
    private int value;


    private Tote( final List<Product> productList,
                  final int volume,
                  final int weight,
                  final int value ) {
        this.productList = productList;
        this.volume = volume;
        this.weight = weight;
        this.value = value;
    }


    public int getVolume() {
        return volume;
    }


    public int getValue() {
        return value;
    }


    public int getWeight() {
        return weight;
    }


    public List<Product> getProductList() {
        return productList;
    }


    public boolean addProduct( final Product product ) {
        if ( product == null ) {
            throw new IllegalArgumentException( "Should be a valid product" );
        }

        int volume = this.volume + product.getVolume();
        if ( volume > MAX_VOLUME ) {
            return false;
        }

        this.productList.add( product );
        this.volume = volume;
        this.weight += product.getWeight();
        this.value += product.getPrice();

        return true;
    }


    public static final Tote copyOf( final Tote tote ) {
        if ( tote == null ) {
            throw new IllegalArgumentException( "Should be a valid tote" );
        }

        return new ToteBuilder( tote ).build();
    }


    @Override
    public int compareTo( Tote tote ) {
        if ( tote == null ) {
            return 1;
        }

        if ( this.getVolume() != tote.getVolume() ) {
            return Integer.compare( tote.getVolume(), this.getVolume() );
        }
        if ( this.getWeight() != tote.getWeight() ) {
            return Integer.compare( tote.getWeight(), this.getWeight() );
        }
        return 0;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Tote (" );
        builder.append( "Products: " )
               .append( Arrays.toString( this.productList.toArray() ) )
               .append( "\n" );
        builder.append( "Value: " ).append( this.value ).append( "\n" );
        builder.append( "Volume: " ).append( this.volume ).append( "\n" );
        builder.append( "Weight: " ).append( this.weight );
        builder.append( " )" );

        return builder.toString();
    }


    public static ToteBuilder builder() {
        return new ToteBuilder();
    }


    public static final class ToteBuilder {

        private List<Product> products = new ArrayList<>();
        private int volume = 0;
        private int weight = 0;
        private int value = 0;


        public ToteBuilder() {

        }


        private ToteBuilder( final Tote tote ) {
            products( new ArrayList<>( tote.getProductList() ) );
            volume( tote.getVolume() );
            weight( tote.getWeight() );
            value( tote.getValue() );
        }


        private ToteBuilder weight( final int weight ) {
            this.weight = weight;
            return this;
        }


        private ToteBuilder value( final int value ) {
            this.value = value;
            return this;
        }


        private ToteBuilder volume( final int volume ) {
            this.volume = volume;
            return this;
        }


        private ToteBuilder products( final List<Product> products ) {
            this.products = products;
            return this;
        }


        public Tote build() {
            return new Tote( products, volume, weight, value );
        }
    }
}
