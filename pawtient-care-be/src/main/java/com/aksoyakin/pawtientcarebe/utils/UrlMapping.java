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

    //APPOINTMENTS
    public static final String APPOINTMENTS = API + VERSION + "/appointments";
    public static final String GET_APPOINTMENTS = "/all";
    public static final String BOOK_APPOINTMENT = "/book-appointment";
    public static final String GET_APPOINTMENT_BY_ID = "/appointment/{id}/get";
    public static final String DELETE_APPOINTMENT_BY_ID = "appointment/{id}/delete";
    public static final String GET_APPOINTMENT_BY_NO = "/appointment/{appointmentNo}/appointment";
    public static final String UPDATE_APPOINTMENT_BY_ID = "appointment/{id}/update";

    //PETS
    public static final String PETS = API + VERSION + "/pets";
    public static final String SAVE_PET = "/save-pet";
    public static final String GET_PET_BY_ID = "/pet/{petId}/get";
    public static final String DELETE_PET_BY_ID = "/pet/{petId}/delete";
    public static final String UPDATE_PET_BY_ID = "/pet/{petId}/update";
}