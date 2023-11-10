package com.example.demo.authorization

import com.example.demo.security.authorization.AccessRightDecisionVoter
import com.example.demo.security.authorization.ReflectiveMethodInvocationHelper
import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication

abstract class AbstractIdVoter extends AccessRightDecisionVoter {

  ReflectiveMethodInvocationHelper reflectiveMethodInvocationHelper = new ReflectiveMethodInvocationHelper()

  abstract int voteInternal(String is, Authentication authentication, ReflectiveMethodInvocation methodToAuthorize, Collection<ConfigAttribute> configAttributes)

  @Override
  final int vote(Authentication authentication, ReflectiveMethodInvocation methodToAuthorize, Collection<ConfigAttribute> configAttributes) {
    if (!supportsAnyConfigAttribute(configAttributes)) {
      return ACCESS_ABSTAIN
    }

    String pathVariable = findPathVariable(methodToAuthorize)
    if (!pathVariable) {
      return ACCESS_ABSTAIN
    }

    return voteInternal(pathVariable, authentication, methodToAuthorize, configAttributes)
  }

  String findPathVariable(ReflectiveMethodInvocation methodToAuthorize) {
    int indexOfPathVariableByName = reflectiveMethodInvocationHelper.findIndexOfPathVariableByName(methodToAuthorize.method, "PATH") as int
    if (indexOfPathVariableByName > -1) {
      String id = methodToAuthorize.arguments[indexOfPathVariableByName]
      return id
    }
    return null
  }
}
