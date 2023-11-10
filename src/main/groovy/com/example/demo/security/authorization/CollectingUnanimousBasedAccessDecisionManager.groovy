package com.example.demo.security.authorization

import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.vote.AbstractAccessDecisionManager
import org.springframework.security.core.Authentication

class CollectingUnanimousBasedAccessDecisionManager extends AbstractAccessDecisionManager {

  CollectingUnanimousBasedAccessDecisionManager(List<AccessRightDecisionVoter> decisionVoters) {
    super(decisionVoters)
  }

  void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException {
    int deniedDecisions = 0

    List violatedAccessRights = []

    for (AccessDecisionVoter voter : getDecisionVoters()) {
      int result = voter.vote(authentication, object, configAttributes)

      switch (result) {
        case AccessDecisionVoter.ACCESS_DENIED:
          deniedDecisions++
          violatedAccessRights.addAll(((AccessRightDecisionVoter) voter).getSupportedAccessRights())
          break
      }
    }

    if (deniedDecisions > 0) {
      throw new AccessDeniedExceptionWithCause("Access is denied", violatedAccessRights)
    }
  }
}
