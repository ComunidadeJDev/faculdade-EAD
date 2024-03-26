package com.jdev.student.security.accessInterface;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('COORDINATOR', 'DIRECTOR', 'ADMIN') and hasAnyAuthority('SCOPE_user:write', 'SCOPE_user:read')")
public @interface CoordinatorDirectorAccess {
}
