package qa.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "WORDS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Type(type = "org.hibernate.type.InstantType")
    @Column(nullable = false)
    protected Instant whenCreated;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    protected QaUser whoCreated;

    @Column(nullable = false)
    protected int points;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "words")
    protected Set<Vote> votes;

    @Lob
    @Column(nullable = false)
    protected String content;

    public int getVoteCount() {
        return votes.stream().mapToInt(vote -> vote.isUpVoted() ? 1 : -1).sum();
    }

    public Words() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addPoint(int point) {
        points += point;
    }

    public void minusPoint(int point) {
        points -= point;
    }
}
