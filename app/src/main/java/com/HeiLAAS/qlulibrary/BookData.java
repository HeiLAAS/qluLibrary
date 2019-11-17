package com.HeiLAAS.qlulibrary;

public class BookData {
    private String BookName;
    private String BookAuthor;
    private String Url;

    public BookData(String BookName,String BookAuthor,String Url){
        this.BookName=BookName;
        this.BookAuthor=BookAuthor;
        this.Url=Url;
    }
    public void setBookName(String BookName){
        this.BookName= BookName;
    }
    public void setBookAuthor(String BookAuthor){
        this.BookAuthor = BookAuthor;
    }
    public void setUrl(String Url){
        this.Url=Url;
    }
    public String getBookName(){
            return BookName;
    }
    public String getBookAuthor(){
        return BookAuthor;
    }
    public String getUrl(){
        return Url;
    }
}
