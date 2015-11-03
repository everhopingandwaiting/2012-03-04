package qa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VOTES")
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false)
    private QaUser whoVoted;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question;

    private boolean upVoted;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setWhoVoted(QaUser whoVoted) {
        this.whoVoted = whoVoted;
    }

    public int getId() {
        return id;
    }

    public QaUser getWhoVoted() {
        return whoVoted;
    }

    public Question getQuestion() {
        return question;
    }

    public void setUpVoted(boolean upVoted) {
        this.upVoted = upVoted;
    }

    public boolean isUpVoted() {
        return upVoted;
    }
}
