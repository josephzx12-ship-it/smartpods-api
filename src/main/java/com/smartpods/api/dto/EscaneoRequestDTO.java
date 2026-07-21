package com.smartpods.api.dto;

public class EscaneoRequestDTO {
    private String qrData;

    public EscaneoRequestDTO() {}

    public String getQrData() { return qrData; }
    public void setQrData(String qrData) { this.qrData = qrData; }
}