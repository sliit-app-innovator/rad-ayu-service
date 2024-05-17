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

    public static final String GET_ALL_STOCK_TRANSFER = "SELECT * FROM stock_transfer";
    public static final String SEARCH_STOCK_TRANSFER_BY_REQUESTED_BY_QUERY = "SELECT * FROM stock_transfer WHERE requested_by like %:requestBy%";
    public static final String SEARCH_STOCK_TRANSFER_BY_STORE_ID_QUERY = "SELECT * FROM stock_transfer WHERE to_store =:storeId" ;
    public static final String SEARCH_STOCK_TRANSFER_BY_STORE_ID_AND_REQUESTED_BY_QUERY = "SELECT * FROM stock_transfer WHERE to_store =:storeId OR requested_by like %:requestBy%";

    public static final String GET_ALL_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID = "SELECT * FROM stock_requisition_item where stock_requisition_id = :requisitionId";
    public static final String GET_ALL_STOCK_REQUISITION_ITEMS_BY_ID = "SELECT * FROM stock_requisition_item where id = :id";
    public static final String GET_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID_AND_MEDICINE_ID = "SELECT * FROM stock_requisition_item where stock_requisition_id = :requisitionId AND medicine_id =:medicineId LIMIT 1";
    public static final String DELETE_ALL_STOCK_REQUISITION_ITEMS_BY_REQUISITION_ID = "DELETE FROM stock_requisition_item where stock_requisition_id = :requisitionId";

    public static final String GET_ALL_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID = "SELECT * FROM stock_transfer_item where stock_transfer_id = :transferId";
    public static final String GET_ALL_STOCK_TRANSFER_ITEMS_BY_ID = "SELECT * FROM stock_transfer_item where id = :id";
    public static final String GET_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID_AND_MEDICINE_ID = "SELECT * FROM stock_transfer_item where stock_transfer_id = :transferId AND medicine_id =:medicineId LIMIT 1";
    public static final String DELETE_ALL_STOCK_TRANSFER_ITEMS_BY_TRANSFER_ID = "DELETE FROM stock_transfer_item where stock_transfer_id = :transferId";

    public static final String SEARCH_WARD_BY_WARD_NUMBER_QUERY = "SELECT * FROM ward WHERE ward_number = :wardNumber";

    public static final String SEARCH_WARD_BY_WARD_TYPE_QUERY = "SELECT * FROM ward WHERE type_id = :typeId";
    public static final String SEARCH_WARD_BY_DESCRIPTION_QUERY = "SELECT * FROM ward WHERE description  like %:description%";
    public static final String SEARCH_LOTS_BY_STORE_MEDICINE_QUERY = "SELECT s.lot_id ,store.name , sum(s.in_qty) - sum(s.out_qty) AS quantity , m.expire_date, m.lot_num,m.created_date AS purchased_date FROM medicine_movement s INNER JOIN  medicine_lot m ON s.lot_id=m.id INNER JOIN store ON s.store_id = store.id WHERE (:storeId IS NULL OR :storeId = '' OR s.store_id = :storeId)   AND s.medicine_id=:medicineId GROUP BY s.store_id , s.lot_id  having quantity > 0 order by m.expire_date desc";
    public static final String SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY = "SELECT  m.id , m.name , m.code,  m.reorder_level , m.medicine_type , mt.name AS medicine_type_name , m.unit ,u.unit AS unitName ,\n" +
            "  get_stock_by_item(m.id,:storeId)  as quantity  FROM medicine AS  m  \n" +
            " INNER JOIN unit u ON m.unit=u.id INNER JOIN medicine_type mt ON m.medicine_type=mt.id \n" +
            "WHERE m.name LIKE %:search%  order by m.name LIMIT :limit OFFSET :skip";
    public static final String SEARCH_MEDICINE_STOCK_BY_STORE_MEDICINE_NAME_QUERY_COUNT = "SELECT  count(*) AS count  FROM medicine AS  m   INNER JOIN unit u ON m.unit=u.id INNER JOIN medicine_type mt ON m.medicine_type=mt.id  WHERE m.name LIKE %:search%  " ;

    public static final String GET_MEDICINE_STOCK_BY_MEDICINE_ID_WITH_STORE="select s.id,s.name,get_stock_by_item(:medicineId,s.id) as stock from store s where get_stock_by_item(:medicineId,s.id) > 0";
    public static final String GET_TOP_5_FAST_MOVING_MEDICINCES="SELECT medicine_id , (select name from medicine where id=md.medicine_id) as name,sum(qty) as issued FROM medicine_issue_details md group by medicine_id order by sum(qty) desc limit 5;";
    public static final String GET_TOP_5_EXPIRING_MEDICINES="SELECT medicine_id, (select name from medicine where id=medicine_movement.medicine_id) as name , count(out_qty) as expired FROM medicine_movement  where description='STOCK_EXPIRED' GROUP BY medicine_id order by count(out_qty) desc limit 5";
    public static final String GET_MEDICINE_MOVEMENT="CALL get_medicines_movement_by_id(:medicineId)";
    public static final String GET_STOCK_REQS_BY_STATUS="SELECT count(*) as total , (select count(*) from stock_requisition where status_id=0) as pending FROM stock_requisition;";
    public static final String GET_RE_ORDER_LEVELS="select count(id) as pending_reorder,(select count(*) from medicine ) as total_items  from medicine where get_stock_by_item(id,null) < reorder_level";
    public static final String GET_REQUESTS_BY_ID="SELECT * FROM stock_requisition where status_id = :id";

}
