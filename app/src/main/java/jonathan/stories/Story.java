package jonathan.stories;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joe on 2/8/2016.
 */
public class Story implements Parcelable {

    private String mTitle;
    private String mQuestion1;
    private String mQuestion2;
    private String mQuestion3;
    private String mQuestion4;
    private String mQuestion5;
    private String mQuestion6;
    private String mQuestion7;
    private String mQuestion8;


    public Story() {

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getQuestion1() {
        return mQuestion1;
    }

    public void setQuestion1(String question1) {
        mQuestion1 = question1;
    }

    public String getQuestion2() {
        return mQuestion2;
    }

    public void setQuestion2(String question2) {
        mQuestion2 = question2;
    }

    public String getQuestion3() {
        return mQuestion3;
    }

    public void setQuestion3(String question3) {
        mQuestion3 = question3;
    }

    public String getQuestion4() {
        return mQuestion4;
    }

    public void setQuestion4(String question4) {
        mQuestion4 = question4;
    }

    public String getQuestion5() {
        return mQuestion5;
    }

    public void setQuestion5(String question5) {
        mQuestion5 = question5;
    }

    public String getQuestion6() {
        return mQuestion6;
    }

    public void setQuestion6(String question6) {
        mQuestion6 = question6;
    }

    public String getQuestion7() {
        return mQuestion7;
    }

    public void setQuestion7(String question7) {
        mQuestion7 = question7;
    }

    public String getQuestion8() {
        return mQuestion8;
    }

    public void setQuestion8(String question8) {
        mQuestion8 = question8;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mQuestion1);
        dest.writeString(mQuestion2);
        dest.writeString(mQuestion3);
        dest.writeString(mQuestion4);
        dest.writeString(mQuestion5);
        dest.writeString(mQuestion6);
        dest.writeString(mQuestion7);
        dest.writeString(mQuestion8);
    }

    private Story(Parcel in){
        mQuestion1 = in.readString();
        mQuestion2 = in.readString();
        mQuestion3 = in.readString();
        mQuestion4 = in.readString();
        mQuestion5 = in.readString();
        mQuestion6 = in.readString();
        mQuestion7 = in.readString();
        mQuestion8 = in.readString();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[0];
        }
    };
}
