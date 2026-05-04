package unaj.edu.pe.sentineldga.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalysisResponse {
    private String url;
    private boolean isMalicious;
    private double score;
    private String message;
}