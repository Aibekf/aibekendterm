package app.dto.response;

public class BookResponse {
    private Long id;
    private String type;
    private String name;
    private double price;

    private Long authorId;
    private String authorName;

    private String format;
    private Integer pages;

    public BookResponse(Long id, String type, String name, double price,
                        Long authorId, String authorName,
                        String format, Integer pages) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.authorId = authorId;
        this.authorName = authorName;
        this.format = format;
        this.pages = pages;
    }

    public Long getId() { return id; }
    public String getType() { return type; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Long getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getFormat() { return format; }
    public Integer getPages() { return pages; }
}
