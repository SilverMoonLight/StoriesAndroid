package jonathan.stories;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    Stories mStories;
    AnswerList mAnswerList;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getConnection();

    }

    private void getConnection() {

        String siteUrl = "http://10stories.esy.es/question.php";

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(siteUrl).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                    String jsonData = response.body().string();
                    if(response.isSuccessful()) {
                       mStories = getStoryInfo(jsonData);
                        mAnswerList = getAnswers(jsonData);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateDisplay();
                            }
                        });

                    } else {
                        alertUserAboutError();
                    } } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {

                    }
                }
            });

        }
    }

    private void updateDisplay() {
        ListAdapter adapter = new ListAdapter(this, mStories, mAnswerList);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean networkOn = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            networkOn = true;
        }

        return networkOn;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error");
    }

    private Stories getStoryInfo(String jsonData) throws JSONException {
        JSONObject info = new JSONObject(jsonData);
        JSONArray questions = info.getJSONArray("questions");
        Stories stories = new Stories();

        for(int i = 0; i < questions.length(); i++) {
            JSONObject jsonObject = questions.getJSONObject(i);

            Story story = new Story();

            story.setTitle(jsonObject.getString("title"));
            story.setQuestion1(jsonObject.getString("question1"));
            story.setQuestion2(jsonObject.getString("question2"));
            story.setQuestion3(jsonObject.getString("question3"));
            story.setQuestion4(jsonObject.getString("question4"));
            story.setQuestion5(jsonObject.getString("question5"));
            story.setQuestion6(jsonObject.getString("question6"));
            story.setQuestion7(jsonObject.getString("question7"));
            story.setQuestion8(jsonObject.getString("question8"));

            stories.addStory(story);

        }

        return stories;

    }

    private AnswerList getAnswers(String jsonData) throws JSONException {


        JSONObject info = new JSONObject(jsonData);
        JSONArray answer1 = info.getJSONArray("answer1");
        JSONArray answer2 = info.getJSONArray("answer2");
        JSONArray answer3 = info.getJSONArray("answer3");
        JSONArray answer4 = info.getJSONArray("answer4");
        JSONArray answer5 = info.getJSONArray("answer5");
        JSONArray answer6 = info.getJSONArray("answer6");
        JSONArray answer7 = info.getJSONArray("answer7");
        JSONArray answer8 = info.getJSONArray("answer8");
        AnswerList answerList = new AnswerList();

        for(int i = 0; i < answer1.length(); i++) {
            JSONObject jsonObject = answer1.getJSONObject(i);
            JSONObject jsonObject2 = answer2.getJSONObject(i);
            JSONObject jsonObject3 = answer3.getJSONObject(i);
            JSONObject jsonObject4 = answer4.getJSONObject(i);
            JSONObject jsonObject5 = answer5.getJSONObject(i);
            JSONObject jsonObject6 = answer6.getJSONObject(i);
            JSONObject jsonObject7 = answer7.getJSONObject(i);
            JSONObject jsonObject8 = answer8.getJSONObject(i);

            Answers answers = new Answers();

            answers.setAnswer1Choice1(jsonObject.getString("choice1"));
            answers.setAnswer1Choice2(jsonObject.getString("choice2"));
            answers.setAnswer1Choice3(jsonObject.getString("choice3"));
            answers.setAnswer1Correct(jsonObject.getString("correct"));

            answers.setAnswer2Choice1(jsonObject2.getString("choice1"));
            answers.setAnswer2Choice2(jsonObject2.getString("choice2"));
            answers.setAnswer2Choice3(jsonObject2.getString("choice3"));
            answers.setAnswer2Correct(jsonObject2.getString("correct"));

            answers.setAnswer3Choice1(jsonObject3.getString("choice1"));
            answers.setAnswer3Choice2(jsonObject3.getString("choice2"));
            answers.setAnswer3Choice3(jsonObject3.getString("choice3"));
            answers.setAnswer3Correct(jsonObject3.getString("correct"));

            answers.setAnswer4Choice1(jsonObject4.getString("choice1"));
            answers.setAnswer4Choice2(jsonObject4.getString("choice2"));
            answers.setAnswer4Choice3(jsonObject4.getString("choice3"));
            answers.setAnswer4Correct(jsonObject4.getString("correct"));

            answers.setAnswer5Choice1(jsonObject5.getString("choice1"));
            answers.setAnswer5Choice2(jsonObject5.getString("choice2"));
            answers.setAnswer5Choice3(jsonObject5.getString("choice3"));
            answers.setAnswer5Correct(jsonObject5.getString("correct"));

            answers.setAnswer6Choice1(jsonObject6.getString("choice1"));
            answers.setAnswer6Choice2(jsonObject6.getString("choice2"));
            answers.setAnswer6Choice3(jsonObject6.getString("choice3"));
            answers.setAnswer6Correct(jsonObject6.getString("correct"));

            answers.setAnswer7Choice1(jsonObject7.getString("choice1"));
            answers.setAnswer7Choice2(jsonObject7.getString("choice2"));
            answers.setAnswer7Choice3(jsonObject7.getString("choice3"));
            answers.setAnswer7Correct(jsonObject7.getString("correct"));

            answers.setAnswer8Choice1(jsonObject8.getString("choice1"));
            answers.setAnswer8Choice2(jsonObject8.getString("choice2"));
            answers.setAnswer8Choice3(jsonObject8.getString("choice3"));
            answers.setAnswer8Correct(jsonObject8.getString("correct"));


            answerList.addAnswers(answers);

        }



        return answerList;

    }
}
