package br.com.felipezanella.springbootrolesapi.config.security;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import br.com.felipezanella.springbootrolesapi.config.security.repository.UserRepository;
import br.com.felipezanella.springbootrolesapi.config.security.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {

    private final String BEARER = "Bearer ";

    private TokenService tokenService;

    private UserRepository userRepository;

    public TokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperaToken(httpServletRequest);

        boolean valido = tokenService.isValidToken(token);

        if (valido) {
            autenticarUsuario(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarUsuario(String token) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

        Long id = tokenService.getIdUser(token);

        User user = userRepository.findById(id).get();

        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String recuperaToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith(BEARER)) {
            return null;
        }
        return token.substring(BEARER.length());
    }
}
