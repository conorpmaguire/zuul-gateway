package com.bloomberg.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom implementation of ZuulFilter
 *
 * @author Conor
 */
public class ProxyServerFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ProxyServerFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.debug("[{}] request will be routed to [{}]", request.getMethod(), request.getRequestURL().toString());
        return null;
    }
}
