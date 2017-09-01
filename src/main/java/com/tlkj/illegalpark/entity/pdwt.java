package com.tlkj.illegalpark.entity;

public class pdwt {
    private Integer id;

    private String authid;

    private String platformip;

    private String deviceaccessid;

    private String ballmachinemainip;

    private String subnetmask;

    private String gateway;

    private String entrynumber;

    private String publiclocus;

    private String installlocation;

    private String lat;

    private String lng;

    private String accessway;

    private String note;

    private String pac;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthid() {
        return authid;
    }

    public void setAuthid(String authid) {
        this.authid = authid == null ? null : authid.trim();
    }

    public String getPlatformip() {
        return platformip;
    }

    public void setPlatformip(String platformip) {
        this.platformip = platformip == null ? null : platformip.trim();
    }

    public String getDeviceaccessid() {
        return deviceaccessid;
    }

    public void setDeviceaccessid(String deviceaccessid) {
        this.deviceaccessid = deviceaccessid == null ? null : deviceaccessid.trim();
    }

    public String getBallmachinemainip() {
        return ballmachinemainip;
    }

    public void setBallmachinemainip(String ballmachinemainip) {
        this.ballmachinemainip = ballmachinemainip == null ? null : ballmachinemainip.trim();
    }

    public String getSubnetmask() {
        return subnetmask;
    }

    public void setSubnetmask(String subnetmask) {
        this.subnetmask = subnetmask == null ? null : subnetmask.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getEntrynumber() {
        return entrynumber;
    }

    public void setEntrynumber(String entrynumber) {
        this.entrynumber = entrynumber == null ? null : entrynumber.trim();
    }

    public String getPubliclocus() {
        return publiclocus;
    }

    public void setPubliclocus(String publiclocus) {
        this.publiclocus = publiclocus == null ? null : publiclocus.trim();
    }

    public String getInstalllocation() {
        return installlocation;
    }

    public void setInstalllocation(String installlocation) {
        this.installlocation = installlocation == null ? null : installlocation.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getAccessway() {
        return accessway;
    }

    public void setAccessway(String accessway) {
        this.accessway = accessway == null ? null : accessway.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPac() {
        return pac;
    }

    public void setPac(String pac) {
        this.pac = pac == null ? null : pac.trim();
    }
}