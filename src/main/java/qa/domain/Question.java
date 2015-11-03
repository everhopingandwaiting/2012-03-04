package qa.domain;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "QUESTIONS")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 10, message = "{question.add.title.size}")
    @Column(nullable = false)
    private String title;

    @Size(min = 50, message = "{question.add.content.size}")
    @Lob
    @Column(nullable = false)
    private String content;

    @Type(type = "org.hibernate.type.InstantType")
    @Column(nullable = false)
    private Instant whenCreated;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false)
    private QaUser whoCreated;

    private String tags;

    @Column(nullable = false)
    private int voteCount;

    @Column(nullable = false)
    private int answerCount;

    @Column(nullable = false)
    private int viewCount;

    private int points;

    public Question() {
        voteCount = 0;
        answerCount = 0;
        viewCount = 0;
        points = 0;
        whenCreated = Instant.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint(int point) {
        this.points += point;
    }
}
