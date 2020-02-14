package com.msg91.sendotp.sample;

public class Cheque1 {

    private String image;
    private String status;
    private String user;
    private String prize;

    private String des;
    private String ph;
    private String ph1;

    public Cheque1(String image, String status, String user, String prize,String des,String ph,String ph1) {

        this.image = image;
        this.status = status;
        this.user=user;
        this.prize=prize;
        this.des=des;
        this.ph=ph;
        this.ph1=ph1;
    }

    public Cheque1() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }
    public String getUser() {
        return user;
    }
    public String getPrize() { return  prize; }
    public String getDes()
    {
        return des;
    }
    public String getph() { return  ph; }

    public String getph1() { return  ph1; }

}
