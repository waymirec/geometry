package io.nuvalence.geometry.controller;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.factory.RectangleFactory;
import io.nuvalence.geometry.model.Rectangle;
import io.nuvalence.geometry.service.RectangleService;
import io.nuvalence.geometry.util.Comparators;
import io.nuvalence.geometry.util.RectanglePair;
import io.nuvalence.geometry.util.SortedPair;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("/api/v1/geometry/rectangle")
@Api(value = "Rectangle Analysis Endpoints", description = "Provides various forms of rectangular analysis.")
public class RectangleController {
    private static final Logger LOGGER = LogManager.getLogger(RectangleController.class);

    private static final String CACHE_NAME = "rectangles";

    private final RectangleService rectangleService;

    RectangleController(@Autowired final RectangleService rectangleService)
    {
        this.rectangleService = rectangleService;
    }

    @ApiOperation(value = "Analyze the relationship between two rectangles.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Analysis succeeded. Response will contain details."),
        @ApiResponse(code = 400, message = "The request was malformed in some way. Verify the request body and try again."),
        @ApiResponse(code = 500, message = "An unexpected exception was encountered.")
    })
    @Cacheable(value = CACHE_NAME, key = "T(io.nuvalence.geometry.controller.CacheKeyGenerator).generate(#request)")
    @PostMapping(path = "/analyze", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnalyzeRectangleResponse analyze(@RequestBody AnalyzeRectangleRequest request)
    {
        final Rectangle first = RectangleFactory.create(request.getFirst());
        final Rectangle second = RectangleFactory.create(request.getSecond());
        final SortedPair<Rectangle> rectangles = new RectanglePair(first, second, Comparators.RECTANGLE_HORIZONTAL);
        final AnalyzeRectangleResponse response = rectangleService.analyze(rectangles);
        return response;
    }

    @CacheEvict(allEntries = true, cacheNames = { CACHE_NAME })
    @Scheduled(fixedDelayString = "${rectangles.cache.ttl}")
    public void cacheEvict() {
        LOGGER.info(() -> "clearing cache: " + CACHE_NAME);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Void> handleIllegalArgumentException() {
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
}
