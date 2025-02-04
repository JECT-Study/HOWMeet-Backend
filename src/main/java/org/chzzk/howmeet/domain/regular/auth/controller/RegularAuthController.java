package org.chzzk.howmeet.domain.regular.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.chzzk.howmeet.domain.common.auth.annotation.Authenticated;
import org.chzzk.howmeet.domain.common.auth.model.AuthPrincipal;
import org.chzzk.howmeet.domain.regular.auth.annotation.RegularUser;
import org.chzzk.howmeet.domain.regular.auth.dto.authorize.request.MemberAuthorizeRequest;
import org.chzzk.howmeet.domain.regular.auth.dto.authorize.response.MemberAuthorizeResponse;
import org.chzzk.howmeet.domain.regular.auth.dto.login.request.MemberLoginRequest;
import org.chzzk.howmeet.domain.regular.auth.dto.login.MemberLoginResult;
import org.chzzk.howmeet.domain.regular.auth.dto.login.response.MemberLoginResponse;
import org.chzzk.howmeet.domain.regular.auth.dto.reissue.MemberReissueResult;
import org.chzzk.howmeet.domain.regular.auth.dto.reissue.response.MemberReissueResponse;
import org.chzzk.howmeet.domain.regular.auth.exception.RefreshTokenErrorCode;
import org.chzzk.howmeet.domain.regular.auth.exception.RefreshTokenException;
import org.chzzk.howmeet.domain.regular.auth.service.RegularAuthService;
import org.h2.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.chzzk.howmeet.domain.regular.auth.controller.RefreshTokenCookieProvider.REFRESH_TOKEN;

@RequestMapping("/oauth")
@RequiredArgsConstructor
@RestController
public class RegularAuthController {
    private final RegularAuthService regularAuthService;
    private final RefreshTokenCookieProvider refreshTokenCookieProvider;

    @GetMapping("/authorize")
    public ResponseEntity<?> authorize(@ModelAttribute @Valid final MemberAuthorizeRequest memberAuthorizeRequest) {
        final MemberAuthorizeResponse memberAuthorizeResponse = regularAuthService.authorize(memberAuthorizeRequest);
        return ResponseEntity.ok(memberAuthorizeResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid final MemberLoginRequest memberLoginRequest) {
        final MemberLoginResult memberLoginResult = regularAuthService.login(memberLoginRequest);
        final ResponseCookie cookie = refreshTokenCookieProvider.createCookie(memberLoginResult.refreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(MemberLoginResponse.from(memberLoginResult));
    }

    @PostMapping("/logout")
    @RegularUser
    public ResponseEntity<?> logout(@Authenticated final AuthPrincipal authPrincipal,
                                    @CookieValue(name = REFRESH_TOKEN, required = false) final String refreshTokenValue) {
        validateRefreshToken(refreshTokenValue);
        regularAuthService.logout(authPrincipal, refreshTokenValue);
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookieProvider.createLogoutCookie().toString())
                .build();
    }

    @PostMapping("/reissue")
    @RegularUser
    public ResponseEntity<?> reissue(@Authenticated final AuthPrincipal authPrincipal,
                                     @CookieValue(name = REFRESH_TOKEN, required = false) final String refreshTokenValue) {
        validateRefreshToken(refreshTokenValue);
        final MemberReissueResult memberReissueResult = regularAuthService.reissue(authPrincipal, refreshTokenValue);
        final ResponseCookie cookie = refreshTokenCookieProvider.createCookie(memberReissueResult.refreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(MemberReissueResponse.from(memberReissueResult));
    }

    private void validateRefreshToken(final String value) {
        if (value == null || value.isBlank()) {
            throw new RefreshTokenException(RefreshTokenErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }
    }
}
