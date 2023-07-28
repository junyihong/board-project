package com.junyihong.boardproject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter @Column(nullable = false) private String title;                       // 제목
    @Setter @Column(nullable = false, length = 10000) private String content;     // 본문

    @Setter private String hashtag;     // 해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // JPA Auditing 어노테이션 사용
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;         // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;    // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;   // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;   // 수정자


    protected Article() {}
    private Article (String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
        // 엔티티를 데이터베이스의 영속화시키고 연결짓고 사용하는 환경에서
        // 서로다른 두 row(엔티티) 가 같은 조건이 무엇인가에 대해 equals가 답을 하고있는것.
        // id가 같은지를 검사해서 두 엔티티가 같은지를 판별함
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
