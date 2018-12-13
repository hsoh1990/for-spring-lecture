package com.ex.forblog.configuration;

import com.ex.forblog.book.BookRepository;
import com.ex.forblog.book.TestBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration {
    @Bean
    public BookRepository bookRepository(){
        return new TestBookRepository();
    }
}
