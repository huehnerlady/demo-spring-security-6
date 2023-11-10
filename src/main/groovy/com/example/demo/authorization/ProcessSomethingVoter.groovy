package com.example.demo.authorization

import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import static com.example.demo.authorization.AuthorizationRoles.PROCESS_SOMETHING

@Component
class ProcessSomethingVoter extends AbstractIdVoter {

  @Override
  String[] getSupportedAccessRights() {
    [PROCESS_SOMETHING]
  }

  @Override
  int voteInternal(String id, Authentication authentication, ReflectiveMethodInvocation methodToAuthorize, Collection<ConfigAttribute> configAttributes) {
    //do something
    return ACCESS_GRANTED
  }
}
