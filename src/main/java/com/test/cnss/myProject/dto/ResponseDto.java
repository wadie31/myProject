package com.test.cnss.myProject.dto;

public class ResponseDto {
    
    private String accessToken;
    
    public ResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
}
