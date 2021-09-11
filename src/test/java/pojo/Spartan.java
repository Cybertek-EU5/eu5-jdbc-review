package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * It is used to represent data and does not have extra functionality
 * POJO Class has 3 required part :
 * private fields
 * public getters and setters
 * no arg constructor
 *
 * anything else is optional
 */
public class Spartan {

    @SerializedName("id") // this means : map json field "id" into below java field "myId"
    // this will only use the field when deserializing and not when serializing
    @Expose(serialize = false, deserialize = true)
    private int myId;
    // if the json field name is same as java field name no need for @SerializedName
    // it will match automatically
    @Expose
    private String name;
    @Expose
    private String gender;
    @Expose
    private long phone ;

    public Spartan(int myId, String name, String gender, long phone) {
        this.myId = myId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + myId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
