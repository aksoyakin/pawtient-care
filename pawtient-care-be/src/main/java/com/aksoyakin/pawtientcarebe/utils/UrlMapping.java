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

    //PHOTOS
    public static final String PHOTOS = API + VERSION + "/photos";
    public static final String UPLOAD_PHOTO = "/photo/upload";
    public static final String UPDATE_PHOTO = "/photo/{photoId}/update";
    public static final String DELETE_PHOTO = "/photo/{photoId}/user/{userId}/delete";
    public static final String GET_PHOTO_BY_ID = "/photo/{photoId}/get";

    //REVIEWS
    public static final String REVIEWS = API + VERSION + "/reviews";
    public static final String SUBMIT_REVIEW = "/submit-review";
    public static final String GET_USER_REVIEWS = "/user/{userId}/reviews";
    public static final String UPDATE_REVIEW = "/review/{reviewId}/update";
    public static final String DELETE_REVIEW = "review/{reviewId}/delete";
    public static final String GET_AVERAGE_RATING = "/veterinarian/{veterinarianId}/average-rating";

    //VETERINARIANS
    public static final String VETERINARIANS = API + VERSION + "/veterinarians";
    public static final String GET_ALL_VETERINARIANS = "/get-all-veterinarians";
    public static final String SEARCH_VETERINARIAN_FOR_APPOINTMENT = "/search-veterinarian";
}