package br.com.campmanager.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // Aplica a configuração a todos os endpoints (/api/)
                registry.addMapping("/*") 

                        // Permite requisições de origem específica (substitua pela URL do seu frontend em produção!)
                        // Para desenvolvimento local, você pode usar "" ou a porta exata do live server (ex: http://127.0.0.1:5500/)
                        .allowedOrigins("http://localhost:5500/", "http://127.0.0.1:5500") 

                        // Métodos HTTP permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                        // Permite credenciais (cookies, headers de autenticação como JWT)
                        .allowCredentials(true)

                        // Headers permitidos na requisição (inclui Content-Type, Authorization, etc.)
                        .allowedHeaders("*")

                        // Tempo que o navegador pode armazenar a resposta pré-voo (pre-flight)
                        .maxAge(3600); 
            }
        };
    }
}
