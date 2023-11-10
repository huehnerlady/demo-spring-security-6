package com.example.demo.security.runas

import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.intercept.RunAsManager
import org.springframework.security.core.Authentication

class RunAsManagerDelegate implements RunAsManager {

  List<RunAsManager> runAsManagers = []

  @Override
  Authentication buildRunAs(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
    def runAsAuthentication = null
    for (RunAsManager runAsManager : runAsManagers) {
      runAsAuthentication = runAsManager.buildRunAs(authentication, object, attributes)
      if (runAsAuthentication) {
        return runAsAuthentication
      }
    }
    runAsAuthentication
  }

  @Override
  boolean supports(ConfigAttribute attribute) {
    true
  }

  @Override
  boolean supports(Class<?> clazz) {
    true
  }
}
