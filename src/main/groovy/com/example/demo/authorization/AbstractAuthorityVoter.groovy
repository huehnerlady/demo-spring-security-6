package com.example.demo.authorization

import com.example.demo.security.authorization.AccessRightDecisionVoter
import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
abstract class AbstractAuthorityVoter extends AccessRightDecisionVoter {

  abstract List<String> getAuthorities()

  @Override
  int vote(Authentication authentication, ReflectiveMethodInvocation objectToAuthorize, Collection<ConfigAttribute> configAttributes) {
    if (!supportsAnyConfigAttribute(configAttributes)) {
      return ACCESS_ABSTAIN
    }

    def hasAuthority = authentication.authorities.any {
      String authority = it.authority
      getAuthorities().any { it == authority }
    }
    return hasAuthority ? ACCESS_GRANTED : ACCESS_DENIED
  }
}
