package com.ex.forblog.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class BookServiceRunner implements ApplicationRunner{
    @Autowired
    BookService bookService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        bookService.printBookRepository();
    }
}
