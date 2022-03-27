package com.jiankun.community.entity;

import java.util.Date;

public class LoginTicket {

    private int id;
    private int UserId;
    private String tickets;
    private int status;
    private Date expired;

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", UserId=" + UserId +
                ", tickets='" + tickets + '\'' +
                ", status=" + status +
                ", expired=" + expired +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
