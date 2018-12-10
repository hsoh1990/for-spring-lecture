package com.ex.forblog.book;

//@Service
public class BookService {

    public BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


//    @PostConstruct
//    public void postBookService(){
//        System.out.println("==============");
//        System.out.println("book service");
//    }

}
