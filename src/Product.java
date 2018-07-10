public class Product {

    private final int id;
    private final int price;
    private final int length;
    private final int width;
    private final int height;
    private final int weight;
    private final int volume;


    private Product( final int id,
                     final int price,
                     final int length,
                     final int width,
                     final int height,
                     final int weight ) {
        this.id = id;
        this.price = price;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;

        this.volume = this.length * this.width * this.height;
    }


    public int getId() {
        return id;
    }


    public int getPrice() {
        return price;
    }


    public int getLength() {
        return length;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    public int getWeight() {
        return weight;
    }


    public int getVolume() {
        return volume;
    }


    public static ProductBuilder builder() {
        return new ProductBuilder();
    }


    @Override
    public String toString() {
        return "Product (" + this.getId() + ")";
    }


    public static final class ProductBuilder {

        private int id;
        private int price = 0;
        private int length = 0;
        private int width = 0;
        private int height = 0;
        private int weight = 0;


        private ProductBuilder() {

        }


        public Product build() {
            return new Product( id, price, length, width, height, weight );
        }


        public ProductBuilder id( final int id ) {
            this.id = id;
            return this;
        }


        public ProductBuilder price( final int price ) {
            this.price = price;
            return this;
        }


        public ProductBuilder length( final int length ) {
            this.length = length;
            return this;
        }


        public ProductBuilder width( final int width ) {
            this.width = width;
            return this;
        }


        public ProductBuilder height( final int height ) {
            this.height = height;
            return this;
        }


        public ProductBuilder weight( final int weight ) {
            this.weight = weight;
            return this;
        }
    }

}
