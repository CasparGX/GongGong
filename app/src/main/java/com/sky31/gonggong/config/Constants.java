package com.sky31.gonggong.config;

/**
 * Created by root on 16-2-28.
 */
public class Constants {

    public final class Api {

        public static final String URL = "http://api.sky31.com";

        public static final String STU_INFO = "edu_student_info.php";
        public static final String COURSE = "edu_course.php";
        public static final String GRADE_REPORT = "edu_grade_report.php";
        public static final String GRADE_DETAILS = "edu_grade_details.php";
        public static final String TERM_CODE = "edu_term_code.php";
        public static final String RANK = "edu_rank.php";

        public static final String ECARD = "ecard_balance.php";

        public static final String SEARCH_MATE = "search_mate.php";

        public static final String LIBRARY_READER_INFO = "library_reader_info.php";
        public static final String LIBRARY_RENT_LIST = "library_rent_list.php";
        public static final String LIBRARY_RENEW = "library_renew.php";


        public static final String SWZL_URL = "http://swzl.sky31.com/API";
        public static final String SWZL_ACTION = "lost_found.php";

    }

    public final class Key {
        public static final String ROLE = "role";
        public static final String HASH = "hash";
        public static final String SID = "sid";
        public static final String PASSWORD = "password";

        public static final String NAME = "name";
        public static final String SEX = "sex";
        public static final String DATE_OF_BIRTH = "date_of_birth";
        public static final String LOCATION = "location";
        public static final String COLLEGE = "college";
        public static final String MAJOR = "major";
        public static final String CLASSX = "classx";


    }

    public final class Value {
        public static final String ROLE = "test";
        public static final String HASH = "test";


        public static final int RESULT_LOGIN = 1;

        //
        //失物招领
        public static final String SWZL_ASK_METHOD_JSON = "json";
        public static final String SWZL_SUBMIT_LOST = "submit_lost";
        public static final String SWZL_SUBMIT_FOUND = "submit_found";
        public static final String SWZL_SEARCH_LOST = "search_lost";
        public static final String SWZL_SEARCH_FOUND = "search_found";

    }
}
