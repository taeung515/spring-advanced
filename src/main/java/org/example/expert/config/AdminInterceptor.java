package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = (String) request.getAttribute("userRole");

        if (!role.equals("ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("인증되지 않은 사용자의 접근을 차단합니다.");
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(dtf);

        log.info("요청 시각 = {}, 요청 URL = {}", formattedDateTime, request.getRequestURI());
        return true;
    }

}
