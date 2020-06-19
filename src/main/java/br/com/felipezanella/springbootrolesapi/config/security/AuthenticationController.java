package br.com.felipezanella.springbootrolesapi.config.security;

import br.com.felipezanella.springbootrolesapi.config.security.models.User;
import br.com.felipezanella.springbootrolesapi.config.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

            user = (User) authentication.getPrincipal();

            Map<String, Object> map = new HashMap<>();
            map.put("token", tokenService.generateToken(user));

            return ResponseEntity.ok(map);
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
