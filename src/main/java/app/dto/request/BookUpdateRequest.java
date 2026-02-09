package app.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BookUpdateRequest {

    @Size(max = 150)
    private String name;

    @Positive
    private Double price;

    private Long authorId;

    private String format;
    @Positive
    private Integer pages;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
