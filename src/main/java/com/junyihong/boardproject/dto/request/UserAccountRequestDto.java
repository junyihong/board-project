package com.junyihong.boardproject.dto.request;

import com.junyihong.boardproject.domain.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccountRequestDto {

    @NotBlank(message = "* 아이디를 입력해주세요.")
    @Size(min = 4, max = 20, message = "아이디를 최소 4자, 최대 20자로 설정해주세요")
    private String userId;

    @NotBlank(message = "* 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])(?!.*[\\W_\\s]).{6,14}$", message = "* 비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
    private String userPassword;

    @NotBlank(message = "* 이메일을 입력해주세요.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "* 별명을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "* 별명은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;

    @Size(max = 255, message = "* 메모는 255자 이하로 설정해주세요.")
    private String memo;

    public UserAccount toEntity() {
        return UserAccount.builder()
                .userId(this.userId)
                .userPassword(this.userPassword)
                .email(this.email)
                .nickname(this.nickname)
                .memo(this.memo)
                .build();
    }
}
