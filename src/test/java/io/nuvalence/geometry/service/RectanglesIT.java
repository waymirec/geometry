package io.nuvalence.geometry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nuvalence.geometry.GeometryApplication;
import io.nuvalence.geometry.dto.ClientAnalyzeRectangleResponse;
import io.nuvalence.geometry.service.util.AnalyzeRectangleRequestBuilder;
import io.nuvalence.geometry.service.util.RectangleBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GeometryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RectanglesIT {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Class<ClientAnalyzeRectangleResponse> RET_TYPE = ClientAnalyzeRectangleResponse.class;
    private static final String BASE_PATH = "/api/v1/geometry/rectangle";
    private static final String ANALYZE_EP = "/analyze";
    private static final String CONTAINED_STATE = "contained";
    private static final String SEPARATED_STATE = "separated";
    private static final String EMPTY_STRING = "";

    @Autowired
    TestRestTemplate restTemplate;

    private String request;
    private ResponseEntity<ClientAnalyzeRectangleResponse> response;

    @Test
    public void whenRectanglesAreSeparated_shouldReturnSeparated()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(1.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(2.0f)
                        .withUpperRightY(2.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(3.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(2.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.OK);
        thenStateIs(SEPARATED_STATE);
        thenSubStateIsNull();
        thenIntersectionsIsEmpty();
    }

    @Test
    public void whenRectanglesAreContained_shouldReturnContained()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(1.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(6.0f)
                        .withUpperRightY(6.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.OK);
        thenStateIs(CONTAINED_STATE);
        thenSubStateIsNull();
        thenIntersectionsIsEmpty();
    }

    @Test
    public void whenDimensionsAreNegative_ShouldSucceed()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(-6.0f)
                        .withLowerLeftY(-6.0f)
                        .withUpperRightX(-1.0f)
                        .withUpperRightY(-1.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(-4.0f)
                        .withLowerLeftY(-4.0f)
                        .withUpperRightX(-2.0f)
                        .withUpperRightY(-2.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.OK);
        thenStateIs(CONTAINED_STATE);
        thenSubStateIsNull();
        thenIntersectionsIsEmpty();
    }

    @Test
    public void whenRequestIsEmpty_ShouldReturn400()
    {
        givenRequest(EMPTY_STRING);

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenFirstRectangleMissing_ShouldReturn400()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(3.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(2.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenSecondRectangleMissing_ShouldReturn400()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(3.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(2.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRequestRootContainsExtraData_ShouldIgnoreAndReturn200()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(1.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(6.0f)
                        .withUpperRightY(6.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .with("foo", "bar")
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.OK);
        thenStateIs(CONTAINED_STATE);
        thenSubStateIsNull();
        thenIntersectionsIsEmpty();
    }

    @Test
    public void whenRectangleIsMissingValue_ShouldReturn400()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(6.0f)
                        .withUpperRightY(6.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRectangleHasMalformedValue_ShouldReturn400()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX("foo")
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(6.0f)
                        .withUpperRightY(6.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenRectangleHasExtraData_ShouldIgnoreAndReturn200()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(1.0f)
                        .withLowerLeftY(1.0f)
                        .withUpperRightX(6.0f)
                        .withUpperRightY(6.0f)
                        .with("foo", "bar")
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.OK);
        thenStateIs(CONTAINED_STATE);
        thenSubStateIsNull();
        thenIntersectionsIsEmpty();
    }

    @Test
    public void whenRectangleDimensionsAreInvalid_ShouldReturn400()
    {
        givenRequest(new AnalyzeRectangleRequestBuilder()
                .withFirst(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(1.0f)
                        .withUpperRightY(1.0f)
                        .build())
                .withSecond(new RectangleBuilder()
                        .withLowerLeftX(2.0f)
                        .withLowerLeftY(2.0f)
                        .withUpperRightX(4.0f)
                        .withUpperRightY(4.0f)
                        .build())
                .build());

        whenPostTo(ANALYZE_EP);

        thenStatusCodeIs(HttpStatus.BAD_REQUEST);
    }
    private void givenRequest(String request)
    {
        this.request = request;
    }

    private void whenPostTo(String path)
    {
        response = post(path, request);
    }

    private void thenStatusCodeIs(HttpStatus expected)
    {
        assertEquals(expected, response.getStatusCode());
    }

    private void thenStateIs(String expected)
    {
        assertEquals(expected.toLowerCase(), response.getBody().state.toLowerCase());
    }

    private void thenSubStateIs(String expected)
    {
        assertEquals(expected.toLowerCase(), response.getBody().subState.toLowerCase());
    }

    private void thenSubStateIsNull()
    {
        assertNull(response.getBody().subState);
    }

    private void thenIntersectionsIsEmpty()
    {
        assertTrue(response.getBody().intersections.isEmpty());
    }

    private ResponseEntity<ClientAnalyzeRectangleResponse> post(final String path, final String requestPayload)
    {
        return restTemplate.exchange(BASE_PATH + path, HttpMethod.POST, new HttpEntity<>(requestPayload, defaultHeaders()), RET_TYPE);
    }

    private HttpHeaders defaultHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        return headers;
    }
}
