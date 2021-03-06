package blackout.jester.BarData;

import android.app.usage.UsageEvents;
import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Contains all data concerning a single event
 */

public class BarEventData implements Parcelable{

    private String description;
    private String time;
    private String date;
    private EventType eventType;
    private BigDecimal coverCharge;
    //recurrence

    public BarEventData(String description, String time, String date, EventType aEventType, BigDecimal coverCharge){
        this.description = description;
        this.time = time;
        this.date = date;
        this.eventType = aEventType;
        this.coverCharge = coverCharge;
    }

    // Getters

    public String getDescription(){
        return description;
    }

    public String getTime(){
        return time;
    }

    public String getDate(){
        return date;
    }

    public EventType getEventType() {return eventType;}

    public BigDecimal getCoverCharge(){
        return coverCharge;
    }

    //Parceling data
    private BarEventData(Parcel in) {
        this.description = in.readString();
        this.time = in.readString();
        this.date = in.readString();
        this.eventType = EventType.valueOf(in.readString());
        this.coverCharge = new BigDecimal(in.readString());
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.description);
        out.writeString(this.date);
        out.writeString(this.time);
        out.writeString(this.eventType.name());
        out.writeString(this.coverCharge.toString());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public BarEventData createFromParcel(Parcel in) {
            return new BarEventData(in);
        }

        public BarEventData[] newArray(int size) {
            return new BarEventData[size];
        }
    };

}
