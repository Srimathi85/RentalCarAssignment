package api.rentalcar.stubmappings;

import api.rentalcar.utility.TestBase;
import org.testng.annotations.BeforeTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class StubMapper extends TestBase {
    @BeforeTest
    public void getStubMapping(){
        wireMockServer.stubFor(get(urlEqualTo("/getData"))
                .withHeader("Content-Type",equalTo("application/json; charset=UTF-8"))
                .willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type","application/json; charset=UTF-8")
                        .withBodyFile("RentalCarSchema.json")
                ));
    }

}
