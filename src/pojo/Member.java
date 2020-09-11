package pojo;

public class Member {
    int status;
    String member_Code;
    String name;
    String phone;
    Float balance;
    String password;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMember_Code() {
        return member_Code;
    }

    public void setMember_Code(String member_Code) {
        this.member_Code = member_Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "status=" + status +
                ", memberCode='" + member_Code + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", balance='" + balance + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
