package com.msg91.sendotp.sample;

public class Cheque3 {

    private String image;
    private String status;

    private String user1;
    private String user2;

    private String user3;


    public Cheque3(String image, String status, String user1, String user2, String user3) {

        this.image = image;
        this.status = status;

        this.user1=user1;
        this.user2=user2;
        this.user3=user3;

    }

    public Cheque3() {
    }


    public String getImage() {
        return image;
    }
    public String getStatus() {
        return status;
    }

    public String getUser1() {
        return user1;
    }
    public String getUser2() {
        return user2;
    }
    public String getUser3() {
        return user3;
    }

}




//   public String getPrize() { return  prize; }}
