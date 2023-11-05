package com.junyihong.boardproject.config;

import com.junyihong.boardproject.domain.UserAccount;
import com.junyihong.boardproject.dto.UserAccountDto;
import com.junyihong.boardproject.repository.UserAccountRepository;
import com.junyihong.boardproject.service.UserAccountService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;
    @MockBean private UserAccountService userAccountService;


    @BeforeTestMethod
    public void securitySetUp() {
        given(userAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(createUserAccountDto());
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "junyihongTest",
                "pw",
                "junyihong-test@naver.com",
                "junyihong-test",
                "test memo"
        );
    }
}
