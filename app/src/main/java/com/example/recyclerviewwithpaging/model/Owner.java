package com.example.recyclerviewwithpaging.model;

public class Owner {
    private String profile_image;

    private String user_type;

    private String user_id;

    private String link;

    private String reputation;

    private String display_name;

    private String accept_rate;

    public Owner(String profile_image, String user_type, String user_id, String link, String reputation, String display_name, String accept_rate) {
        this.profile_image = profile_image;
        this.user_type = user_type;
        this.user_id = user_id;
        this.link = link;
        this.reputation = reputation;
        this.display_name = display_name;
        this.accept_rate = accept_rate;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getAccept_rate() {
        return accept_rate;
    }

    public void setAccept_rate(String accept_rate) {
        this.accept_rate = accept_rate;
    }
}
