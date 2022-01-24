package Model;

import java.util.ArrayList;
import java.util.List;

public class Utilizador {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private int permissao;
    private boolean bloqueado;
    private List<String> piGuardados;
    private List<String> reviews;
    private List<String> reviewsGostadas;

    public Utilizador(){
        this.userID = -1;
        this.userName = "NaN";
        this.email = "NaN";
        this.password = "NaN";
        this.permissao = 1;
        this.bloqueado = false;
        this.piGuardados = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.reviewsGostadas = new ArrayList<>();
    }

    public Utilizador(int userID, String userName, String email, String password, int permissao) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.permissao = permissao;
        this.bloqueado = false;
        this.piGuardados = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.reviewsGostadas = new ArrayList<>();
    }

    public Utilizador(int userID, String userName, String email, String password, int permissao, boolean bloqueado) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.permissao = permissao;
        this.bloqueado = bloqueado;
        this.piGuardados = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.reviewsGostadas = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissao() {
        return permissao;
    }

    public void setPermissao(int permissao) {
        this.permissao = permissao;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public List<String> getpiGuardados() {
        return piGuardados;
    }

    public void setpiGuardados(List<String> PIGuardados) {
        this.piGuardados = PIGuardados;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        reviews = reviews;
    }

    public List<String> getReviewsGostadas() {
        return reviewsGostadas;
    }

    public void setReviewsGostadas(List<String> reviewsGostadas) {
        this.reviewsGostadas = reviewsGostadas;
    }

    public void addReviewGostada(String reviewID){
        this.reviewsGostadas.add(reviewID);
    }

    public void removeReviewGostada(String reviewID){
        this.reviewsGostadas.remove(reviewID);
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", permissao=" + permissao +
                ", bloqueado=" + bloqueado +
                ", piGuardados=" + piGuardados +
                ", reviews=" + reviews +
                ", reviewsGostadas=" + reviewsGostadas +
                '}';
    }
}
