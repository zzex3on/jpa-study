package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// jpa를 사용하는 애구나~ 라고 인식, 관리해야함을 인지 (어노테이션 필수!)
@Entity
// USER 라는 테이블에 동작하도록 표기
//@Table(name = "USER")
public class Member {
    @Id // PK 지정
    private Long id;
    //@Column(name = "username") // DB의 컬럼명을 명시
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
