package jonathan.stories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StoryActivity extends AppCompatActivity {

    public static final String QUESTION = "question";
    public static final String CHOICE1 = "choice1";
    public static final String CHOICE2 = "choice2";
    public static final String CHOICE3 = "choice3";
    public static final String CORRECT = "correct";
    Story mStory;
    ArrayList<String> mQuestions;
    ArrayList<String> mChoice1;
    ArrayList<String> mChoice2;
    ArrayList<String> mChoice3;
    ArrayList<String> mCorrect;
    Answers mAnswers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            mStory = b.getParcelable(ListAdapter.TAG);
            mAnswers = b.getParcelable(ListAdapter.ANSWER);
        }

        setQuestions();
        setChoices();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);

        Bundle bundle = new Bundle();
        bundle.putSerializable(QUESTION, mQuestions);
        bundle.putSerializable(CHOICE1, mChoice1);
        bundle.putSerializable(CHOICE2, mChoice2);
        bundle.putSerializable(CHOICE3, mChoice3);
        bundle.putSerializable(CORRECT, mCorrect);
        if (fragment == null) {
            fragment = new StoryFragment();
            fragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.fragment_layout, fragment).commit();
        }

    }



    public void setChoices() {

        mChoice1 = new ArrayList<String>();
        mChoice2 = new ArrayList<String>();
        mChoice3 = new ArrayList<String>();
        mCorrect = new ArrayList<String>();

        mChoice1.add(mAnswers.getAnswer1Choice1());
        mChoice1.add(mAnswers.getAnswer2Choice1());
        mChoice1.add(mAnswers.getAnswer3Choice1());
        mChoice1.add(mAnswers.getAnswer4Choice1());
        mChoice1.add(mAnswers.getAnswer5Choice1());
        mChoice1.add(mAnswers.getAnswer6Choice1());
        mChoice1.add(mAnswers.getAnswer7Choice1());
        mChoice1.add(mAnswers.getAnswer8Choice1());

        mChoice2.add(mAnswers.getAnswer1Choice2());
        mChoice2.add(mAnswers.getAnswer2Choice2());
        mChoice2.add(mAnswers.getAnswer3Choice2());
        mChoice2.add(mAnswers.getAnswer4Choice2());
        mChoice2.add(mAnswers.getAnswer5Choice2());
        mChoice2.add(mAnswers.getAnswer6Choice2());
        mChoice2.add(mAnswers.getAnswer7Choice2());
        mChoice2.add(mAnswers.getAnswer8Choice2());

        mChoice3.add(mAnswers.getAnswer1Choice3());
        mChoice3.add(mAnswers.getAnswer2Choice3());
        mChoice3.add(mAnswers.getAnswer3Choice3());
        mChoice3.add(mAnswers.getAnswer4Choice3());
        mChoice3.add(mAnswers.getAnswer5Choice3());
        mChoice3.add(mAnswers.getAnswer6Choice3());
        mChoice3.add(mAnswers.getAnswer7Choice3());
        mChoice3.add(mAnswers.getAnswer8Choice3());

        mCorrect.add(mAnswers.getAnswer1Correct());
        mCorrect.add(mAnswers.getAnswer2Correct());
        mCorrect.add(mAnswers.getAnswer3Correct());
        mCorrect.add(mAnswers.getAnswer4Correct());
        mCorrect.add(mAnswers.getAnswer5Correct());
        mCorrect.add(mAnswers.getAnswer6Correct());
        mCorrect.add(mAnswers.getAnswer7Correct());
        mCorrect.add(mAnswers.getAnswer8Correct());

    }

    public void setQuestions() {

        mQuestions = new ArrayList<>();
        mQuestions.add(mStory.getQuestion1());
        mQuestions.add(mStory.getQuestion2());
        mQuestions.add(mStory.getQuestion3());
        mQuestions.add(mStory.getQuestion4());
        mQuestions.add(mStory.getQuestion5());
        mQuestions.add(mStory.getQuestion6());
        mQuestions.add(mStory.getQuestion7());
        mQuestions.add(mStory.getQuestion8());
    }



}







