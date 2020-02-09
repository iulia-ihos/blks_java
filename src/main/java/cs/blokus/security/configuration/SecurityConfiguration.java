package cs.blokus.security.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cs.blokus.encryption.Encryption;
import cs.blokus.security.jwt.JwtAuthEntryPoint;
import cs.blokus.security.jwt.JwtAuthFilter;
import cs.blokus.security.services.UserDetailsServiceImpl;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

 	@Bean
    public JwtAuthFilter authenticationJwtTokenFilter() {
        return new JwtAuthFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.authorizeRequests()
        	.antMatchers("/user/login").permitAll()
        	.antMatchers("/user/register").permitAll()
        	.antMatchers("/stomp-endpoint/**").permitAll()
        	.anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAfter(authenticationJwtTokenFilter(), 
        		AnonymousAuthenticationFilter.class);
    }

    private PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return Encryption.encryptPassword(charSequence.toString());
            }
            
            /**
             * 
             * @param charSequence - login password
             * @param s - database password
             * @return true - passwords match
             * 			false, otherwise
             */
            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	return s.equals(encode(charSequence));
            }
        };
    }
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
    
    @Bean
    public CorsFilter corsFilter() {
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
       config.addAllowedMethod(HttpMethod.POST);
       config.addAllowedMethod(HttpMethod.GET);
       config.addAllowedMethod(HttpMethod.PUT);
       config.addAllowedMethod(HttpMethod.DELETE);
       config.addAllowedOrigin("http://localhost:4200");
       config.addAllowedHeader("Origin");
       config.addAllowedHeader("Authorization");
       //allowedHeaders("Accept", "Content-Type", "X-Auth-Token");
       source.registerCorsConfiguration("/**", config);
       CorsFilter bean = new CorsFilter(source);
       return bean;
    }

}