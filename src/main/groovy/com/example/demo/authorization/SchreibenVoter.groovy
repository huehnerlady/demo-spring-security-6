package com.example.demo.authorization

import org.springframework.stereotype.Component

import static com.example.demo.security.authorization.Scopes.SCOPE_SCHREIBEN

@Component
class SchreibenVoter extends AbstractAuthorityVoter {

  @Override
  List<String> getAuthorities() {
    return [SCOPE_SCHREIBEN]
  }

  @Override
  String[] getSupportedAccessRights() {
    return [SCOPE_SCHREIBEN]
  }
}
