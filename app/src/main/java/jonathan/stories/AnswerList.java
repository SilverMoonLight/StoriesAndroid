package jonathan.stories;

import java.util.ArrayList;

/**
 * Created by Joe on 2/15/2016.
 */
public class AnswerList {

    ArrayList<Answers> mAnswers;

    public AnswerList() {
        mAnswers = new ArrayList<>();
    }

    public void addAnswers(Answers answers) {
        mAnswers.add(answers);
    }

    public int getListSize() {
        return mAnswers.size();
    }

    public Answers getAnswers(int index) {

        Answers answers = mAnswers.get(index);

        return answers;
    }

    public ArrayList<Answers> getAllAnswers() {
        return  mAnswers;
    }
}
