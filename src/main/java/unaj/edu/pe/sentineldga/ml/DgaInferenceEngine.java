package unaj.edu.pe.sentineldga.ml;

import ai.onnxruntime.*;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.Collections;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DgaInferenceEngine {

    private final OrtEnvironment env;
    private final OrtSession session;

    public float predict(String url) throws OrtException {
        float[] inputs = new float[] {
                (float) url.length(),               // 1. Longitud
                calculateEntropy(url),              // 2. Entropía
                countDigits(url),                   // 3. Cantidad de dígitos
                url.contains("-") ? 1.0f : 0.0f,    // 4. Presencia de guiones
                countVowels(url)                    // 5. Cantidad de vocales
        };

        long[] shape = new long[]{1, 5};

        try (OnnxTensor tensor = OnnxTensor.createTensor(env, java.nio.FloatBuffer.wrap(inputs), shape)) {
            try (OrtSession.Result results = session.run(Collections.singletonMap("float_input", tensor))) {

                long[] labels = (long[]) results.get("output_label").get().getValue();
                return (float) labels[0];
            }
        }
    }

    private float calculateEntropy(String url) {
        return (float) url.chars().distinct().count() / (float) url.length();
    }

    private float countDigits(String url) {
        return (float) url.chars().filter(Character::isDigit).count();
    }

    private float countVowels(String url) {
        String vowels = "aeiouAEIOU";
        return (float) url.chars().filter(ch -> vowels.indexOf(ch) != -1).count();
    }
}