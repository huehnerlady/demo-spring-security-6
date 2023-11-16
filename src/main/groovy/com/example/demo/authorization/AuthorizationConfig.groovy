package com.example.demo.authorization

import com.example.demo.security.authorization.CollectingUnanimousBasedAccessDecisionManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class AuthorizationConfig extends GlobalMethodSecurityConfiguration {

  @Autowired
  ServiceCallVoter serviceCallVoter
  @Autowired
  AuthorityAndScopeVoter authorityAndScopeVoter
  @Autowired
  CallOtherVoterVoter callOtherVoterVoter
  @Autowired
  AuthorityVoter authorityVoter

  @Autowired
  Environment environment

  @Override
  AccessDecisionManager accessDecisionManager() {
    new CollectingUnanimousBasedAccessDecisionManager([
        serviceCallVoter,
        authorityAndScopeVoter,
        callOtherVoterVoter,
        authorityVoter
    ])
  }
}
