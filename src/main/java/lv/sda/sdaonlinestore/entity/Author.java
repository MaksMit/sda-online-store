package lv.sda.sdaonlinestore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(name = "firstName")
    @NotNull
    @Size(min=3, max=60)
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    @Size(min=3, max=60)
    private String lastName;

    @OneToMany(mappedBy = "author")
    private Set<Product> products;

    public Long getId() {
        return authorId;
    }

    public void setId(Long id) {
        this.authorId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
