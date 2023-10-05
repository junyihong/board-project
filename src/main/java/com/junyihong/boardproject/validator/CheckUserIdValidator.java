package com.junyihong.boardproject.validator;

import com.junyihong.boardproject.dto.request.UserAccountRequestDto;
import com.junyihong.boardproject.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUserIdValidator extends AbstractValidator<UserAccountRequestDto> {

    private final UserAccountRepository userAccountRepository;

    @Override
    protected void doValidate(UserAccountRequestDto dto, Errors errors) {
        if (userAccountRepository.existsByUserId(dto.toEntity().getUserId())) {
            errors.rejectValue("userId", "아이디 중복 오류", " * 이미 사용중인 아이디 입니다.");
        }
    }
}