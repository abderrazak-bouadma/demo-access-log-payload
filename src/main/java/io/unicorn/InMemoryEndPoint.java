package io.unicorn;

import com.google.common.collect.Lists;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.List;

/**
 * Created by Abderrazak BOUADMA
 * on 07/03/2017.
 */
public class InMemoryEndPoint extends AbstractEndpoint {

    private static final List<Trace> buffer = Lists.newArrayList();

    public InMemoryEndPoint() {
        super("spy");
    }

    @Override
    public Object invoke() {
        return Lists.reverse(buffer);
    }

    public static void trace(Trace t) {
        buffer.add(t);
    }
}
