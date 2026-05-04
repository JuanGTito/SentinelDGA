package unaj.edu.pe.sentineldga.service;

import unaj.edu.pe.sentineldga.dto.AnalysisResponse;
import unaj.edu.pe.sentineldga.model.DetectionRecord;
import unaj.edu.pe.sentineldga.ml.DgaInferenceEngine;
import unaj.edu.pe.sentineldga.repository.DetectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final DgaInferenceEngine mlEngine;
    private final DetectionRepository repository;

    public AnalysisResponse processUrl(String url) throws Exception {
        // Ejecutar predicción
        float score = mlEngine.predict(url);
        boolean isMalicious = score > 0.5; // Umbral estándar

        // Persistir en Supabase
        DetectionRecord record = new DetectionRecord();
        record.setUrl(url);
        record.setMalicious(isMalicious);
        record.setScore(score);
        repository.save(record);

        // Retornar DTO para el controlador
        String message = isMalicious ? "Amenaza detectada: Dominio sospechoso" : "Dominio seguro";
        return new AnalysisResponse(url, isMalicious, score, message);
    }
}