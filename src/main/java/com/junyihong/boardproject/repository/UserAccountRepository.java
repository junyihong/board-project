package com.junyihong.boardproject.repository;


import com.junyihong.boardproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
}