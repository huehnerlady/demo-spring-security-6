package com.example.demo.authorization

import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import static com.example.demo.authorization.AuthorizationRoles.SERVICE_CALL

@Component
class ServiceCallVoter extends AbstractIdVoter {

  @Override
  String[] getSupportedAccessRights() {
    [SERVICE_CALL]
  }

  @Override
  int voteInternal(String id, Authentication authentication, ReflectiveMethodInvocation methodToAuthorize, Collection<ConfigAttribute> configAttributes) {
    if (true) { // some service call
      return ACCESS_GRANTED
    }
    return ACCESS_DENIED
  }
}
