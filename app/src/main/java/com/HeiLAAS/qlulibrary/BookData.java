package com.HeiLAAS.qlulibrary;

public class BookData {
    private String BookName;
    private String BookAuthor;
    private String Url;
    private String NextPage;

    public BookData(String BookName,String BookAuthor,String Url,String NextPage){
        this.BookName=BookName;
        this.BookAuthor=BookAuthor;
        this.Url=Url;
        this.NextPage=NextPage;
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
    public void setNextPage(String NextPage){this.NextPage=NextPage;}
    public String getBookName(){
            return BookName;
    }
    public String getBookAuthor(){
        return BookAuthor;
    }
    public String getUrl(){
        return Url;
    }
    public String getNextPage(){return NextPage;}
}
