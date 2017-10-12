package za.co.kholofelo.eventbus.reactor.app.resource;

import lombok.Data;
import za.co.kholofelo.eventbus.reactor.app.model.Quote;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */

@Data
public class QuoteResource {

    String type;
    Quote value;

}