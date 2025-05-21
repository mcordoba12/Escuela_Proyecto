package org.example.introspringboot.controller.api;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.example.introspringboot.service.UserService;
import org.example.introspringboot.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private
    UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> me (HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized token not found"));
        }


        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractAllClaims(token).getSubject();
        var user = userService.findByEmail(username);
        return ResponseEntity.ok(user);
    }


}
