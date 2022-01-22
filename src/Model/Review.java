package Model;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Review {
    private String reviewID;
    private String piID;
    private String userID;
    private String reviewEscrita;
    private float classificacao;
    private int nrGostos;
    private LocalDateTime date;
    public ReentrantLock lock;

    public Review(String reviewID, String userID,String piID, String reviewEscrita, float classificacao, int nrGostos) {
        this.reviewID = reviewID;
        this.piID = piID;
        this.userID = userID;
        this.reviewEscrita = reviewEscrita;
        this.classificacao = classificacao;
        this.nrGostos = nrGostos;
        this.lock = new ReentrantLock();
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPiID() {
        return piID;
    }

    public void setPiID(String piID) {
        this.piID = piID;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getReviewEscrita() {
        return reviewEscrita;
    }

    public void setReviewEscrita(String reviewEscrita) {
        this.reviewEscrita = reviewEscrita;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getNrGostos() {
        return nrGostos;
    }

    public void setNrGostos(int nrGostos) {
        this.nrGostos = nrGostos;
    }

    public void incrementaGosto(){
        try {
            this.lock.lock();
            this.nrGostos ++;
        } finally {
            this.lock.unlock();
        }
    }

    public void decrementaGosto(){
        try {
            this.lock.lock();
            this.nrGostos --;
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID='" + reviewID + '\'' +
                ", piID='" + piID + '\'' +
                ", userID='" + userID + '\'' +
                ", reviewEscrita='" + reviewEscrita + '\'' +
                ", classificacao=" + classificacao +
                ", nrGostos=" + nrGostos +
                ", date='" + date + '\'' +
                '}';
    }
}
