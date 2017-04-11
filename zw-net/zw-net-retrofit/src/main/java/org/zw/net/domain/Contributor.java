package org.zw.net.domain;

/**
 * Created by zw on 2017/4/3.
 */
public class Contributor {
    private String login;
    private double id;
    private String avatar_url;
    private double gravatar_id;
    private String url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public double getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(double gravatar_id) {
        this.gravatar_id = gravatar_id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id=" + gravatar_id +
                ", url='" + url + '\'' +
                '}';
    }

}
