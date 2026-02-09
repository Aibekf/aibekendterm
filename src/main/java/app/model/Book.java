package app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "book_type")
public abstract class Book extends BaseEntity {

    @Column(nullable = false, length = 150)
    protected String name;

    @Column(nullable = false)
    protected double price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    protected Author author;

    protected Book() {}

    protected Book(String name, double price, Author author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public Author getAuthor() { return author; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setAuthor(Author author) { this.author = author; }

    // Polymorphism target:
    public abstract String getType();

    // Example method to show OOP:
    public double calculateValue() {
        return price; // subclasses may override if needed
    }
}
