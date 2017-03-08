package io.unicorn;

import com.google.common.collect.Maps;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Abderrazak BOUADMA
 * on 07/03/2017.
 */
public class SpyFilter extends OncePerRequestFilter implements Ordered {


    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 10;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Trace t = initTrace();

        BufferedResponseWrapper responseWrapper = new BufferedResponseWrapper(response);
        BufferedRequestWrapper requestWrapper = new BufferedRequestWrapper(request);

        enhanceTraceWithRequest(t, requestWrapper);

        filterChain.doFilter(requestWrapper, responseWrapper);

        enhanceTraceWithResponse(t, responseWrapper);

        InMemoryEndPoint.trace(t);
    }

    private Trace initTrace() {
        return Trace.builder()
                .requestHeaders(Maps.newHashMap())
                .responseHeaders(Maps.newHashMap())
                .build();
    }

    private void enhanceTraceWithResponse(Trace t, BufferedResponseWrapper response) {
        response.getHeaderNames()
                .forEach(h -> t.getResponseHeaders().put(h, response.getHeader(h)));
        t.setResponseBody(response.getContent());
    }

    private void enhanceTraceWithRequest(Trace t, BufferedRequestWrapper request) {
        Collections.list(request.getHeaderNames())
                .forEach(h -> t.getRequestHeaders().put(h, request.getHeader(h)));
        try {
            t.setRequestBody(request.getRequestBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
