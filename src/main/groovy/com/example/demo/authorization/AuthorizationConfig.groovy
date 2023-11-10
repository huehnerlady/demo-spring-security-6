package com.example.demo.authorization

import com.example.demo.security.authorization.CollectingUnanimousBasedAccessDecisionManager
import com.example.demo.security.runas.RunAsManagerDelegate
import com.example.demo.security.runas.RunAsMethodSecurityMetadataSource
import com.example.demo.security.runas.RunAsSystemUserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.intercept.RunAsManager
import org.springframework.security.access.method.MethodSecurityMetadataSource
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

import static com.example.demo.authorization.RunAsRoles.SUPER_USER

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

  @Override
  protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
    new RunAsMethodSecurityMetadataSource()
  }

  @Override
  protected RunAsManager runAsManager() {
    def runAsManagerDelegate = new RunAsManagerDelegate()
    runAsManagerDelegate.runAsManagers = [
        someManager(),
        // and others looking similar
    ]
    runAsManagerDelegate
  }

  private RunAsManager someManager() {
    def runAsSystemUserManager = new RunAsSystemUserManager()
    runAsSystemUserManager.key = environment.getProperty("someToken")
    runAsSystemUserManager.supportedRole = SUPER_USER
    runAsSystemUserManager
  }

}
