package za.co.kholofelo.eventbus.reactor.app.resource;

import za.co.kholofelo.eventbus.reactor.app.model.Quote;

/**
 * @author Kholofelo Maloma
 * @since 2017/10/12.
 */
public class QuoteResource {

    String type;
    Quote value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Quote getValue() {
        return value;
    }

    public void setValue(Quote value) {
        this.value = value;
    }
}
