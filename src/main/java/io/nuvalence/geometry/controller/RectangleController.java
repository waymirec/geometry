package io.nuvalence.geometry.controller;

import io.nuvalence.geometry.dto.AnalyzeRectangleRequest;
import io.nuvalence.geometry.dto.AnalyzeRectangleResponse;
import io.nuvalence.geometry.service.RectangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geometry/rectangle")
public class RectangleController {
    private final RectangleService rectangleService;

    RectangleController(@Autowired final RectangleService rectangleService)
    {
        this.rectangleService = rectangleService;
    }

    @PostMapping(path = "/analyze", consumes = "application/json", produces = "application/json")
    public AnalyzeRectangleResponse analyze(@RequestBody AnalyzeRectangleRequest request)
    {
        return rectangleService.analyze(request);
    }
}
