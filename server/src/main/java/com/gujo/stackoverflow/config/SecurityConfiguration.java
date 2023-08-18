package com.gujo.stackoverflow.config;

import com.gujo.stackoverflow.auth.filter.JwtAuthenticationFilter;
import com.gujo.stackoverflow.auth.filter.JwtVerificationFilter;
import com.gujo.stackoverflow.auth.handler.MemberAuthenticationFailureHandler;
import com.gujo.stackoverflow.auth.handler.MemberAuthenticationSuccessHandler;
import com.gujo.stackoverflow.auth.handler.OAuth2MemberSuccessHandler;
import com.gujo.stackoverflow.auth.jwt.JwtTokenizer;
import com.gujo.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.gujo.stackoverflow.member.repository.MemberRepository;
import com.gujo.stackoverflow.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils, MemberRepository memberRepository, MemberService memberService) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()   // 안 하면 403 에러 뜸 -> 접속 불가능
                .cors(withDefaults())
//                .cors(configuration -> configuration // cors error 설정 추가 // 이 설정 때문에 jwt 헤더 제대로 못받는 듯 싶은데.. 일단 해결
//                        .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성하지 않도록 설정
                .and()
                .formLogin().disable()  // 우린 CSR 방식이라 비활성화
                .httpBasic().disable()
                .apply(new CustomFilterConfigurer())    // 추가~
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        // 수정 해야 할 수도~~~
                        // 질문 등록의 경우, 회원만 작성 가능
                        .antMatchers(HttpMethod.POST, "/questions").hasRole("USER")
                        // 질문 수정의 경우, 회원만 수정 가능
                        .antMatchers(HttpMethod.PATCH, "/questions/**").hasRole("USER")
                        // 답변 수정의 경우, 회원만 수정 가능             아래 url 대충 넣어뒀어요..! 고쳐야 댐!
                        .antMatchers(HttpMethod.PATCH,"/questions/**/answers/**").hasRole("USER")
                        // 회원 정보 조회의 경우, 회원만 조회 가능  ( + 회원 정보 목록 관리자만 볼 수 있는 거 해야 할까요? 저희 관리자 페이지도 하나요)
                        .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
                        // 나머지는 비회원도 가능
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2MemberSuccessHandler(jwtTokenizer, authorityUtils, memberService, memberRepository))
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {     // 구체적인 CORS 정책 설정~
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://localhost:8080",
                "3.39.55.166:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS", "PUT"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
                                                                    // ^ Manager 객체 얻을 수 있음

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");

            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler(memberRepository));
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)   // filter chain 추가
                    .addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }

}
