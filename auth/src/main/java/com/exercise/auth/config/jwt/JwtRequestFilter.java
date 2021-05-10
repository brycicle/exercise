package com.exercise.auth.config.jwt;

import com.exercise.auth.service.impl.JwtUserDetailsServiceImpl;
import com.exercise.auth.util.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsServiceImpl jwtUserDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain
    ) throws ServletException, IOException {
        final Optional<String> requestTokenHeaderOptional = Optional.ofNullable(request.getHeader("Authorization"));
        Optional<String> usernameOptional = Optional.empty();
        String jwtToken = "";
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeaderOptional.isPresent()) {
            final int jwtTokenHeaderSubstring = 7;

            jwtToken = requestTokenHeaderOptional.get().substring(jwtTokenHeaderSubstring);

            try {
                usernameOptional = Optional.ofNullable(jwtTokenUtil.getUsernameFromToken(jwtToken));
            } catch (final IllegalArgumentException illegalArgumentException) {
                logger.warn("Unable to get JWT Token");
            } catch (final ExpiredJwtException expiredJwtException) {
                logger.warn("JWT Token has expired");
            }

        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (usernameOptional.isPresent()) {
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(usernameOptional.get());

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        chain.doFilter(request, response);
    }

}
