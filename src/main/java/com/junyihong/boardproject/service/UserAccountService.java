package com.junyihong.boardproject.service;

import com.junyihong.boardproject.domain.UserAccount;
import com.junyihong.boardproject.dto.UserAccountDto;
import com.junyihong.boardproject.dto.request.UserAccountRequestDto;
import com.junyihong.boardproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    /* 회원가입 시, 유효성 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    public UserAccount join(UserAccountRequestDto userAccountRequestDto) {
        userAccountRequestDto.setUserPassword(passwordEncoder.encode(userAccountRequestDto.getUserPassword()));

        return userAccountRepository.save(
                        UserAccount.of(
                                userAccountRequestDto.getUserId(),
                                userAccountRequestDto.getUserPassword(),
                                userAccountRequestDto.getEmail(),
                                userAccountRequestDto.getNickname(),
                                userAccountRequestDto.getMemo()
                                ));
    }

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return userAccountRepository.findById(username)
                .map(UserAccountDto::from);
    }

    public UserAccountDto saveUser(String userId, String userPassword, String email, String nickname, String memo) {
        return UserAccountDto.from(
                userAccountRepository.save(UserAccount.of(userId, userPassword, email, nickname, memo, userId))
        );
    }
}
