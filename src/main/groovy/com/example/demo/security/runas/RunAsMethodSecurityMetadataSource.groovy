package com.example.demo.security.runas

import org.springframework.security.access.ConfigAttribute
import org.springframework.security.access.SecurityConfig
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource

import java.lang.reflect.Method

class RunAsMethodSecurityMetadataSource extends AbstractFallbackMethodSecurityMetadataSource {

  @Override
  protected Collection<ConfigAttribute> findAttributes(Method method, Class<?> targetClass) {
    def annotation = method.getAnnotation(RunAs)
    if (annotation) {
      return [new SecurityConfig(annotation.value())]
    }
    return []
  }

  @Override
  protected Collection<ConfigAttribute> findAttributes(Class<?> clazz) {
    []
  }

  @Override
  Collection<ConfigAttribute> getAllConfigAttributes() {
    null
  }
}
