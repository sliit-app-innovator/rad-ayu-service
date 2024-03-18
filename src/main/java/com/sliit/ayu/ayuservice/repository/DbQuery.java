package com.sliit.ayu.ayuservice.repository;

public class DbQuery {

    // USERS QUERIES
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String SEARCH_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE first_name like %:firstName% OR last_name like %:lastName%";
    public static final String SEARCH_STORE_BY_NAME_QUERY = "SELECT * FROM store WHERE name like %:name% ";

    public static final String GET_ALL_MEDICINE = "SELECT * FROM medicine";
    public static final String SEARCH_MEDICINE_BY_NAME_QUERY = "SELECT * FROM medicine WHERE name like %:name%";

}
