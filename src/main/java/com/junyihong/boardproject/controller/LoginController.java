package com.junyihong.boardproject.controller;

import com.junyihong.boardproject.dto.UserAccountDto;
import com.junyihong.boardproject.dto.security.BoardPrincipal;
import com.junyihong.boardproject.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public LoginController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    /**
     * 로그인 페이지
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, RedirectAttributes redirectAttributes) {
        UserAccountDto userAccountDto = userAccountRepository.findById(username)
                .map(UserAccountDto::from)
                .orElse(null);

        if (userAccountDto != null && userAccountDto.userPassword().equals(password)) {
            // 로그인 성공 시 사용자 정보로 Principal 객체를 생성하여 인증 처리
            BoardPrincipal boardPrincipal = BoardPrincipal.from(userAccountDto);
            Authentication authentication = new UsernamePasswordAuthenticationToken(boardPrincipal, null, boardPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 로그인 성공 후 대시보드 페이지로 리다이렉트
            return "redirect:/";
        } else {
            // 로그인 실패 시 에러 메시지와 함께 다시 로그인 페이지로 리다이렉트
            redirectAttributes.addFlashAttribute("error", "로그인에 실패하였습니다. 다시 시도해주세요.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login";
    }
}
