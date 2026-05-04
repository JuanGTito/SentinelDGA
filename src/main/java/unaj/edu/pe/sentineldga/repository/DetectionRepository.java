package unaj.edu.pe.sentineldga.repository;


import unaj.edu.pe.sentineldga.model.DetectionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectionRepository extends JpaRepository<DetectionRecord, Long> {
    // Aquí puedes agregar métodos personalizados como findByUrl
}