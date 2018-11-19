package object;

import java.io.Serializable;

public class PetData implements Serializable {

    private String userid;
    private String name;
    private int age;
    private float weight;
    private String birth;
    private String inform;
    private String kind;
    private int flag; // 1: 강아지 0 : 고양이
    private int sex; // 1 : 남자 0 : 여자

    public PetData() {

    }

    public PetData(String userid , String name, int age, float weight, String inform) {
        this.userid = userid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.inform = inform;
    }

    public PetData(String userid, String name, int age, float weight, String birth, String inform, String kind, int flag, int sex) {
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
        return "PetData{" +
                "userid='" + userid + '\'' +
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
}
