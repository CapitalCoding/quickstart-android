package br.com.dbccompany.sicred.domain.model;

public class CheckIn {
    String eventId;
    String name;
    String email;


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public CheckIn(String eventId, String name, String email) {
        this.eventId = eventId;
        this.name = name;
        this.email = email;
    }

    public CheckIn() {
    }
}
