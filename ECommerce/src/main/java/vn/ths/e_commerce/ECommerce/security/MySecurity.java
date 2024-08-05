package vn.ths.e_commerce.ECommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.AccountService;
import vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm.AccountServiceIpm;

@Configuration
public class MySecurity {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AccountServiceIpm accountServiceIpm){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(accountServiceIpm);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
                configurer->configurer
                        .requestMatchers("/admin/listKeyboard/**","/admin/listLaptop/**","/admin/listMouse/**","/admin/listScreen/**").hasRole("ADMIN")
                        .requestMatchers("/admin/createKeyboard/**","/admin/createLaptop/**","/admin/createMouse/**","/admin/createScreen/**").hasRole("ADMIN")
                        .requestMatchers("/admin/deleteKeyboard/**","/admin/deleteLaptop/**","/admin/deleteMouse/**","/admin/deleteScreen/**").hasRole("ADMIN")
                        .requestMatchers("/admin/updateKeyboard/**","/admin/updateLaptop/**","/admin/updateMouse/**","/admin/updateScreen/**").hasRole("ADMIN")
                        .requestMatchers("/admin/saveKeyboard/**","/admin/saveLaptop/**","/admin/saveMouse/**","/admin/saveScreen/**").hasRole("ADMIN")
                        .requestMatchers("/admin/saveKeyboardDetail/**","/admin/saveLaptopDetail/**","/admin/saveMouseDetail/**","/admin/saveScreenDetail/**").hasRole("ADMIN")
                        .requestMatchers("/admin/listAccount/**","/admin/deleteAccount/**","/admin/listUser/**","/admin/deleteUser/**").hasRole("ADMIN")
                        .requestMatchers("/admin/saveVoucher/**","/admin/listVoucher/**","/admin/createVoucher/**","/admin/updateVoucher/**","/admin/deleteVoucher/**").hasRole("ADMIN")
                        .requestMatchers("/user/addProductToCart/**","/user/showCart/**","/user/DeleteProductInCart/**").hasRole("USER")
                        .anyRequest().permitAll()
        ).formLogin(
                login->login
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticationTheUser")
                        .permitAll()
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?error")
        ).logout(
                logout->logout.permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(crsrf->crsrf.disable());
        return http.build();
    }
}
