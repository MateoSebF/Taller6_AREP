package co.edu.eci.arep.springweb.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.HashSet;
import java.util.Set;

/*
 * Simple interceptor to allow certain routes without authentication
 */
@Slf4j
@Component
public class BasicAuthInterceptor implements HandlerInterceptor {

    private static final Set<String> EXCLUDE_URLS = new HashSet<>();
    
    static {
        EXCLUDE_URLS.add("/auth/login");
        EXCLUDE_URLS.add("/auth/register");
        EXCLUDE_URLS.add("/properties");
    }

    private boolean isExcludedUri(String requestURI) {
        return EXCLUDE_URLS.contains(requestURI);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("RequestURI: " + requestURI);

        // Permitir el acceso sin autenticación a las rutas especificadas
        if (isExcludedUri(requestURI)) {
            return true;
        }

        // Bloquea cualquier otro intento de acceso (como si fuera una autenticación fallida)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;
    }
}
