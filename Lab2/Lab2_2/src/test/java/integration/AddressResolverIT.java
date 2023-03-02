package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressResolverIT {
    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        this.resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        Optional<Address> result = resolver.findAddressForLocation(40.631803, -8.657881);
        
        assertEquals(result.get(), new Address("Rua S.Antonio - Travasso", "Agueda", "Centro", "3750-755", null));
        assertTrue(result.isPresent());
        assertEquals(result.get().getCity(), "Agueda");
        assertEquals(result.get().getHouseNumber(), null);
        assertEquals(result.get().getStreet(), "Rua S.Antonio - Travasso");
        assertEquals(result.get().getState(), "Centro");
        assertEquals(result.get().getZipCode(), "3750-755");


    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows( IllegalArgumentException.class, () -> { resolver.findAddressForLocation(-300, -810); } );
    }

}
