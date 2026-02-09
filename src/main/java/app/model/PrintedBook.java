package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRINTED")
public class PrintedBook extends Book {

    @Column
    private Integer pages;

    public PrintedBook() {}

    public PrintedBook(String name, double price, Author author, Integer pages) {
        super(name, price, author);
        this.pages = pages;
    }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }

    @Override
    public String getType() {
        return "PRINTED";
    }
}
