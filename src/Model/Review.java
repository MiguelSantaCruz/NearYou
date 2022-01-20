package Model;

import java.util.concurrent.locks.ReentrantLock;

public class Review {
    private String reviewID;
    private String userID;
    private String reviewEscrita;
    private int classificacao;
    private int nrGostos;
    public ReentrantLock lock;

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

    public int getClassificacao() {
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
}
