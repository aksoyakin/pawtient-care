package com.aksoyakin.pawtientcarebe.utils;

public class UrlMapping {
    //API AND VERSION
    public static final String API = "/api";
    public static final String VERSION = "/v1";
    //USER
    public static final String USERS = API + VERSION + "/users";
    public static final String REGISTER_USER = "/register";
    public static final String UPDATE_USER = "/update/{userId}";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String GET_ALL_USERS = "/all-users";
}
