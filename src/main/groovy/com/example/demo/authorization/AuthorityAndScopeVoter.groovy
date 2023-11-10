package com.example.demo.authorization

import org.springframework.stereotype.Component

import static com.example.demo.authorization.AuthorizationRoles.AUTHORITY_AND_SCOPE
import static com.example.demo.security.authorization.Authorities.AUTHORITY_1
import static com.example.demo.security.authorization.Scopes.SCOPE_SCHREIBEN

@Component
class AuthorityAndScopeVoter extends AbstractAuthorityVoter {

  @Override
  String[] getSupportedAccessRights() {
    [AUTHORITY_AND_SCOPE]
  }

  @Override
  List<String> getAuthorities() {
    return [AUTHORITY_1, SCOPE_SCHREIBEN]
  }
}
