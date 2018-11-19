package object;

import java.io.Serializable;

public class PetData implements Serializable {

    String ID;
    String NM;
    String AGE;
    String WEIGHT;
    String BIRTH;
    String INFORM;

    public PetData(String NM) {
        this.NM = NM;
    }

    public PetData(String ID, String NM, String AGE, String WEIGHT, String BIRTH, String INFORM) {
        this.ID = ID;
        this.NM = NM;
        this.AGE = AGE;
        this.WEIGHT = WEIGHT;
        this.BIRTH = BIRTH;
        this.INFORM = INFORM;
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

    public String getINFORM() {
        return INFORM;
    }

    public void setINFORM(String INFORM) {
        this.INFORM = INFORM;
    }
}
