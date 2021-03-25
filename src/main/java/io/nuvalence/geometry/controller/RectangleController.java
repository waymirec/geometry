package io.nuvalence.geometry.controller;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.service.RectangleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geometry/rectangle")
@Api(value = "Rectangle Analysis Endpoints", description = "Provides various forms of rectangular analysis.")
public class RectangleController {
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
    @PostMapping(path = "/analyze", consumes = "application/json", produces = "application/json")
    public AnalyzeRectangleResponse analyze(@RequestBody AnalyzeRectangleRequest request)
    {
        return rectangleService.analyze(request);
    }
}