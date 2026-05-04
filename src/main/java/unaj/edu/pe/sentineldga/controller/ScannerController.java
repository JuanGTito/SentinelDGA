package unaj.edu.pe.sentineldga.controller;

import unaj.edu.pe.sentineldga.dto.AnalysisResponse;
import unaj.edu.pe.sentineldga.dto.UrlRequest;
import unaj.edu.pe.sentineldga.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/scanner")
@RequiredArgsConstructor
public class ScannerController {

    private final AnalysisService analysisService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponse> analyze(@RequestBody UrlRequest request) {
        try {
            AnalysisResponse response = analysisService.processUrl(request.getUrl());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("DGA-Hunter Engine Online");
    }
}