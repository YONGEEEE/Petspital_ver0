package object;

import java.io.Serializable;

public class PetData implements Serializable {

    private String userid;
    private String petid;
    private String name;
    private String age;
    private String weight;
    private String birth;
    private String infrom;
    private String kind;
    private int flag; // 1: 강아지 0 : 고양이

    public PetData() {
    }

    public PetData(String userid, String petid, String name, String age, String weight, String birth, String infrom, String kind, int flag) {
        this.userid = userid;
        this.petid = petid;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.birth = birth;
        this.infrom = infrom;
        this.kind = kind;
        this.flag = flag;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getInfrom() {
        return infrom;
    }

    public void setInfrom(String infrom) {
        this.infrom = infrom;
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
}
