package com.rodrigohf.apicompras.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.rodrigohf.apicompras.security.JWTAuthenticationFilter;
import com.rodrigohf.apicompras.security.JWTAuthorizationFilter;
import com.rodrigohf.apicompras.security.JWTUtil;




@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity  // configurando a anotação @PreAuthorize para permitir acesso somente para admins
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	
	@Autowired
	private JWTUtil jwtUtil;
	
			// definindo um vetor string pra definir quais padroes estão liberados
		public static final String[] PUBLIC_MATCHERS_GET = {
				
				"/produtos/**",
				"/categorias/**",
				"/estados/**"
		};
		
		public static final String[] PUBLIC_MATCHERS_POST = {
				"/clientes/**",
				"/pedidos/**",
				"/auth/forgot/**"
		};

		@SuppressWarnings("deprecation")
		@Override
		protected void configure(HttpSecurity http) throws Exception  {
			
			http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //permitindo somente acesso ao metodo get no endpoint
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll() //somente clientes podem cadastrar no endpoint
			.anyRequest().authenticated()
			.and().httpBasic()
	    	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	   
	    	http.cors().and().csrf().disable();
	    	
	    	http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
	    	http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil,userDetailsService));
			
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
		
		//cofigurando o mecanismo de autencicaçao do Usuario passando o UserDetailsServiceImpl e a senha encriptografada
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		}
}
