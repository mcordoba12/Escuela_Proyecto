package org.example.introspringboot.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.example.introspringboot.service.UserService;
import org.example.introspringboot.security.CustomUserDetail;
import org.example.introspringboot.service.impl.CustomUserDetailsService;
import org.example.introspringboot.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.CloseStatus;

import java.util.Map;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {


    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService userDetailsService;


    public JwtHandshakeInterceptor() {

    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            String token = httpRequest.getHeader("Authorization");  // Obtener el token del encabezado Authorization

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);  // Eliminar el prefijo "Bearer " del token

                try {
                    String username = jwtService.extractAllClaims(token).getSubject();
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtService.isValidToken(token, userDetails)) {
                        return true;  // Permitir la conexión WebSocket
                    } else {
                        System.out.println("Token inválido o expirado");
                    }
                } catch (JwtException e) {
                    System.out.println("Token inválido: " + e.getMessage());
                }
            }
        }
        return false;  // Rechazar la conexión si el token no es válido o está ausente
    }


    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception)
    {

    }


}
