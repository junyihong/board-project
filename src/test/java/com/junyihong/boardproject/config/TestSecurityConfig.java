package com.junyihong.boardproject.config;

import com.junyihong.boardproject.domain.UserAccount;
import com.junyihong.boardproject.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "junyihongTest",
                "pw",
                "junyihong-test@naver.com",
                "junyihong-test",
                "test memo"
        )));
    }

}
