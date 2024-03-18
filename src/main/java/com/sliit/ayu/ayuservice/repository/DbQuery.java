package com.sliit.ayu.ayuservice.repository;

public class DbQuery {

    // USERS QUERIES
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String SEARCH_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE first_name like %:firstName% OR last_name like %:lastName%";

    public static final String SEARCH_WARD_BY_WARD_NUMBER_QUERY = "SELECT * FROM ward WHERE ward_number = :wardNumber";

    public static final String SEARCH_WARD_BY_WARD_TYPE_QUERY = "SELECT * FROM ward WHERE type_id = :typeId";
}
