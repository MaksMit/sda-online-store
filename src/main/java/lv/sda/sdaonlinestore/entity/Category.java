package lv.sda.sdaonlinestore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    @NotNull
    @Size(min=3, max=60)
    private String name;

    @Column(name = "parentName")
    @NotNull
    @Size(min=3, max=60)
    private String parentName;

    @Column(name = "childName")
    @NotNull
    @Size(min=3, max=60)
    private String childName;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + categoryId +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", childName='" + childName + '\'' +
                '}';
    }
}
