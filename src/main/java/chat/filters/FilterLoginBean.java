package chat.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by maciej.debowski on 2015-06-21.
 */
@Component
public class FilterLoginBean implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String uri = request.getRequestURI();

        if (uri != null && (uri.contains("/chat/login") || uri.contains("css") || uri.contains("js") || uri.contains("font") || uri.contains("img"))) {
            chain.doFilter(req, res);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie ck : cookies) {
                    if (ck != null && "LOGIN".equals(ck.getName())) {
                        chain.doFilter(req, res);
                        return;
                    }
                }
            }
            ((HttpServletResponse) res).sendRedirect("/chat/login");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
