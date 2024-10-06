package springbootjwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootjwt.config.JwtUtil;
import springbootjwt.model.AuthRequest;
import springbootjwt.model.AuthResponse;
import springbootjwt.model.ErrorResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            logger.info("Attempting authentication for user: {}", request.getUsername());
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(request.getUsername());
            
            logger.info("Authentication successful for user: {}", request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
            
        } catch (BadCredentialsException e) {
            logger.error("Authentication failed: Bad credentials", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid username or password"));
        } catch (Exception e) {
            logger.error("Authentication failed: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Authentication failed: " + e.getMessage()));
        }
    }
}
