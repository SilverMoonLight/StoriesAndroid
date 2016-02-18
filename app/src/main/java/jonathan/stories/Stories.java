package jonathan.stories;

import java.util.ArrayList;

/**
 * Created by Joe on 2/8/2016.
 */
public class Stories {

    ArrayList<Story> mStories;

    public Stories() {
        mStories = new ArrayList<>();
    }

    public void addStory(Story story) {
        mStories.add(story);
    }

    public int getStorySize() {
       return mStories.size();
    }

    public Story getStory(int index) {

       Story story = mStories.get(index);

        return story;
    }
}
