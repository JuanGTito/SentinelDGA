package unaj.edu.pe.sentineldga.config;

import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class OnnxConfig {

    @Bean
    public OrtEnvironment ortEnvironment() {
        return OrtEnvironment.getEnvironment();
    }

    @Bean
    public OrtSession ortSession(OrtEnvironment env) throws Exception {
        // El archivo .onnx debe estar en src/main/resources/model/dga_hunter.onnx
        byte[] modelBytes = new ClassPathResource("model/dga_hunter.onnx").getInputStream().readAllBytes();
        return env.createSession(modelBytes, new OrtSession.SessionOptions());
    }
}