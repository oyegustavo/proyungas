package gov.justucuman.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CurrentUserFilter extends ZuulFilter {

	public static final String USER_HEADER = "X-User-Header";

	@Override
	public boolean shouldFilter() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.getPrincipal() != null;
	}

	@Override
	public Object run() throws ZuulException {
		
	    final RequestContext ctx = RequestContext.getCurrentContext();
	    final HttpServletRequest request = ctx.getRequest();
	 //Here is the authorization header being read.
	    final String xAuth = request.getHeader("Authorization");
	 //Use the below method to add anything to the request header to read downstream. if needed.
	    ctx.addZuulRequestHeader(USER_HEADER, xAuth); 

	    return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
	}

}
