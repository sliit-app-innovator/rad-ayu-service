package com.sliit.ayu.ayuservice.repository;

public class DbQuery {

    // USERS QUERIES
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String SEARCH_USER_BY_NAME_QUERY = "SELECT * FROM user WHERE first_name like %:firstName% OR last_name like %:lastName%";
    public static final String SEARCH_STORE_BY_NAME_QUERY = "SELECT * FROM store WHERE name like %:name% ";

    public static final String GET_ALL_MEDICINE = "SELECT * FROM medicine";
    public static final String GET_ALL_MEDICINE_TYPE = "SELECT * FROM medicine_type";
    public static final String SEARCH_MEDICINE_BY_NAME_QUERY = "SELECT * FROM medicine WHERE name like %:name%";
    public static final String SEARCH_MEDICINE_BY_NAME_OR_CODE_PAGING = "SELECT m.id, m.name, m.code, m.type, m.medicine_type, m.unit, u.unit as uname, m.created_date, m.updated_date, m.is_expire , m.reorder_level  FROM  medicine m INNER JOIN unit u ON m.unit=u.id WHERE LOWER(m.name) LIKE LOWER(concat('%', :search, '%')) OR LOWER(m.code) LIKE LOWER(concat('%', :search, '%')) LIMIT :limit OFFSET :skip";
    public static final String SEARCH_MEDICINE_BY_NAME_OR_CODE_COUNT = "SELECT count(*) as count FROM  medicine m WHERE LOWER(m.name) LIKE LOWER(concat('%', :search, '%')) OR LOWER(m.code) LIKE LOWER(concat('%', :search, '%'))";
    public static final String SEARCH_MEDICINE_TYPE_BY_NAME_QUERY = "SELECT * FROM medicine_type WHERE name like %:name%";

    public static final String GET_ALL_STOCK_REQUISITION = "SELECT * FROM stock_requisition";
    public static final String SEARCH_STOCK_REQUISITION_BY_REQUESTED_BY_QUERY = "SELECT * FROM stock_requisition WHERE requested_by like %:requestBy%";
    public static final String SEARCH_STOCK_REQUISITION_BY_STORE_ID_QUERY = "SELECT * FROM stock_requisition WHERE to_store =:storeId" ;
    public static final String SEARCH_STOCK_REQUISITION_BY_STORE_ID_AND_REQUESTED_BY_QUERY = "SELECT * FROM stock_requisition WHERE to_store =:storeId OR requested_by like %:requestBy%";

    public static final String GET_ALL_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID = "SELECT * FROM stock_requisition_item where stock_requisition_id = :requisitionId";
    public static final String GET_ALL_STOCK_REQUISITION_ITEMS_BY_ID = "SELECT * FROM stock_requisition_item where id = :id";
    public static final String GET_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID_AND_MEDICINE_ID = "SELECT * FROM stock_requisition_item where stock_requisition_id = :requisitionId AND medicine_id =:medicineId LIMIT 1";
    public static final String DELETE_ALL_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID = "DELETE FROM stock_requisition_item where stock_requisition_id = :requisitionId";

    public static final String SEARCH_WARD_BY_WARD_NUMBER_QUERY = "SELECT * FROM ward WHERE ward_number = :wardNumber";

    public static final String SEARCH_WARD_BY_WARD_TYPE_QUERY = "SELECT * FROM ward WHERE type_id = :typeId";
    public static final String SEARCH_WARD_BY_DESCRIPTION_QUERY = "SELECT * FROM ward WHERE description  like %:description%";
    public static final String SEARCH_LOTS_BY_STORE_MEDICINE_QUERY = "SELECT s.lot_id , sum(s.in_qty) - sum(s.out_qty) AS quantity , m.expire_date, m.lot_num,m.created_date AS purchased_date FROM medicine_movement s INNER JOIN  medicine_lot m ON s.lot_id=m.id WHERE (:storeId IS NULL OR :storeId = '' OR s.store_id = :storeId)   AND s.medicine_id=:medicineId GROUP BY s.lot_id having quantity > 0 order by m.expire_date desc";
    public static final String SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY = "SELECT  m.id , m.name , m.code,  m.reorder_level , m.medicine_type , mt.name AS medicine_type_name , " +
            "m.unit ,u.unit AS unitName , ifnull(sum(s.in_qty) - sum(s.out_qty),0) AS quantity   " +
            "FROM medicine AS  m  LEFT JOIN medicine_movement s ON s.medicine_id=m.id  " +
            "INNER JOIN unit u ON m.unit=u.id " +
            "INNER JOIN medicine_type mt ON m.medicine_type=mt.id  " +
            "WHERE (:storeId IS NULL OR :storeId = '' OR s.store_id = :storeId) " +
            "GROUP BY s.medicine_id HAVING name LIKE %:search%  " +
            "order by m.name LIMIT :limit OFFSET :skip";
    public static final String SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY_COUNT = "SELECT  count(*) AS count  " +
            "FROM medicine AS  m  LEFT JOIN medicine_movement s ON s.medicine_id=m.id  " +
            "INNER JOIN unit u ON m.unit=u.id " +
            "INNER JOIN medicine_type mt ON m.medicine_type=mt.id  " +
            "WHERE (:storeId IS NULL OR :storeId = '' OR s.store_id = :storeId) AND m.name LIKE %:search%  " ;
}
