package Model;

import java.util.concurrent.locks.ReentrantLock;

public class ReportedReview {
        private int id;
        private int userID;
        private int reviewID;
        private int numberOfReports;
        public ReentrantLock lock;

        public ReportedReview(int idu,int id,int num){
            userID = idu;
            reviewID = id;
            numberOfReports = num;
        }

        public ReportedReview(int id, int utilizador_id, int rev_id, int numReports) {
            id = id;
            userID = utilizador_id;
            reviewID = rev_id;
            numberOfReports = numReports;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getNumberOfReports() {
            return numberOfReports;
        }

        public void setNumberOfReports(int numberOfReports) {
            this.numberOfReports = numberOfReports;
        }

        public int getReviewID() {
            return reviewID;
        }

        public void setReviewID(int reviewID) {
            this.reviewID = reviewID;
        }

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