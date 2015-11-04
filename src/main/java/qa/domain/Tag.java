package qa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TAGS")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "QUESTIONS_TAGS",
            joinColumns = @JoinColumn(name = "TAG_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID", nullable = false))
    public Set<Question> questions;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return String.format("{name:%s, id:%s}", name, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tag) {
            Tag that = (Tag) obj;
            return Objects.equals(that.name, this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
