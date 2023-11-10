package com.example.demo

import com.example.demo.authorization.SecuredWithDefaultRights
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static com.example.demo.authorization.AuthorizationRoles.CALL_OTHER_VOTER
import static com.example.demo.authorization.AuthorizationRoles.PROCESS_SOMETHING
import static com.example.demo.security.authorization.Scopes.SCOPE_SCHREIBEN
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.http.ResponseEntity.created
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class HttpController {

  @Autowired
  ApplicationContext context

  @SecuredWithDefaultRights
  @RequestMapping(value = "/test1", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  ResponseEntity<Map> test1(HttpServletResponse servletResponse) {
    return created(URI.create("http://localhost:8080/location")).body([hello: "world"])
  }

  @Secured([SCOPE_SCHREIBEN, CALL_OTHER_VOTER, PROCESS_SOMETHING])
  @RequestMapping(value = "/test2", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  String test2() {
    return "hello world"
  }
}
