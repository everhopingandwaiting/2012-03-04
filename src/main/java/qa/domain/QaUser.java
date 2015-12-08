package qa.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS")
public class QaUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 6, message = "{user.add.name.size}")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(min = 8, message = "{user.add.password.size}")
    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "whoCreated")
    private Set<Words> words;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinTable(name = "USERS_TAGS",
            joinColumns = @JoinColumn(name = "USER_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID", nullable = false))
    private Set<Tag> tags;

    public QaUser(String name) {
        this.name = name;
    }

    public QaUser() {
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

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

//    public Set<Tag> getTags() {
//        return words.stream().filter().collect(Collectors.toSet());
//    }
}
