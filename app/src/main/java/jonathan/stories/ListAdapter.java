package jonathan.stories;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Joe on 2/8/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.StoryHolder> {


    public static final String TAG = ListAdapter.class.getSimpleName();
    public static final String ANSWER = "answers";
    Stories mStories;
    AnswerList mAnswerList;
    Context mContext;

    public ListAdapter(Context context, Stories stories, AnswerList answerList) {
        mStories = stories;
        mContext = context;
        mAnswerList = answerList;
    }
    @Override
    public StoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_layout, parent, false);
        StoryHolder holder = new StoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StoryHolder holder, int position) {
            holder.bindStory(mStories.getStory(position), mAnswerList.getAnswers(position));
    }

    @Override
    public int getItemCount() {
        return mStories.getStorySize();
    }

   public class StoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView storyTitleLabel;
        Story mStory;
       Answers mAnswers;

        public StoryHolder(View itemView) {
            super(itemView);

            storyTitleLabel = (TextView) itemView.findViewById(R.id.storyLabel);

            itemView.setOnClickListener(this);
        }

        public void bindStory(Story story, Answers answers) {

            mStory = story;
            storyTitleLabel.setText(story.getTitle());
            mAnswers = answers;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(TAG, mStory);
            bundle.putParcelable(ANSWER, mAnswers);
            intent.setClass(mContext, StoryActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }
}
