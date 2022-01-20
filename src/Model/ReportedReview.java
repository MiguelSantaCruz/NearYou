package Model;

import java.util.concurrent.locks.ReentrantLock;

public class ReportedReview {
    private String reviewID;
    private int numberOfReports;
    public ReentrantLock lock;

    public void addReport(){
        try {
            this.lock.lock();
            this.numberOfReports++;
        } finally {
            this.lock.unlock();
        }
    }

    public void removeReport(){
        try {
            this.lock.lock();
            this.numberOfReports--;
        } finally {
            this.lock.unlock();
        }
    }
}
