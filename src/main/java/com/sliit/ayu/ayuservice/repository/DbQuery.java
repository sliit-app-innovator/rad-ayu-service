package com.sliit.ayu.ayuservice.repository;

public class DbQuery {

    // USERS QUERIES
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String SEARCH_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE first_name like %:firstName% OR last_name like %:lastName%";
    public static final String SEARCH_STORE_BY_NAME_QUERY = "SELECT * FROM store WHERE name like %:name% ";

    public static final String GET_ALL_MEDICINE = "SELECT * FROM medicine";
    public static final String GET_ALL_MEDICINE_TYPE = "SELECT * FROM medicine_type";
    public static final String SEARCH_MEDICINE_BY_NAME_QUERY = "SELECT * FROM medicine WHERE name like %:name%";
    public static final String SEARCH_MEDICINE_TYPE_BY_NAME_QUERY = "SELECT * FROM medicine_type WHERE name like %:name%";

    public static final String GET_ALL_STOCK_REQUISITION = "SELECT * FROM stock_requisition";
    public static final String SEARCH_STOCK_REQUISITION_BY_REQUESTED_BY_QUERY = "SELECT * FROM stock_requisition WHERE requested_by like %:requestBy%";
    public static final String SEARCH_STOCK_REQUISITION_BY_STORE_ID_QUERY = "SELECT * FROM stock_requisition WHERE to_store =:storeId" ;
    public static final String SEARCH_STOCK_REQUISITION_BY_STORE_ID_AND_REQUESTED_BY_QUERY = "SELECT * FROM stock_requisition WHERE to_store =:storeId OR requested_by like %:requestBy%";

    public static final String SEARCH_WARD_BY_WARD_NUMBER_QUERY = "SELECT * FROM ward WHERE ward_number = :wardNumber";

    public static final String SEARCH_WARD_BY_WARD_TYPE_QUERY = "SELECT * FROM ward WHERE type_id = :typeId";
}
