package com.example.demo.authorization;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.demo.authorization.AuthorizationRoles.AUTHORITY;
import static com.example.demo.authorization.AuthorizationRoles.PROCESS_SOMETHING;
import static com.example.demo.authorization.AuthorizationRoles.SERVICE_CALL;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Secured({SERVICE_CALL, PROCESS_SOMETHING, AUTHORITY})
public @interface SecuredWithDefaultRights {

}
