package SWRC.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // "Bearer " 이후 부분만 잘라내기

            // ✅ validateToken으로 이메일(subject) 검증
            String email = jwtUtil.validateToken(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // ✅ 추가: 토큰에서 userId도 추출
                Long userId = jwtUtil.extractUserId(token);

                // ✅ userId, email을 담은 UserDetailsImpl 생성
                UserDetailsImpl userDetails = new UserDetailsImpl(
                        SWRC.entity.User.builder()
                                .id(userId)
                                .email(email)
                                .build()
                );

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (email == null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Invalid JWT Token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
