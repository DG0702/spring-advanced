package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.time.LocalDateTime;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 요청 URL
        String url = request.getRequestURI();

        if (url.startsWith("/admin")) {
            
            // HTTP 메서드
            String method = request.getMethod();

            // 클라이언트 IP 주소
            String clientIP = request.getRemoteAddr();

            // 요청 시간 기록
            LocalDateTime requestTime = LocalDateTime.now();

            // 브라우저 정보 확인
            String userAgent = request.getHeader("User-Agent");

            // Authorization 헤더 확인
            String authorization = request.getHeader("Authorization");
            
            // 권한 확인 → JWT 필터에서 이미 검증 완료
            // JWT 필터에서 request 저장한 정보
            Long userId = (Long) request.getAttribute("userId");
            String email = (String) request.getAttribute("email");
            String userRole = (String) request.getAttribute("userRole");

            log.info("URL : {}", url);
            log.info("METHOD : {}", method);
            log.info("IP : {}", clientIP);
            log.info("RequestTime : {}", requestTime);
            log.info("userId : {}" ,userId);
            log.info("email : {}" ,email);
            log.info("userRole : {}" ,userRole);

            if (userAgent != null){
                log.info("userAgent : {}", userAgent);
            }
            if (authorization != null){
                log.info("authorization :{}", authorization);
            }
            
        }
        return true;
    }
}
