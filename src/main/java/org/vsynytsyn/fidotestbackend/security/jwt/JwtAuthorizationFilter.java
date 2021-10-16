package org.vsynytsyn.fidotestbackend.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.vsynytsyn.fidotestbackend.security.service.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.vsynytsyn.fidotestbackend.security.jwt.JwtSecurityConstants.JWT_TOKEN_PREFIX;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService userDetailsService, TokenService tokenService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenService = tokenService;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader(JwtSecurityConstants.HEADER_NAME);
        if (header == null || !header.startsWith(JWT_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            UsernamePasswordAuthenticationToken token = authenticationToken(header);
            SecurityContextHolder.getContext().setAuthentication(token);
            chain.doFilter(request, response);
        } catch (UsernameNotFoundException e){
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
        }
    }


    private UsernamePasswordAuthenticationToken authenticationToken(String header) {
        String token = header.replace(JWT_TOKEN_PREFIX, "");
        String email = tokenService.getTokenSubject(token);
        if (email != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return new UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.getPassword(),
                    userDetails.getAuthorities());
        }
        return null;
    }

}
