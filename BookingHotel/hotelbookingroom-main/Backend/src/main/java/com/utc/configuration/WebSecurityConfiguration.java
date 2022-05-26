package com.utc.configuration;

import com.utc.configuration.jwtconfig.AuthEntryPointJwt;
import com.utc.configuration.jwtconfig.AuthTokenFilter;
import com.utc.service.IGuestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IGuestsService guestsService;

    @Autowired
    private AuthEntryPointJwt authEntryPoint;

    public static final String[] APIPublic = {
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**",
                "swagger-ui/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(guestsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthTokenFilter createAuthTokenFiler() {
        return new AuthTokenFilter();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**","/configuration/**", "/swagger-ui.html", "/webjars/**","swagger-ui/**");
//
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests().antMatchers(APIPublic).permitAll();

        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/UTCAuth/**").permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        http.cors()
                        .and().authorizeHttpRequests()
                        .antMatchers(APIPublic).permitAll();
//        http.cors().and()
//                .authorizeHttpRequests().antMatchers(HttpMethod.GET,"/UTCDemo/address/**").permitAll()
//                .antMatchers(HttpMethod.PUT,"/UTCDemo/address/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.POST,"/UTCDemo/address/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/UTCDemo/address/**").hasAnyAuthority("GUESTS","ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();

//        http.cors().and()
//                .authorizeHttpRequests().antMatchers(HttpMethod.GET,"/UTCDemo/guests/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"/UTCDemo/guests/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.POST,"/UTCDemo/guests/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.DELETE,"/UTCDemo/guests/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();
//
//        http.cors().and()
//                .authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/hotel/**").hasAnyAuthority("ADMIN","GUESTS")
//                .antMatchers(HttpMethod.POST,"UTCDemo/hotel/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/hotel/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/image/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.POST,"UTCDemo/image/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/image/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/image/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/hotelServices/**").hasAnyAuthority("ADMIN","GUESTS")
//                .antMatchers(HttpMethod.POST,"UTCDemo/hotelServices/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/hotelServices/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/hotelServices/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/roomBook/**").hasAnyRole("ADMIN","GUESTS")
//                .antMatchers(HttpMethod.POST,"UTCDemo/roomBook/**").hasAnyRole("ADMIN","GUESTS")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/roomBook/**").hasAnyRole("ADMIN","GUESTS")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/roomBook/**").hasAnyRole("ADMIN","GUESTS")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/room/**").hasAuthority("GUESTS")
//                .antMatchers(HttpMethod.POST,"UTCDemo/room/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/room/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/room/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/roomType/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.POST,"UTCDemo/roomType/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/roomType/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/roomType/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/starRate/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.POST,"UTCDemo/starRate/**").hasAuthority("GUESTS")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/starRate/**").hasAuthority("GUESTS")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/starRate/**").hasAuthority("GUESTS")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"UTCDemo/userServices/**").hasAnyAuthority("GUESTS","ADMIN")
//                .antMatchers(HttpMethod.POST,"UTCDemo/userServices/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.PUT,"UTCDemo/userServices/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE,"UTCDemo/userServices/**").hasAuthority("ADMIN")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().and().csrf().disable();
//
//        http.cors().and().authorizeHttpRequests().antMatchers(HttpMethod.GET,"/UTCDemo/booking/**").hasAnyAuthority("ADMIN","GUESTS")
//                        .antMatchers(HttpMethod.POST,"/UTCDemo/booking/**").hasAuthority("GUESTS")
//                        .antMatchers(HttpMethod.PUT,"/UTCDemo/booking/**").hasAuthority("GUESTS")
//                        .antMatchers(HttpMethod.DELETE,"/UTCDemo/booking/**").hasAuthority("GUESTS")
//                        .and()
//                                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
//                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .and().httpBasic().and().csrf().disable();

        http.addFilterBefore(createAuthTokenFiler(), UsernamePasswordAuthenticationFilter.class);

    }
}
