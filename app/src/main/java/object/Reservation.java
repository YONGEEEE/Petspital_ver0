package object;

import android.os.Parcel;
import android.os.Parcelable;

import listview.CommentItem;

public class Reservation implements Parcelable {
    private int num;
    private String petspital;
    private String regdate;
    private String userid;
    private String name;
    private int age;
    private float weight;
    private String birth;
    private String inform;
    private String kind;
    private int flag; // 1: 강아지 0 : 고양이
    private int sex; // 1 : 남자 0 : 여자

    public Reservation() {
    }

    public Reservation(String petspital, String regdate) {
        this.petspital = petspital;
        this.regdate = regdate;
    }

    public Reservation(String petspital, String regdate, String userid, String name, int age, float weight, String birth, String inform, String kind, int flag, int sex) {
        this.petspital = petspital;
        this.regdate = regdate;
        this.userid = userid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.birth = birth;
        this.inform = inform;
        this.kind = kind;
        this.flag = flag;
        this.sex = sex;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPetspital() {
        return petspital;
    }

    public void setPetspital(String petspital) {
        this.petspital = petspital;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "num=" + num +
                ", petspital='" + petspital + '\'' +
                ", regdate='" + regdate + '\'' +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", birth='" + birth + '\'' +
                ", inform='" + inform + '\'' +
                ", kind='" + kind + '\'' +
                ", flag=" + flag +
                ", sex=" + sex +
                '}';
    }


    public Reservation(Parcel src) {
        num = src.readInt();
        this.petspital = src.readString();
        this.regdate = src.readString();
        this.userid = src.readString();
        this.name = src.readString();
        this.age = src.readInt();
        this.weight = src.readFloat();
        this.birth = src.readString();
        this.inform = src.readString();
        this.kind = src.readString();
        this.flag = src.readInt();
        this.sex = src.readInt();
    }

    public static final Creator CREATOR = new Creator() {
        public Reservation createFromParcel(Parcel src) {
            return new Reservation(src);
        }

        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    @Override
    public int describeContents() { // 필수 오버라이드
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { // 필수 오버라이드
        dest.writeInt(num);
        dest.writeString(name);
        dest.writeString(regdate);
        dest.writeString(petspital);
        dest.writeString(userid);
        dest.writeFloat(weight);
        dest.writeInt(age);
        dest.writeInt(flag);
        dest.writeInt(sex);
        dest.writeString(birth);
        dest.writeString(inform);
        dest.writeString(kind);
    }
}

