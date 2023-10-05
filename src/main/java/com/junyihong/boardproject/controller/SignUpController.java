package com.junyihong.boardproject.controller;

import com.junyihong.boardproject.dto.request.UserAccountRequestDto;
import com.junyihong.boardproject.service.UserAccountService;
import com.junyihong.boardproject.validator.CheckEmailValidator;
import com.junyihong.boardproject.validator.CheckNicknameValidator;
import com.junyihong.boardproject.validator.CheckUserIdValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    private final CheckUserIdValidator checkUserIdValidator;
    private final CheckNicknameValidator checkNicknameValidator;
    private final CheckEmailValidator checkEmailValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUserIdValidator);
        binder.addValidators(checkNicknameValidator);
        binder.addValidators(checkEmailValidator);
    }

    /**
     * 회원 가입
     *
     * @return 회원 가입 페이지
     */
    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("userAccountRequestDto", new UserAccountRequestDto());
        return "sign-up";
    }

    /**
     * 회원 가입 post
     *
     * @param userAccountRequestDto
     * @param bindingResult
     * @param model
     * @return 홈 페이지
     */
    @PostMapping("/signup")
    public String signUp(@Valid UserAccountRequestDto userAccountRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 유지 */
            model.addAttribute("userAccountRequestDto", userAccountRequestDto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userAccountService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "sign-up";
        }
        try {
            userAccountService.join(userAccountRequestDto);
            String messageFromBackend = "회원가입이 성공적으로 이루어졌습니다. 로그인 해주세요.";
            model.addAttribute("messageFromBackend", messageFromBackend);
        } catch (Exception e) {
            return "error";
        }
        return "sign-up";
    }
}

