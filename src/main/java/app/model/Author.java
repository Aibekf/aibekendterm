package app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Author extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String name;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
