package app.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EBOOK")
public class EBook extends Book {

    @Column(length = 30)
    private String format; // PDF/EPUB etc.

    public EBook() {}

    public EBook(String name, double price, Author author, String format) {
        super(name, price, author);
        this.format = format;
    }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    @Override
    public String getType() {
        return "EBOOK";
    }
}
