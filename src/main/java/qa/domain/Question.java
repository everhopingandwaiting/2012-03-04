package qa.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "QUESTIONS")
@DiscriminatorValue("QUESTION")
public class Question extends Words implements Serializable {
    @Size(min = 10, message = "{question.add.title.size}")
    @Column(nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinTable(name = "QUESTIONS_TAGS",
            joinColumns = @JoinColumn(name = "QUESTION_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID", nullable = false))
    private Set<Tag> tags;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,
            mappedBy = "question")
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Question) {
            Question that = (Question) obj;
            return Objects.equals(this.title, that.title)
                    && Objects.equals(this.content, that.content);
        }
        return false;
    }
}
