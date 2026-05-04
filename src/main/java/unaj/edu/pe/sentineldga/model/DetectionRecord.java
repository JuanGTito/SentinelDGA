package unaj.edu.pe.sentineldga.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "detections")
@Data
public class DetectionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(name = "is_malicious", nullable = false)
    private boolean malicious;

    private double score;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}