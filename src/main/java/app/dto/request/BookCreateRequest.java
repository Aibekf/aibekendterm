package app.dto.request;

import app.patterns.factory.BookType;
import jakarta.validation.constraints.*;

public class BookCreateRequest {

    @NotNull
    private BookType type;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Positive
    private double price;

    @NotNull
    private Long authorId;

    private String format;

    @Positive
    private Integer pages;

    public BookType getType() { return type; }
    public void setType(BookType type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
