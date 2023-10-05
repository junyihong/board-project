package com.junyihong.boardproject.service;

import com.junyihong.boardproject.domain.UserAccount;
import com.junyihong.boardproject.dto.UserAccountDto;
import com.junyihong.boardproject.dto.request.UserAccountRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class UserAccountServiceTest {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원가입 테스트")
    public void saveUserAccountTest() {

        UserAccountRequestDto userAccountRequestDto = UserAccountRequestDto.builder()
                .userId("junyihong2")
                .userPassword("asdf1234")
                .email("junyihong2@naver.com")
                .nickname("junyihong2")
                .memo("junyihong2")
                .build();
        UserAccount userAccount = userAccountService.join(userAccountRequestDto);

        UserAccount savedUserAccount = userAccountService.saveUserAccount(userAccount);

        assertEquals(userAccount.getUserId(), savedUserAccount.getUserId());
    }
}