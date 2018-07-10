import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Warehouse {

    private List<Product> productList;


    public Warehouse( final List<Product> productList ) {
        if ( productList == null ) {
            throw new IllegalArgumentException( "Should be a valid product list" );
        }
        this.productList = productList;
    }


    private void checkCandidate( final Tote candidate, final HashMap<Integer, Tote> candidates ) {
        if ( !candidates.containsKey( candidate.getValue() ) ) {
            candidates.put( candidate.getValue(), candidate );
        } else {
            Tote old = candidates.get( candidate.getValue() );

            if ( candidate.compareTo( old ) > 0 ) {
                candidates.put( candidate.getValue(), candidate );
            }
        }
    }


    public Tote findMostValuableTote() {
        HashMap<Integer, Tote> candidates = new HashMap<>();
        for ( Product product : productList ) {
            for ( Map.Entry<Integer, Tote> entry : new HashMap<>( candidates ).entrySet() ) {
                Tote candidate = Tote.copyOf( entry.getValue() );
                if ( candidate.addProduct( product ) ) {
                    checkCandidate( candidate, candidates );
                }
            }

            Tote candidate = Tote.builder().build();
            if ( candidate.addProduct( product ) ) {
                checkCandidate( candidate, candidates );
            }
        }
        return candidates.get( Collections.max( candidates.keySet() ) );
    }
}
