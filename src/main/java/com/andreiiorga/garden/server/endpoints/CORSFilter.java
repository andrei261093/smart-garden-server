package com.andreiiorga.garden.server.endpoints;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class CORSFilter implements Filter {

    private static final List<String> allowedOrigins = Arrays.asList("http://localhost:4200", "http://andrei261093.go.ro:4200", "http://192.168.0.177:4200" ,"http://ro-as72.ro.lidl.net", "https://ro-as72.ro.lidl.net");

    public static List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            // Access-Control-Allow-Origin
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "*");
            // Access-Control-Max-Age
            response.setHeader("Access-Control-Max-Age", "360000");

            // Access-Control-Allow-Credentials
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // Access-Control-Allow-Methods
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // Access-Control-Allow-Headers
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-CSRF-TOKEN, authorization");

            response.setHeader("Set-Cookie", "JSESSIONID=" + request.getSession().getId() + "; Path=/; HttpOnly");


            chain.doFilter(req, res);

        }


    }

    @Override
    public void destroy() {

    }
}
