package org.chzzk.howmeet.domain.regular.auth.service;

import org.chzzk.howmeet.domain.common.auth.model.AuthPrincipal;
import org.chzzk.howmeet.domain.regular.auth.entity.RefreshToken;
import org.chzzk.howmeet.domain.regular.auth.repository.RefreshTokenRepository;
import org.chzzk.howmeet.domain.regular.auth.util.RefreshTokenProvider;
import org.chzzk.howmeet.fixture.GuestFixture;
import org.chzzk.howmeet.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class RefreshTokenCrudServiceTest {
    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @Mock
    RefreshTokenProvider refreshTokenProvider;

    @InjectMocks
    RefreshTokenCrudService refreshTokenCrudService;

    AuthPrincipal memberPrincipal = AuthPrincipal.from(MemberFixture.KIM.생성());
    AuthPrincipal guestAuthPrincipal = AuthPrincipal.from(GuestFixture.KIM.생성());
    String refreshTokenValue = "refreshToken";
    Long expiration = 360_000L;

    @Test
    @DisplayName("리프레시 토큰 저장")
    public void save() throws Exception {
        // given
        final RefreshToken expect = RefreshToken.of(memberPrincipal, refreshTokenValue, expiration);

        // when
        doReturn(expect).when(refreshTokenProvider)
                .createToken(memberPrincipal);
        doReturn(expect).when(refreshTokenRepository)
                .save(any());
        final RefreshToken actual = refreshTokenCrudService.save(memberPrincipal);

        // then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("1회용 회원으로 리프레시 토큰 저장시 예외 발생")
    public void saveWhenInvalidAuthPrincipal() throws Exception {
        assertThatThrownBy(() -> refreshTokenCrudService.save(guestAuthPrincipal))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("토큰 파싱 정보와 리프레시 토큰값을 통해 리프레시 토큰 삭제")
    public void deleteByAuthPrincipalAndValue() throws Exception {
        // when
        doNothing().when(refreshTokenRepository)
                .deleteByMemberIdAndValue(memberPrincipal.id(), refreshTokenValue);

        // then
        assertThatCode(() -> refreshTokenCrudService.delete(memberPrincipal, refreshTokenValue))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1회용 회원으로 리프레시 토큰 삭제시 예외 발생")
    public void deleteWhenInvalidAuthPrincipal() throws Exception {
        assertThatThrownBy(() -> refreshTokenCrudService.delete(guestAuthPrincipal, refreshTokenValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}