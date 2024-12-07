package com.reto.tecnico.bcastro.application.dto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CCC {

        public static void main(String[] args) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = "BRUNO123";
            String encodedPassword = encoder.encode(password);
            System.out.println("Hash para 'admin123': " + encodedPassword);

            // Verificar que el hash funciona
            boolean matches = encoder.matches(password, encodedPassword);
            System.out.println("¿La contraseña coincide? " + matches);

            // Verificar contra el hash actual
            String currentHash = "$2a$19$gQFDC7IkYSRusqHDS/GJLeeY/uhgr8FsvDt47husQo72tnZIbA0MW";
            boolean matchesCurrentHash = encoder.matches(password, currentHash);
            System.out.println("¿La contraseña coincide con el hash actual? " + matchesCurrentHash);
        }
    }

