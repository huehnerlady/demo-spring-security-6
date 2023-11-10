package com.example.demo.security.runas

import com.example.demo.security.SystemAuthentication
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.intercept.RunAsManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

class RunAsSystemUserManager implements RunAsManager {

  String key
  String supportedRole

  @Override
  Authentication buildRunAs(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
    if (!key) {
      throw new BadCredentialsException("Key required to run as system user ...")
    }

    if (!attributes.any { supports(it) }) {
      return null
    }

    return new SystemAuthentication(authenticationToken: key, partnerId: authentication.principal as String, authenticated: true, authorities: attributes.collect {
      new SimpleGrantedAuthority(it.attribute)
    })
  }

  @Override
  boolean supports(ConfigAttribute attribute) {
    attribute.attribute == supportedRole
  }

  @Override
  boolean supports(Class<?> clazz) {
    true
  }
}
