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

@Entity
@Table(name = "QUESTIONS")
@DiscriminatorValue("QUESTION")
public class Question extends Words implements Serializable {
    @Size(min = 10, message = "{question.add.title.size}")
    @Column(nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "questions")
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answers;

    @Column(nullable = false)
    private int viewCount;

    public Question() {
        viewCount = 0;
        tags = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int getVoteCount() {
        return votes.stream().mapToInt(vote -> vote.isUpVoted() ? 1 : -1).sum();
    }

    public int getAnswerCount() {
        return answers.size();
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

}
