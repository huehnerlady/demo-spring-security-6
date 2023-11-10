package com.example.demo.security.authorization

import org.springframework.security.access.AccessDeniedException

class AccessDeniedExceptionWithCause extends AccessDeniedException {

  List<String> violatedAccessRights = []

  AccessDeniedExceptionWithCause(String message, List violatedAccessRights) {
    super(message)
    this.violatedAccessRights = violatedAccessRights
  }
}
