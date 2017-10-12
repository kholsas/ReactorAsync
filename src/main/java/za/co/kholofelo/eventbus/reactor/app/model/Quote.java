package za.co.kholofelo.eventbus.reactor.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Quote {

    Long id;
    String quote;
}
