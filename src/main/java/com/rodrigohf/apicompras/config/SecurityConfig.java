package com.rodrigohf.apicompras.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
			// definindo um vetor string pra definir quais padroes estão liberados
		public static final String[] PUBLIC_MATCHERS_GET = {
				
				"/produtos/**",
				"/categorias/**"
			
		};

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //permitindo somente acesso ao metodo get no endpoint
			.anyRequest().authenticated()
			.and().httpBasic()
	    	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    	http.cors().and().csrf().disable();
			
		}
		
		//para permitir autencicação em múltiplas fontes como aplicativos(http.cors()):
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
			return source;
			
		}
		
		//Criando um encriptador de senhas:
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
			
		}
}
