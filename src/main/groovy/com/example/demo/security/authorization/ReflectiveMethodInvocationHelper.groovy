package com.example.demo.security.authorization

import org.springframework.web.bind.annotation.PathVariable

import java.lang.annotation.Annotation
import java.lang.reflect.Method

class ReflectiveMethodInvocationHelper {

  def findIndexOfPathVariableByName(Method method, def parameterName) {
    findIndexOfParameterAnnotationByName(PathVariable, method, parameterName)
  }

  def findIndexOfParameterAnnotationByName(Class<? extends Annotation> annotationType, Method method, def parameterName) {
    def parameterAnnotations = method?.parameterAnnotations
    def indexOfParameter = -1

    for (int i = 0; i < parameterAnnotations?.length; i++) {
      for (def annotation : parameterAnnotations[i]) {
        if (annotationType.isAssignableFrom(annotation.annotationType())) {
          def castToAnnotationType = annotationType.cast(annotation)
          if (castToAnnotationType.value() == parameterName) {
            indexOfParameter = i
          }
        }
      }
    }

    indexOfParameter
  }
}
