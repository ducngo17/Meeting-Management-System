package project.meeting_service.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class ContainerIdLoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ContainerIdLoggingFilter.class);
    private final String hostname;

    public ContainerIdLoggingFilter() throws Exception {
        this.hostname = InetAddress.getLocalHost().getHostName();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("Handled by container: " + hostname);
        chain.doFilter(request, response);
    }
}