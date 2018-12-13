package com.ex.forblog.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired @Qualifier("hsBookRepository")
    public BookRepository bookRepository;

    public void printBookRepository(){
        System.out.println(bookRepository.getClass());

    }

}
