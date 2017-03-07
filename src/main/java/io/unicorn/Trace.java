package io.unicorn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.util.Map;

/**
 * Created by Abderrazak BOUADMA
 * on 07/03/2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonComponent
public class Trace {

    private long timestamp;
    private Map<String, String> requestHeaders;
    private Map<String, String> responseHeaders;
    private String requestBody;
    private String responseBody;
}
