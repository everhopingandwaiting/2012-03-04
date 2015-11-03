package qa.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ANSWERS")
@DiscriminatorValue("ANSWER")
public class Answer extends Words implements Serializable {
    @Column(nullable = false)
    private boolean accepted;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Words question;

    @Override
    public int getVoteCount() {
        return votes.size();
    }

    public Answer() {
        accepted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Instant getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Instant whenCreated) {
        this.whenCreated = whenCreated;
    }

    public void setQuestion(Words question) {
        this.question = question;
    }

    public Words getQuestion() {
        return question;
    }
}
