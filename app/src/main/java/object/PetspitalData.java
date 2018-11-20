package object;

import java.io.Serializable;

public class PetspitalData implements Serializable {

    String ID;//번호
    String NM;//사업장명
    String ADDR_OLD;//구 주소
    String ADDR;// 도로명 주소
    String PERMISSION_DT;//인허가 일자
    String STATE;//영업상태명
    String STOP_DT;// 폐업일자
    String SUSPENSION_START_DT;// 휴업시작일자
    String SUSPENSION_END_DT;// 휴업종료일자
    String RE_OPEN_DT;// 재개업일자
    String AREA;//소재지 면적
    String POST;//소재지우편번호
    String TOTAL_EMPLOY;
    String SFRMPRD_TYPE;
    String LSIND_TYPE;
    String TEL;//전화번호
    double XCODE;//위치정보 X
    double YCODE;// 위치정보 Y
    String PERMISSION_NO;
    String DETAIL_STAT_NM;

    public PetspitalData() {

    }

    public PetspitalData(String NM) {
        this.NM = NM;
    }

    public PetspitalData(String ID, String NM, String ADDR_OLD, String ADDR, String PERMISSION_DT, String STATE, String STOP_DT, String SUSPENSION_START_DT, String SUSPENSION_END_DT, String RE_OPEN_DT, String AREA, String POST, String TOTAL_EMPLOY, String SFRMPRD_TYPE, String LSIND_TYPE, String TEL, double XCODE, double YCODE, String PERMISSION_NO, String DETAIL_STAT_NM) {
        this.ID = ID;
        this.NM = NM;
        this.ADDR_OLD = ADDR_OLD;
        this.ADDR = ADDR;
        this.PERMISSION_DT = PERMISSION_DT;
        this.STATE = STATE;
        this.STOP_DT = STOP_DT;
        this.SUSPENSION_START_DT = SUSPENSION_START_DT;
        this.SUSPENSION_END_DT = SUSPENSION_END_DT;
        this.RE_OPEN_DT = RE_OPEN_DT;
        this.AREA = AREA;
        this.POST = POST;
        this.TOTAL_EMPLOY = TOTAL_EMPLOY;
        this.SFRMPRD_TYPE = SFRMPRD_TYPE;
        this.LSIND_TYPE = LSIND_TYPE;
        this.TEL = TEL;
        this.XCODE = XCODE;
        this.YCODE = YCODE;
        this.PERMISSION_NO = PERMISSION_NO;
        this.DETAIL_STAT_NM = DETAIL_STAT_NM;
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

    public String getADDR_OLD() {
        return ADDR_OLD;
    }

    public void setADDR_OLD(String ADDR_OLD) {
        this.ADDR_OLD = ADDR_OLD;
    }

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getPERMISSION_DT() {
        return PERMISSION_DT;
    }

    public void setPERMISSION_DT(String PERMISSION_DT) {
        this.PERMISSION_DT = PERMISSION_DT;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTOP_DT() {
        return STOP_DT;
    }

    public void setSTOP_DT(String STOP_DT) {
        this.STOP_DT = STOP_DT;
    }

    public String getSUSPENSION_START_DT() {
        return SUSPENSION_START_DT;
    }

    public void setSUSPENSION_START_DT(String SUSPENSION_START_DT) {
        this.SUSPENSION_START_DT = SUSPENSION_START_DT;
    }

    public String getSUSPENSION_END_DT() {
        return SUSPENSION_END_DT;
    }

    public void setSUSPENSION_END_DT(String SUSPENSION_END_DT) {
        this.SUSPENSION_END_DT = SUSPENSION_END_DT;
    }

    public String getRE_OPEN_DT() {
        return RE_OPEN_DT;
    }

    public void setRE_OPEN_DT(String RE_OPEN_DT) {
        this.RE_OPEN_DT = RE_OPEN_DT;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getPOST() {
        return POST;
    }

    public void setPOST(String POST) {
        this.POST = POST;
    }

    public String getTOTAL_EMPLOY() {
        return TOTAL_EMPLOY;
    }

    public void setTOTAL_EMPLOY(String TOTAL_EMPLOY) {
        this.TOTAL_EMPLOY = TOTAL_EMPLOY;
    }

    public String getSFRMPRD_TYPE() {
        return SFRMPRD_TYPE;
    }

    public void setSFRMPRD_TYPE(String SFRMPRD_TYPE) {
        this.SFRMPRD_TYPE = SFRMPRD_TYPE;
    }

    public String getLSIND_TYPE() {
        return LSIND_TYPE;
    }

    public void setLSIND_TYPE(String LSIND_TYPE) {
        this.LSIND_TYPE = LSIND_TYPE;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public double getXCODE() {
        return XCODE;
    }

    public void setXCODE(double XCODE) {
        this.XCODE = XCODE;
    }

    public double getYCODE() {
        return YCODE;
    }

    public void setYCODE(double YCODE) {
        this.YCODE = YCODE;
    }

    public String getPERMISSION_NO() {
        return PERMISSION_NO;
    }

    public void setPERMISSION_NO(String PERMISSION_NO) {
        this.PERMISSION_NO = PERMISSION_NO;
    }

    public String getDETAIL_STAT_NM() {
        return DETAIL_STAT_NM;
    }

    public void setDETAIL_STAT_NM(String DETAIL_STAT_NM) {
        this.DETAIL_STAT_NM = DETAIL_STAT_NM;
    }

    @Override
    public String toString() {
        return "PetspitalData{" +
                "ID='" + ID + '\'' +
                ", NM='" + NM + '\'' +
                ", ADDR_OLD='" + ADDR_OLD + '\'' +
                ", ADDR='" + ADDR + '\'' +
                ", STATE='" + STATE + '\'' +
                ", TEL='" + TEL + '\'' +
                ", XCODE=" + XCODE +
                ", YCODE=" + YCODE +
                ", PERMISSION_NO='" + PERMISSION_NO + '\'' +
                '}';
    }
}
