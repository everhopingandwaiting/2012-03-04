package qa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ANSWERS")
@DiscriminatorValue("ANSWER")
public class Answer extends Words implements Serializable {
    @Column(nullable = false)
    private boolean accepted;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Words question;

    @Override
    public int getVoteCount() {
        return votes.size();
    }

    public Answer() {
        accepted = false;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setQuestion(Words question) {
        this.question = question;
    }

    public Words getQuestion() {
        return question;
    }
}
