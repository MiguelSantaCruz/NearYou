package Model;

import java.util.List;

public class Utilizador {
    private String userID;
    private String userName;
    private String email;
    private List<String> PIGuardados;
    private List<String> Reviews;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPIGuardados() {
        return PIGuardados;
    }

    public void setPIGuardados(List<String> PIGuardados) {
        this.PIGuardados = PIGuardados;
    }

    public List<String> getReviews() {
        return Reviews;
    }

    public void setReviews(List<String> reviews) {
        Reviews = reviews;
    }
}
