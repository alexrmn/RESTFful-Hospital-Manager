package ro.alexrmn.hospitalmanagerbackend.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static boolean isLoggedInUserAdmin(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }
}
