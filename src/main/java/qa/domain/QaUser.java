package qa.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

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
    private Set<Question> questions;

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

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof QaUser) {
            return ((QaUser)obj).name.equals(this.name);
        }
        return false;
    }

    public int getReputation() {
        return questions.stream().mapToInt(question -> question.getPoints()).sum();
    }
}
