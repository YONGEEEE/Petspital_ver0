package object;

import java.io.Serializable;

public class PetData implements Serializable {

    String USERID;
    String ID;
    String NM;
    String AGE;
    String WEIGHT;
    String BIRTH;
    String KIND;
    int FLAG;// 1: 강아지 0: 고양이
    String INFORM;

    public PetData(){}

    public PetData(String USERID, String ID, String NM, String AGE, String WEIGHT, String BIRTH, String KIND, int FLAG, String INFORM) {
        this.USERID = USERID;
        this.ID = ID;
        this.NM = NM;
        this.AGE = AGE;
        this.WEIGHT = WEIGHT;
        this.BIRTH = BIRTH;
        this.KIND = KIND;
        this.FLAG = FLAG;
        this.INFORM = INFORM;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNM() {
        return NM;
    }

    public void setNM(String NM) {
        this.NM = NM;
    }

    public String getAGE() {
        return AGE;
    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(String WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getBIRTH() {
        return BIRTH;
    }

    public void setBIRTH(String BIRTH) {
        this.BIRTH = BIRTH;
    }

    public String getKIND() {
        return KIND;
    }

    public void setKIND(String KIND) {
        this.KIND = KIND;
    }

    public int getFLAG() {
        return FLAG;
    }

    public void setFLAG(int FLAG) {
        this.FLAG = FLAG;
    }

    public String getINFORM() {
        return INFORM;
    }

    public void setINFORM(String INFORM) {
        this.INFORM = INFORM;
    }
}
