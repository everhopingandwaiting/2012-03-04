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
    @JoinColumn(name = "WORDS_ID", nullable = false)
    private Words words;

    private boolean upVoted;

    public void setId(int id) {
        this.id = id;
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

    public void setUpVoted(boolean upVoted) {
        this.upVoted = upVoted;
    }

    public boolean isUpVoted() {
        return upVoted;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public Words getWords() {
        return words;
    }
}
