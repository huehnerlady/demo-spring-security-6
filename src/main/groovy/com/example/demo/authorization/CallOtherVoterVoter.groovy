package com.example.demo.authorization

import com.example.demo.security.authorization.AccessRightDecisionVoter
import org.springframework.aop.framework.ReflectiveMethodInvocation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import static com.example.demo.authorization.AuthorizationRoles.PROCESS_SOMETHING
import static com.example.demo.authorization.AuthorizationRoles.CALL_OTHER_VOTER

@Component
class CallOtherVoterVoter extends AccessRightDecisionVoter {

  @Autowired
  ProcessSomethingVoter processSomethingVoter

  @Override
  String[] getSupportedAccessRights() {
    return processSomethingVoter.getSupportedAccessRights()
  }

  @Override
  boolean supportsAnyConfigAttribute(Collection<ConfigAttribute> configAttributes) {
    configAttributes.any {
      CALL_OTHER_VOTER == it.attribute
    }
  }

  @Override
  int vote(Authentication authentication, ReflectiveMethodInvocation objectToAuthorize, Collection<ConfigAttribute> configAttributes) {
    if (!supportsAnyConfigAttribute(configAttributes)) {
      return ACCESS_ABSTAIN
    }

    // do some more

    int access = processSomethingVoter.vote(authentication, objectToAuthorize, [new SimpleConfigAttribute(PROCESS_SOMETHING)])
    if (access != ACCESS_DENIED) {
      // do some more
    }
    return access
  }

  private class SimpleConfigAttribute implements ConfigAttribute {

    String attribute

    SimpleConfigAttribute(String attribute) {
      this.attribute = attribute
    }

    @Override
    String getAttribute() {
      attribute
    }
  }
}
