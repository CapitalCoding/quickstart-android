package br.com.dbccompany.sicred.domain.model;

import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

public class Event {
    @Nullable
    List<People> people;
    Date date;
    String description;
    String id;
    String image;
    double latitude;
    double longitude;
    double price;
    String title;
    String contentToBeShare;

    @Nullable
    public List<People> getPeople() {
        return people;
    }

    public void setPeople(@Nullable List<People> people) {
        this.people = people;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentToBeShare() {
        return contentToBeShare;
    }

    public void setContentToBeShare(String contentToBeShare) {
        this.contentToBeShare = contentToBeShare;
    }

    public Event(@Nullable List<People> people, Date date, String description, String id, String image, double latitude, double longitude, double price, String title, String contentToBeShare) {
        this.people = people;
        this.date = date;
        this.description = description;
        this.id = id;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.title = title;
        this.contentToBeShare = contentToBeShare;
    }
}
