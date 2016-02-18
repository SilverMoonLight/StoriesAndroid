package jonathan.stories;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joe on 2/8/2016.
 */
public class StoryFragment extends Fragment {

    public static final String QUESTION = "question";
    public static final String SHOW = "show";
    public static final String ANSWERS = "answers";
    public static final String CHOICE1 = "choice1";
    public static final String CHOICE2 = "choice2";
    public static final String CHOICE3 = "choice3";
    public static final String CORRECT = "correct";
    ArrayList<String> mArrayList;
    ArrayList<String> mChoice1;
    ArrayList<String> mChoice2;
    ArrayList<String> mChoice3;
    ArrayList<String> mCorrect;
    RecyclerView mRecyclerView;
    StoryAdapter mAdapter;
    int num = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).setTitle("start").
                setMessage("click on the next button on the top right").
                setPositiveButton(android.R.string.ok, null).create();
        alertDialog.show();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.story_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.next_button:
                num++;
                mAdapter.setVisiblity(num);
                mAdapter.onBindViewHolder(mAdapter.mQuestionHolder, 1);
                updateUI();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void updateUI() {

        if(mAdapter == null) {
            mAdapter = new StoryAdapter(mArrayList, mChoice1, mChoice2, mChoice3, mCorrect);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mArrayList = (ArrayList<String>) getArguments().getSerializable(QUESTION);
        mChoice1 = (ArrayList<String>) getArguments().getSerializable(CHOICE1);
        mChoice2 = (ArrayList<String>) getArguments().getSerializable(CHOICE2);
        mChoice3 = (ArrayList<String>) getArguments().getSerializable(CHOICE3);
        mCorrect = (ArrayList<String>) getArguments().getSerializable(CORRECT);


        View view = inflater.inflate(R.layout.question_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.questionView);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }



    class StoryAdapter extends RecyclerView.Adapter<QuestionHolder> {

        ArrayList<String> mArrayList;
        ArrayList<String> mc1;
        ArrayList<String> mc2;
        ArrayList<String> mc3;
        ArrayList<String> mc;
        int number = 0;
        QuestionHolder mQuestionHolder;
        public StoryAdapter(ArrayList<String> questions,ArrayList<String> choice1,
                            ArrayList<String> choice2, ArrayList<String> choice3, ArrayList<String> correct) {
            mArrayList = questions;
            mc1 = choice1;
            mc2 = choice2;
            mc3 = choice3;
            mc = correct;

        }

        public void setVisiblity(int num) {
                number = num;
        }

        @Override
        public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_layout, parent, false);
            mQuestionHolder = new QuestionHolder(view);

            return mQuestionHolder;
        }



        @Override
        public void onBindViewHolder(QuestionHolder holder, int position) {

                holder.startStory(mArrayList.get(position));


            holder.setChoices(mc1.get(position), mc2.get(position), mc3.get(position), mc.get(position));

            if(number > 0){

                if(holder.getAdapterPosition() < number ) {
                  holder.mQuestionLabel.setVisibility(View.VISIBLE);


                }


            }

            if(holder.mQuestionLabel.isClickable()) {
                Log.d(ANSWERS, holder.getAdapterPosition() + "");
            }


        }


        @Override
        public int getItemCount() {
            return mArrayList.size();
        }
    }

    class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mQuestionLabel;
        MultiChoiceDialog choices;
        QuestionHolder(View view){
            super(view);
            view.setOnClickListener(this);
            mQuestionLabel = (TextView) view.findViewById(R.id.questionLabel);
        }


        public void startStory(String question) {

            mQuestionLabel.setText(question);
            mQuestionLabel.setVisibility(View.INVISIBLE);


        }

        public void updateView(String question) {
            mQuestionLabel.setVisibility(View.VISIBLE);
        }

        public void setChoices(String choices1, String choice2, String choice3, String correct) {

            choices = MultiChoiceDialog.newInstance(choices1, choice2, choice3 ,correct);
        }

        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
            if(getAdapterPosition() < num) {
                choices.show(fragmentManager, SHOW);
            }

        }
    }
}
