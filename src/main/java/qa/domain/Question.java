package qa.domain;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;
import org.springframework.security.access.vote.AbstractAclVoter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//TODO 把answerCount替换为Answer对象
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
    private int answerCount;

    @Column(nullable = false)
    private int viewCount;

    private int points;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.PERSIST)
    private Set<Vote> votes;

    public Question() {
        answerCount = 0;
        viewCount = 0;
        points = 0;
        votes = new HashSet<>();
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
        return votes.stream().mapToInt(vote -> vote.isUpVoted() ? 1 : -1).sum();
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

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public void addVote(Vote vote) {
        votes.add(vote);
    }

    public void minusPoint(int point) {
        this.points -= point;
    }
}
