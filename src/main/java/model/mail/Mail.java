package model.mail;

public class Mail {

    private String from;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String subject;
    private String messageBody;

    public Mail(){}

    public String getFrom(){
        return from;
    }

    public void setFrom(String from){
        this.from = from;
    }

    public String[] getTo(){
        return to;
    }

    public void setTo(String[] to){
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getMessageBody(){
        return messageBody;
    }

    public void setMessageBody(String body){
        this.messageBody = body;
    }
}
