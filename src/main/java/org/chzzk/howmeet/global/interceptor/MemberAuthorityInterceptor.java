package org.chzzk.howmeet.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chzzk.howmeet.domain.common.auth.model.AuthPrincipal;
import org.chzzk.howmeet.domain.regular.auth.annotation.RegularUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class MemberAuthorityInterceptor implements HandlerInterceptor {
    private final String authAttributeKey;

    public MemberAuthorityInterceptor(@Value("${auth.attribute-key}") final String authAttributeKey) {
        this.authAttributeKey = authAttributeKey;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final RegularUser regularUser = handlerMethod.getMethodAnnotation(RegularUser.class);
        if (!Objects.isNull(regularUser)) {
            validateUserAuthorization(request);
        }

        return true;
    }

    private void validateUserAuthorization(final HttpServletRequest request) {
        final AuthPrincipal authPrincipal = (AuthPrincipal) request.getAttribute(authAttributeKey);
        if (!authPrincipal.isMember()) {
            throw new IllegalArgumentException();
        }
    }
}
