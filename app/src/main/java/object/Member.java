package object;

import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private String id;
    private String password;
    private String nickname;
    private String tel;
    private int flag;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Member(String name, String id, String password, String nickname, String tel) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
