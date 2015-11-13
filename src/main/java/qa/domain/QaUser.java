package qa.domain;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS")
@Document
public class QaUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.annotation.Id
    private int id;

    @Size(min = 3, message = "{user.add.name.size}")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 3, message = "{user.add.password.size}")
    @Column(nullable = false)

    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "whoCreated")
    @DBRef
    private Set<Words> words;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setQuestions(Set<Words> words) {
        this.words = words;
    }

    public Set<Words> getWords() {
        return words;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QaUser) {
            return ((QaUser) obj).name.equals(this.name);
        }
        return false;
    }

    public Set<Words> getQuestions() {
        return words.stream().filter(w -> w instanceof Question).collect(Collectors.toSet());
    }

    public Set<Words> getAnswers() {
        return words.stream().filter(w -> w instanceof Answer).collect(Collectors.toSet());
    }

    public int getReputation() {
        return words.stream().mapToInt(Words::getPoints).sum();
    }
}
