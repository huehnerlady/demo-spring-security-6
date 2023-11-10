package com.example.demo.security.authorization

import org.aopalliance.intercept.MethodInvocation
import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.ConfigAttribute

abstract class AccessRightDecisionVoter implements AccessDecisionVoter<ReflectiveMethodInvocation> {

  @Override
  boolean supports(Class<?> aClass) {
    aClass == MethodInvocation
  }

  @Override
  boolean supports(ConfigAttribute configAttribute) {
    supportsAnyConfigAttribute([configAttribute])
  }

  boolean supportsAnyConfigAttribute(Collection<ConfigAttribute> configAttributes) {
    configAttributes.any {
      getSupportedAccessRights().contains(it.attribute)
    }
  }

  abstract String[] getSupportedAccessRights()
}
