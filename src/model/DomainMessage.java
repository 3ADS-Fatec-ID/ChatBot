package model;

public class DomainMessage {

    public String body;
    public int progressId;

    public DomainMessage(String body, int progressId) {
        this.body = body;
        this.progressId = progressId;
    }

    public DomainMessage() {
    }

    @Override
    public String toString() {
        return "DomainMessage{" + "body=" + body + ", progressId=" + progressId + '}';
    }
}
