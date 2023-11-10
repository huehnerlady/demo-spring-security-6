package com.example.demo.security

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

@ToString(includeNames = true, includePackage = false)
@EqualsAndHashCode
class SystemAuthentication implements Authentication, Serializable {

  String authenticationToken
  String partnerId
  boolean authenticated = false
  def details
  Set<GrantedAuthority> authorities = [] as HashSet
  Map claims = [:]

  @Override
  Object getCredentials() {
    authenticationToken
  }

  @Override
  Object getPrincipal() {
    partnerId
  }

  @Override
  Collection<GrantedAuthority> getAuthorities() {
    authorities
  }

  @Override
  Object getDetails() {
    details
  }

  @Override
  boolean isAuthenticated() {
    authenticated
  }

  //fuer equals in der aktuellen Groovy Version
  boolean getAuthenticated() {
    authenticated
  }

  @Override
  void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    authenticated = isAuthenticated
  }

  @Override
  String getName() {
    "System Authentication"
  }
}
