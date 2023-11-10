package com.example.demo.authorization

import com.example.demo.security.SystemAuthentication
import com.example.demo.security.authorization.AccessRightDecisionVoter
import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import static com.example.demo.authorization.AuthorizationRoles.AUTHORITY
import static com.example.demo.security.authorization.Authorities.AUTHORITY_2

@Component
class AuthorityVoter extends AccessRightDecisionVoter {

  @Override
  String[] getSupportedAccessRights() {
    [AUTHORITY]
  }

  @Override
  int vote(Authentication authentication, ReflectiveMethodInvocation ignored, Collection<ConfigAttribute> configAttributes) {
    if (!supportsAnyConfigAttribute(configAttributes)) {
      return ACCESS_ABSTAIN
    }
    return authentication.authorities.any { it.getAuthority() == AUTHORITY_2 } && isNotPublicApiToken(authentication) ? ACCESS_GRANTED : ACCESS_DENIED
  }

  boolean isNotPublicApiToken(Authentication authentication) {
    return authentication instanceof SystemAuthentication && ((SystemAuthentication) authentication).claims?.get("api") != "KEX"
  }
}
