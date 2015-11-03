package qa.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

//TODO: 完成Answer实体类相关的映射
@Entity
@Table(name = "ANSWERS")
public class Answer implements Serializable {
    @Id
    private int id;
    private boolean accepted;
    private Instant whenCreated;
    private QaUser whoCreated;
    private Set<Vote> votes;
    private int points;
    private String content;

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

    public QaUser getWhoCreated() {
        return whoCreated;
    }

    public void setWhoCreated(QaUser whoCreated) {
        this.whoCreated = whoCreated;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
