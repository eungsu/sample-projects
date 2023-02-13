package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.mapper.BookMapper;
import com.example.mapper.UserMapper;
import com.example.vo.Book;
import com.example.vo.User;

@SpringBootApplication
public class KakaopayApplication {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BookMapper bookMapper;

    public static void main(String[] args) {
        SpringApplication.run(KakaopayApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CommandLineRunner runner() {
    	PasswordEncoder passwordEncoder = passwordEncoder();
    	return (arg) -> {
    		userMapper.insertUser(new User("홍길동", "hong@gmail.com", passwordEncoder.encode("zxcv1234")));
    		userMapper.insertUser(new User("김유신", "kim@gmail.com", passwordEncoder.encode("zxcv1234")));
    		userMapper.insertUser(new User("강감찬", "kang@gmail.com", passwordEncoder.encode("zxcv1234")));
    		userMapper.insertUser(new User("이순신", "lee@gmail.com", passwordEncoder.encode("zxcv1234")));
    		
    		bookMapper.insertBook(new Book("진짜 쓰는 실무 엑셀", "전진권", "", "제이펍", 21000, 10));
    		bookMapper.insertBook(new Book("1일 1로그 100일 완성 IT지식", "브라이언 W. 커니핸", "", "인사이트", 20000, 5));
    		bookMapper.insertBook(new Book("Do it! 점프 투 파이썬", "박응용", "", "이지스퍼블리싱", 18000, 20));
    		bookMapper.insertBook(new Book("비전공자를 위한 이해할 수 있는 IT 지식", "최원영", "", "티더블유아이지", 16000, 5));
    		bookMapper.insertBook(new Book("회사에서 바로 통하는 실무 엑셀+파워포인트", "전미진", "", "한빛미디어", 22000, 3));
    		bookMapper.insertBook(new Book("혼자 공부하는 파이썬", "윤인성", "", "한빛미디어", 18000, 20));
    		bookMapper.insertBook(new Book("맛있는 디자인 포토샵과 일러스트레이트", "박정아", "", "한빛미디어", 22000, 10));
    		bookMapper.insertBook(new Book("프로그래머의 뇌", "펠리너 헤르만스", "", "제이펍", 24000, 5));
    		bookMapper.insertBook(new Book("Clean Code 클린 코드", "로버트 C. 마틴", "", "인사이트", 33000, 7));
    		bookMapper.insertBook(new Book("혼자 공부하는 머신러닝+딥러닝", "박해선", "", "한빛미디어", 26000, 10));
    	};
    }

}
