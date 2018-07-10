import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ProductLoader {

    private static final String FIELD_DELIMITER = ",";


    public static List<Product> loadCSVProducts( final String path ) throws Exception {
        Stream<String> lines;
        try {
            lines = Files.lines( Paths.get( path ) );
        } catch ( IOException e ) {
            throw new Exception( "Unable to load the list of products", e );
        }

        return lines.map( line -> line.split( FIELD_DELIMITER ) )
                    .filter( data -> data.length == 6 )
                    .map( data -> {
                        return Product.builder()
                                      .id( Integer.parseInt( data[0] ) )
                                      .price( Integer.parseInt( data[1] ) )
                                      .length( Integer.parseInt( data[2] ) )
                                      .width( Integer.parseInt( data[3] ) )
                                      .height( Integer.parseInt( data[4] ) )
                                      .weight( Integer.parseInt( data[5] ) )
                                      .build();

                    } )
                    .filter( product -> product.getVolume() <= Tote.MAX_VOLUME )
                    .collect( Collectors.toList() );
    }
}
