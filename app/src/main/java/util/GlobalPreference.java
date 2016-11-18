//package util;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.preference.PreferenceManager;
//import android.text.TextUtils;
//
//import com.CouponChart.R;
//import com.CouponChart.constant.CooChaConstants;
//import com.CouponChart.constant.FilterConstants;
//import com.CouponChart.fragment.FilterAreaFragment;
//import com.CouponChart.listener.OnFilterChangedListener;
//import com.CouponChart.util.CommonDataManager;
//import com.CouponChart.util.DateUtils;
//
//import java.util.Date;
//
//
//public class GlobalPreference {
//    public static SharedPreferences prefs = null;
//
//	/* 쿠차페이 시작 */
//
//    public static final String PREF_PAY_SHIPPING = "pref_pay_shipping";
//    public static final String PREF_PAY_TOKEN = "pref_pay_token";
//    public static final String PREF_PAY_STATUS_JSON = "pref_pay_status_json";
//
//	/* 쿠차페이 끝 */
//
//    public static final String PREF_PREV_KEYWORD = "pref_prev_keyword";
//    public static final String PREF_NOTICE_LATEST_BCID = "pref_notice_latest_bcid";
//    public static final String PREF_APPLICATION_ID = "pref_application_id";
//    public static final String PREF_SETUP_GCM_ALLOW = "pref_setup_gcm_allow";
//    public static final String PREF_SETUP_GCM_REGISTRATION_ID = "pref_setup_gcm_registration_id";
//    public static final String PREF_SEEN_PRODUCT_SORT = "pref_seen_product_sort";
//    public static final String PREF_USER_EMAIL = "pref_user_email";
//    public static final String PREF_USER_PHONE = "pref_user_phone";
//    public static final String PREF_USER_SMS_AGREE = "pref_user_sms_agree";
//    public static final String PREF_USER_EMAIL_AGREE = "pref_user_email_agree";
//    public static final String PREF_USER_TOKEN = "pref_user_token";
//    public static final String PREF_USER_ENCODED_MID = "pref_user_encoded_mid";
//    public static final String PREF_USER_LOGIN_TYPE = "pref_user_login_type";
//    public static final String PREF_USER_MID = "pref_user_mid";
//    public static final String PREF_AUTO_LOGIN = "pref_auto_login";
//    public static final String PREF_VILLAGE_DISTANCE = "pref_village_distance";
//    public static final String PREF_VILLAGE_DISTANCE_TEXT = "pref_village_distance_text";     // jay.lee 추가 (내동네 거리필터 name)
//    public static final String PREF_VILLAGE_POSITION = "pref_village_position";
//    public static final String PREF_CRASH_LOG = "pref_crash_log";
//    public static final String PREF_AGREE_RUNNING_GPS = "pref_agree_running_gps";
//    public static final String PREF_CURRENT_LATITUDE = "pref_current_latitude";
//    public static final String PREF_CURRENT_LONGITUDE = "pref_current_longitude";
//    public static final String PREF_MODIFY_JJIM_DATA = "pref_modify_jjim_date";
//    public static final String PREF_VILLAGE_CATEGORY_INFO = "pref_village_cate_info";
//    public static final String PREF_VILLAGE_AREA_INFO = "pref_village_area_info";
//    public static final String PREF_VILLAGE_SORT_PARAM = "pref_village_sort_param";   // jay.lee 추가 (내동네 정렬 필터)
//    public static final String PREF_VILLAGE_SORT_TEXT = "pref_village_sort_text";   // jay.lee 추가 (내동네 정렬 필터)
//
//    public static final String PREF_CATEGORY_UPDATE = "pref_category_update";
//    private static final String PREF_BRAND_UPDATE = "pref_brand_update";
//    public static final String PREF_LOCAL_UPDATE = "pref_local_update";
//    public static final String PREF_SORT_UPDATE = "pref_sort_update";
//    public static final String PREF_HOME_SHOP_UPDATE = "pref_home_shop_update";
//    public static final String PREF_SHOP_UPDATE = "pref_shop_update";
//    public static final String PREF_MALL_UPDATE = "pref_mall_update";
//    public static final String DEFAULT_DATE = "20140101000000";
//    public static final String PREF_LIST_SORT = "pref_list_sort";
////	public static final String PREF_SHOPPING_OPTION = "pref_shopping_option";
//
//    private static final String PREF_SORT_PARAM = "pref_sort_param";
//    private static final String PREF_OPTION_PARAM = "pref_option_param";
//    private static final String PREF_OPTION_PARAM_LABEL = "pref_option_param_label";
//    private static final String PREF_SHOP_PARAM = "pref_shop_param";
//    private static final String PREF_SEARCH_SHOP_PARAM = "pref_search_shop_param";
//    private static final String PREF_SHOP_SELECTED_PARAM = "pref_shop_selected_param";
//    private static final String PREF_SEARCH_SHOP_SELECTED_PARAM = "pref_search_shop_selected_param";
//    //	private static final String PREF_SEARCH_SHOP_PARAM = "pref_search_shop_param";
//    private static final String PREF_TEMP_ONE_SHOP_PARAM = "pref_temp_one_shop_param";
//    //	private static final String PREF_SEARCH_TEMP_ONE_SHOP_PARAM = "pref_search_temp_one_shop_param";
//    private static final String PREF_BABY_AGE_PARAM = "pref_baby_age_param";
//
//    private static final String PREF_OPTION_MINIMUM_PRICE = "pref_option_minimum_price";
//    private static final String PREF_OPTION_MAXIMUM_PRICE = "pref_option_maximum_price";
//    private static final String PREF_SEARCH_OPTION_MAXIMUM_PRICE = "pref_search_option_maximum_price";
//
//    // 4.31 추가
//    private static final String PREF_OPTION_MIN_MAX_PRICE = "pref_option_min_max_price";
//    private static final String PREF_SEARCH_OPTION_MIN_MAX_PRICE = "pref_search_option_min_max_price";
//
//    private static final String PREF_SORT_TEXT = "pref_sort_txt";
//
//    private static final String PREF_SEARCH_SORT_PARAM = "pref_search_sort_param";
//    private static final String PREF_SEARCH_OPTION_PARAM = "pref_search_option_param";
//    private static final String PREF_SEARCH_OPTION_PARAM_LABEL = "pref_search_option_param_label";
//    private static final String PREF_SEARCH_SORT_TEXT = "pref_search_sort_txt";
//
//    /* 지역설정 필터 관련 */
//    private static final String PREF_LOCATION_TYPE = "pref_location_type";
//    private static final String PREF_SELECTED_ANAME = "pref_selected_aname";
//    private static final String PREF_SELECTED_AIDS = "pref_selected_aids";
//    //	private static final String PREF_TEMP_SELECTED_ANAME = "pref_temp_selected_aname";
////	private static final String PREF_TEMP_SELECTED_AIDS = "pref_temp_selected_aids";
//    private static final String PREF_SELECTED_LATITUDE = "pref_selected_latitude";
//    private static final String PREF_SELECTED_LONGITUDE = "pref_selected_longitude";
//
//    //	private static final String PREF_SELECTED_CNAME = "pref_selected_cname";
//    private static final String PREF_SELECTED_CATEGORY_ID = "pref_selected_category_id";
//    private static final String PREF_SEARCH_SELECTED_CATEGORY_ID = "pref_search_selected_category_id";
//    private static final String PREF_MYTOWN_SELECTED_CATEGORY_ID = "pref_mytown_selected_category_id";
//    private static final String PREF_SELECTED_CATEGORY_NAME = "pref_selected_category_name";
//    private static final String PREF_SEARCH_SELECTED_CATEGORY_NAME = "pref_search_selected_category_name";
//    private static final String PREF_MYTOWN_SELECTED_CATEGORY_NAME = "pref_mytown_selected_category_name";
//	/* 지역설정 필터 관련 끝 */
//
//    /* 지역설정 홈 */
//    private static final String PREF_SELECTED_HOME_ANAME = "pref_selected_home_aname";
//    private static final String PREF_SELECTED_HOME_AIDS = "pref_selected_home_aids";
//
//    /* 지역설정 쇼핑 */
//    private static final String PREF_SELECTED_SHOPPING_ANAME = "pref_selected_shopping_aname";
//    private static final String PREF_SELECTED_SHOPPING_AIDS = "pref_selected_shopping_aids";
//
//    /* 지역설정 맛집 */
//    private static final String PREF_SELECTED_RESTAURANT_ANAME = "pref_selected_res_aname";
//    private static final String PREF_SELECTED_RESTAURANT_AIDS = "pref_selected_res_aids";
//
//    /* 지역설정 뷰티 */
//    private static final String PREF_SELECTED_BEAUTY_ANAME = "pref_selected_beauty_aname";
//    private static final String PREF_SELECTED_BEAUTY_AIDS = "pref_selected_beauty_aids";
//
//    /* 지역설정 여행 */
//    private static final String PREF_SELECTED_TRAVAL_ANAME = "pref_selected_traval_aname";
//    private static final String PREF_SELECTED_TRAVAL_AIDS = "pref_selected_traval_aids";
//
//    /* 지역설정 문화 */
//    private static final String PREF_SELECTED_CULTURE_ANAME = "pref_selected_culture_aname";
//    private static final String PREF_SELECTED_CULTURE_AIDS = "pref_selected_culture_aids";
//
//    /* 메인 마지막 탭 설정*/
////	private static final String PREF_LAST_SELECTED_TAB_TWODEPTH_POSITION = "pref_last_selected_tab_twodepth_position";
////	private static final String PREF_LAST_SELECTED_TAB_THREEDEPTH_POSITION = "pref_last_selected_tab_threedepth_position";
////	private static final String PREF_LAST_SELECTED_TAB_FOURDEPTH_POSITION = "pref_last_selected_tab_fourdepth_position";
//    private static final String PREF_FIRST_APP_START = "pref_first_app_start";
//
//    /*해스오퍼스 관련 설정*/
//    private static final String PREF_FIRST_USE_HASOFFERS = "pref_first_use_hasoffers";
//    private static final String PREF_ACCEPT_USING_HASOFFERS = "pref_accept_using_hasoffers";
//
//    /*최근검색어 관련 설정*/
//    private static final String PREF_ADD_RECENT_SEARCH_KEYWORD = "pref_add_recent_search_keyword";
//
//    /* 스플래시 이미지 버전 및 경로 설정 */
//    private static final String PREF_IMAGE_SPLASH_VERSION = "pref_image_splash_version";
//    private static final String PREF_IMAGE_TOP_OLD_PATH = "pref_image_top_path";        // splash old
//    private static final String PREF_IMAGE_CENTER_OLD_PATH = "pref_image_center_path";    // splash old
//    private static final String PREF_IMAGE_BOTTOM_OLD_PATH = "pref_image_bottom_path";    // splash old
//    private static final String PREF_IMAGE_TOP_PATH = "pref_image_spalsh_top_path";
//    private static final String PREF_IMAGE_CENTER_PATH = "pref_image_spalsh_center_path";
//    private static final String PREF_IMAGE_BOTTOM_PATH = "pref_image_spalsh_bottom_path";
//
//    /* 랜딩 이미지 경로 설정 */
//    private static final String PREF_IMAGE_LANDING_PATH = "pref_image_landing_path";
//    private static final String PREF_IMAGE_LANDING_VERSION = "pref_image_landing_version";
//
//    /* 웹뷰 관련 설정 */
//    private static final String PREF_WEBVIEW_CLOSE_DESC = "pref_webview_close_desc";
//    private static final String PREF_WEBVIEW_ENABLE = "pref_webview_enable";
//
//    /* 여행 출발일 시작 끝 관련 설정 */
//    private static final String PREF_DEPARTURE_START_DATE = "pref_departure_start_date";
//    private static final String PREF_DEPARTURE_END_DATE = "pref_departure_end_date";
//    private static final String PREF_DEPARTURE_SELECT_DATE = "pref_departure_select_date";
//
//    private static final String PREF_MYTOWN_FILTER = "pref_mytown_filter";
//    private static final String PREF_MYTOWN_MAP_FILTER = "pref_mytown_map_filter";      // jay.lee 추가 (내동네 지도 필터)
//    private static final String PREF_SEARCH_FILTER = "pref_search_filter";
//
//    /* 하단 메세지 */
//    private static final String PREF_FOOTER_MSG = "pref_footer_msg";
//
//    /* 가이드 띄위줄지 여부 */
//    private static final String PREF_IS_SHOW_GUIDE = "pref_is_show_guide";
//    private static final String PREF_IS_SHOW_GUIDE_PURCHASE = "pref_is_show_guide_purchase";
//
//    /* 슬라이딩 메세지 띄위줄지 여부 */
//    private static final String PREF_IS_SHOW_SLIDING_MSG_GUIDE = "pref_is_show_sliding_msg_guide";
//
//    /* 구매내역 메세지 띄위줄지 여부 */
//    private static final String PREF_IS_SHOW_PURCHASE_MSG_GUIDE = "pref_is_show_purchase_msg_guide";
//
//    /* 자동로그인 메세지 띄워줄지 여부 */
//    private static final String PREF_IS_SHOW_AUTO_LOGIN_MSG_GUIDE = "pref_is_show_auto_login_msg_guide";
//
//    /* 검색시 쓰는 temp */
//    public static final String PREF_TEMP = "pref_temp";
//
//    /* 내동네에서 쓰는 temp */
//    public static final String PREF_MYTOWN_TEMP = "pref_mytown_temp";
//
//    public static final String PREF_USER_AGENT = "pref_user_agent";
//
//    /* 강제로 재시작 설정 */
//    public static final String PREF_FORCE_UPDATE = "pref_force_update";
//
//    /* 사용자 닉네임 */
//    public static final String PREF_NICK_NAME = "key_nickName";
//
//    public static final String PREF_IS_EXSIT_FILTER = "pref_is_exsit_filter";
//    public static final String PREF_IS_EXSIT_OTHER_FILTER = "pref_is_exsit_other_filter";
//    public static final String PREF_IS_EXSIT_POPULAR_CATEGORY = "pref_is_exsit_popular_category";
//    public static final String PREF_IS_FIRST_FILTER_AREA_START = "pref_is_first_filter_area_start";
//
//    public static final String PREF_IS_SHOW_SLIDE_GUIDE_1 = "pref_is_show_slide_guide_1";
//    public static final String PREF_IS_SHOW_SLIDE_GUIDE_2 = "pref_is_show_slide_guide_2";
//
//    /* 플로팅 팝업 종료시간 */
//    public static final String PREF_FLOATING_CLOSE_TIME = "pref_floating_close_time";
//
//    /* 시작 팝업 종료시간 */
//    public static final String PREF_STARTING_CLOSE_TIME = "pref_starting_close_time";
//
//    private static final String PREF_FILTER_UPDATE = "pref_filter_update";
//    private static final String PREF_ACCOUNT_LINK_INFO_UPDATE = "pref_account_link_info_update";
//    private static final String PREF_CPS_STYLESHOP_UPDATE = "pref_cps_styleshop_update";
//
//    private static final String PREF_RELATION_PRODUCT_YN = "pref_relation_product_yn";
//
//    /* 구글 aid 저장 */
//    private static final String PREF_GOOGLE_AID = "pref_google_aid";
//
//    private static final String PREF_TEST_MDID = "pref_test_mdid";
//
//    // 이전 사용자를 위해서 변수 변경 불가
//    private static final String PREF_RECOBELL_LONGCLICK_GUIDE = "pref_recobell_longclick_guide";
//    private static final String PREF_PERFORM_COUNT = PREF_RECOBELL_LONGCLICK_GUIDE;
//
//    private static final String PREF_FLITER_AREA_GUIDE = "pref_filter_area_guide";
//
//    private static final String PREF_CAULY_BANNER_POSITION = "pref_cauly_banner_position";
//    private static final String PREF_CAULY_BANNER_APP_CODE = "pref_cauly_banner_app_code";
//
//    private static final String PREF_COUPON_LIST_POSITION = "pref_coupon_list_position";
//
//    private static final String PREF_USE_11ST = "pref_use_11st";
//    private static final String PREF_USE_BB_PROJECT = "pref_use_bb_project";
//
//    private static final String PREF_USE_SEARCH_TAB = "pref_use_search_tab";
//
//    private static final String PREF_USE_TWO_ARRAY_LIST = "pref_use_two_array_list";
//    private static final String PREF_USE_MALL_LINK_TWO_ARRAY_LIST = "pref_use_mall_link_two_array_list";
//    private static final String PREF_USE_WEBVIEW_CLOSE_BUTTON = "pref_use_webview_close_button";
//    private static final String PREF_ENABLE_WEBVIEW_CLOSE_BUTTON = "pref_enable_webview_close_button";
//    private static final String PREF_IS_SHOW_WEBVIEW_CLOSE_BUTTON_GUIDE = "pref_is_show_webview_close_button_guide";
//    private static final String PREF_WEBVIEW_CLOSE_BUTTON_TOP = "pref_webview_close_button_top";
//    private static final String PREF_WEBVIEW_CLOSE_BUTTON_LEFT = "pref_webview_close_button_left";
//
//    private static final String PREF_LANDING_TIME = "pref_landing_time";
//
//    private static final String PREF_GMARKET_COOKIE = "pref_gmarket_cookie";
//
//    private static final String PREF_BEST_TUTORIAL = "pref_best_tutorial";
//
//    /* 구매내역 필터 */
//    private static final String PREF_PURCHASE_SHOP_FILTER = "pref_purchase_shop_filter";
//    private static final String PREF_PURCHASE_TYPE_FILTER = "pref_purchase_type_filter";
//    private static final String PREF_PURCHASE_ORDER_STATE_FILTER = "pref_purchase_order_state_filter";
//    private static final String PREF_PURCHASE_ORDER_PERIOD_FILTER = "pref_purchase_order_period_filter";
//
//    /* 구매내역 관련 */
//    private static final String PREF_IS_SHOW_CONNECT_SHOP_DIALOG = "is_show_connect_shop_dialog";
//    private static final String PREF_SEARCH_IN_RESULT_KEYWORD = "pref_search_in_result_keword";
//    private static final String PREF_IS_SHOW_AUTO_LOGIN_SHOP_DIALOG = "is_show_auto_login_shop_dialog";
//
//    private static final String PREF_SHOW_CONNECT_SHOP_DIALOG_COUNT = "pref_show_connect_shop_dialog_count";
//    private static final String PREF_SHOW_CONNECT_SHOP_DIALOG_DATE = "pref_show_connect_shop_dialog_date";
//    private static final String PREF_SHOW_CONNECT_SHOP_DIALOG_LIMIT = "pref_show_connect_shop_dialog_limit";
//
//    /* 배송조회 관련 */
//    private static final String PREF_ENABLE_CRAWLING_ONLY_WIFI = "enable_crawling_only_wifi";
//    private static final String PREF_IS_SHOWN_PURCHASE_REFRESH_GUIDE_COUNT = "pref_is_shown_purchase_refresh_guide_count";
//    private static final String PREF_DEFAULT_PERIOD_CHANGED = "default_period_changed";
//
//    private static final String PREF_KEY_IS_SHOW_FIRST_PURCHASE_LIST = "pref_key_is_show_first_purchase_list";
//    private static final String PREF_IMAGE_ISP_PATH = "pref_image_isp_path";
//    private static final String PREF_ISP_NOTICE_VERSION = "pref_isp_notice_version";
//
//    public static final String PREF_WEBVIEW_DEAL_HISTORY = "pref_webview_deal_history";
//    public static final String PREF_WEBVIEW_DEAL_HISTORY_TOOLTIP = "pref_webview_deal_history_tooltip";
//
//    public static final String PREF_SILDING_MENU_ALL = "pref_silding_menu_all";
//    public static final String PREF_IS_USE_PURCHASE = "pref_is_use_purchase";
//
//    public static final String PREF_RECOBEL_PUSH_UUID = "pref_recobell_push_uuid";
//    public static final String PREF_IS_AGREE_PURCHASE_TERMS = "pref_is_agree_purchase_terms";
//
//    public static final String PREF_IS_BROWSER_HISTORY_APPEND = "pref_is_browser_history_append";
//    public static final String PREF_SHIPPING_ERROR_LOG_USE_YN = "pref_shipping_error_log_use_yn";
//    public static final String PREF_PURCHASE_ALERT_MSG = "pref_purchase_alert_msg";
//
//    public static final String PREF_WEBVIEW_POPUP_GUIDE = "pref_webview_popup_guide";
//    public static final String PREF_WEBVIEW_USE_COUNT = "pref_webview_use_count";
//    public static final String PREF_WEBVIEW_CLOSE_POPUP_GUIDE = "pref_webview_close_popup_guide";
//
//    public static final String PREF_SEARCH_POPUP_GUIDE = "pref_search_popup_guide";
//
//    public static final String PREF_APP_VERSION = "pref_app_version";
//
//    public static final String PREF_KEY_IS_SHOW_GUIDE_COMPARE_PRICE_GUIDE = "show_guide_compare_price_guide";
//
//    //    public static final String PREF_IS_SHOW_PERSONAL_RECOMMEND_DIALOG = "is_show_personal_recommend_dialog";              // v4.30 에서 미사용 처리(실제 value 설정 여부로 확인) @author uno.kim
//    public static final String PREF_KEY_PERSONAL_RECOMMEND_SETTING = "personal_recommend_setting_";
//    public static final String PREF_PERSONAL_RECOMMEND_DIALOG_SHOW_DATE = "personal_recommend_dialog_show_date";
//
//    public static final String PREF_USE_WEB_FOOTER = "pref_use_web_footer";
//    public static final String PREF_APP_FIRST_INSTALL = "pref_app_first_install";
//
//    public static final String PREF_PERSONAL_RECOMMEND_USEYN = "pref_personal_recommend_useyn";
//
//    /* 검색결과 최저가 핫딜  탭에 대한 N아이콘 노출 유무 */
//    public static final String PREF_IS_SHOW_SEARCH_RESULT_LOWEST_N = "pref_is_show_search_result_lowest_n";
//
//    public static final String PREF_NEW_SEARCH_RESULT = "pref_new_search_result";
//    public static final String PREF_NEW_SEARCH_RESULT_HISTORY = "pref_new_search_result_history";
//    public static final String PREF_NEW_SEARCH_RESULT_SHOP_FILTER = "pref_new_search_result_shop_filter";
//    public static final String PREF_NEW_SEARCH_KEYWORD = "pref_new_search_keyword";
//    public static final String PREF_NEW_SEARCH_KEYWORD_LABEL = "pref_new_search_keyword_label";
//    public static final String PREF_NEW_SEARCH_KEYWORD_ID = "pref_default_search_id";
//
//    public static final String PREF_WEBVIEW_BOTTOM_LIST_YN = "pref_webview_bottom_list_yn";
//
//    public static final String PREF_MORE_MENU_LIST_VO = "pref_more_menu_list_vo";
//
//    /* style shop UI관련 값 */
//    public static final String PREF_STATUS_BAR_HEIGHT = "pref_status_bar_height";
//    public static final String PREF_DISPLAY_WIDTH = "pref_display_width";
//    public static final String PREF_DISPLAY_HEIGHT = "pref_display_height";
//    //    public static final String PREF_STYLE_SHOP_ROW_COUNT = "pref_style_shop_row_count_4_35";      // 4.35 부터 새로운 변수로 사용한다.
//    public static final String PREF_STYLE_SHOP_ROW_COUNT = "pref_style_shop_row_count_4_xx";        // TODO 스타일# 개편 업데이트시 버전 네임 변경 필요 @author uno.kim
//    public static final String PREF_STYLE_SHOP_ROW_SQUARE_HEIGHT = "pref_style_shop_row_square_height";
//    public static final String PREF_STYLE_SHOP_ROW_FIT_HEIGHT = "pref_style_shop_row_fit_height";
//    public static final String PREF_STYLE_SHOP_ROW_IS_SQUARE = "pref_style_shop_row_is_square";
//    public static final String PREF_STYLE_SHOP_OFFSET = "pref_style_shop_offset";
//
//    public static final String PREF_STYLE_SHOP_ROW_RECTANGLE_WIDTH = "pref_style_shop_row_rectangle_width";       // 카테고리, 검색화면에서 추가된 스타일# row의 너비 값
//    public static final String PREF_STYLE_SHOP_ROW_RECTANGLE_HEIGHT = "pref_style_shop_row_ractangle_height";     // 카테고리, 검색화면에서 추가된 스타일# row의 높이 값
//
//    public static final String PREF_MALL_LINK_NEW_GENERATION = "pref_mall_link_new_generation";   // 몰링크 신규 화면구조 적용 플래그
//    public static final String PREF_PUSH_ALARM_OPEN_YN = "pref_push_alarm_open_yn";   // 푸쉬수신동의 알람 팝업 오픈여부 플래그
//
//    public static final String PREF_KEYWORD_HOTCLICK_AB = "pref_keyword_hotclick_ab";   // 검색 핫클릭 배경컬러 AB테스트
//
//    public static final String PREF_STYLE_SHOP_SALE_NEW_ITEM_UPDATE_DATE = "pref_style_shop_sale_new_item_update_date";     // 스타일# sale 새로운 아이템 갱신일 저장
//
//   //- 오늘의 싸다구 / MD Pick
//    public static final String PREF_SSADAGU_MDPICK_PUSH_ENABLE = "pref_ssadagu_mdpick_push_enable"; // 오늘의 싸다구 / MD Pick 푸쉬 동의 여부
//    public static final String PREF_SSADAGU_BUBBLE_SHOW = "pref_ssadagu_bubble_show";
//    public static final String PREF_HOT_BUBBLE_SHOW = "pref_hot_bubble_show";
//
//    public static final String PREF_JJIM_DID_LIST = "pref_jjim_did_list";                           // 찜 목록 did
//
//    // 베스트 텝 개편
//    // 베스트 카테고리 클릭로그 DB 삭제를 위한 값
//    public static final String PREF_BEST_CATE_CLICK_LOG_DELETE_DAY = "pref_best_cate_click_log_delete_day";
//
//
//
//    public static final String PREF_PUSH_RECEIVE_LOG_URL = "pref_push_receive_log_url_4_74";             // 푸시 도달로그 적재 URL
//    public static final String PREF_PUSH_OPEN_LOG_URL = "pref_push_open_log_url_4_74";                   // 푸시 오픈로그 적재 URL
//
//    public static final String PREF_COOCHA_PUSH_ARRIVAL_LOG_URL = "pref_coocha_push_arrival_log_url_4_74";             // 쿠차푸시 도달로그 적재 URL
//    public static final String PREF_COOCHA_PUSH_OPEN_LOG_URL = "pref_coocha_push_open_log_url_4_74";                   // 쿠차푸시 오픈로그 적재 URL
//
//    // 필터 개편시 추가
//    private static final String PREF_FILTER_SHOP_LABEL = "pref_filter_shop_label";                                          // 업체필터 라벨
//    private static final String PREF_FILTER_SHOP_SEARCH_LABEL = "pref_filter_shop_search_label";                            // 검색영역 업체필터 라벨
//    private static final String PREF_FILTER_DELIVERY_OPTION = "pref_filter_delivery_option";                                // 배송옵션
//    private static final String PREF_FILTER_DELIVERY_OPTION_LABEL = "pref_filter_delivery_option_label";                    // 배송옵션 라벨
//    private static final String PREF_FILTER_SEARCH_DELIVERY_OPTION = "pref_filter_search_delivery_option";                  // 배송옵션
//    private static final String PREF_FILTER_SEARCH_DELIVERY_OPTION_LABEL = "pref_filter_search_delivery_option_label";      // 배송옵션 라벨
//    private static final String PREF_FILTER_PRICE_LABEL = "pref_filter_price_label";                                        // 가격옵션필터 라벨
//    private static final String PREF_FILTER_SEARCH_PRICE_LABEL = "pref_filter_search_price_label";                          // 검색영역 가격옵션필터 라벨
//    private static final String PREF_FILTER_KEYWORD = "pref_filter_keyword";                                                // 결과내 검색
//    private static final String PREF_FILTER_KEYWORD_LABEL = "pref_filter_keyword_label";                                    // 결과내 검색 라벨
//    private static final String PREF_FILTER_SEARCH_KEYWORD = "pref_filter_search_keyword";                                  // 검색화면 결과내 검색
//    private static final String PREF_FILTER_SEARCH_KEYWORD_LABEL = "pref_filter_search_keyword_label";                      // 검색화면 결과내 검색 라벨
//
//    private static final String PREF_IS_BOTTOMBAR_MARKET_OPEN = "pref_is_bottombar_market_open";                            // 하단바에 중고장터 표시 노출 여부
//
//    private static final String PREF_FILTER_MENU_HEIGHT = "pref_filter_menu_height";                                        // 필터 메뉴 높이
//
//    private static final String PREF_FILTER_CHANGED_DELAY = "pref_filter_changed_delay";                                    // 필터 변경시 기본 딜레이 타임
//
//    // 쿠차 푸시 모듈 사용 유무 (v4.70)
//    private static final String PREF_COOCHA_PUSH_LOG_YN = "pref_coocha_push_log_yn";
//
//    private static final String PREF_ACCOUNTS_BACKUP_VERSION = "pref_accounts_backup_version";                              // 자동로그인 계정정보 백업 데이터 버전
//    private static final String PREF_IS_SHOW_GUIDE_STYLE_SHOP = "pref_is_show_guide_style_shop";                            // 스타일# 메인 가이드 노출 여부
//
//    private static final String PREF_SEEN_PRODUCT_UPDATE_DATE = "pref_seen_product_update_date";                            // 내가 본 상품 업데이트 일자
//    private static final String PREF_SEEN_PRODUCT_LIST = "pref_seen_product_list";                                          // 내가 본 상품 리스트 JSONArray
//
//    private static final String PREF_STYLE_SHOP_CPS_NPAY_URL = "pref_style_shop_cps_npay_url";                              // 스타일샵 cps npay url
//
//    private static final String PREF_SEEN_PRODUCT_REFRESH_TIME = "pref_seen_product_refresh_time";
//    private static final String PREF_SEARCH_SEEN_PRODUCT_YN = "pref_search_seen_product_yn";
//    private static final String PREF_MENU_MANAGEMENT_YN = "pref_menu_management_yn";
//    private static final String PREF_MENU_MANAGEMENT_TIME = "pref_menu_management_time";
//    private static final String PREF_MENU_MANAGEMENT_IS_NEW = "pref_menu_management_is_new";
//    public static final String PREF_MENU_MANAGEMENT_HOME_MID = "pref_menu_management_home_mid";
//    private static final String PREF_COMPARE_PRICE_VIEW_YN = "pref_compare_price_view_yn";
//
//    /* 쿠차 푸시 수신 동의 관려 값 */
//    // 4.50 이후 변경됨
//    public static final String PREF_COOCHA_NEED_PUSH_AGREE = "pref_coocha_need_push_agree_4.50"; // 쿠차 푸시 수신 동의창을 띄워야 하는지 여부
//    public static final String PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_TIME = "pref_coocha_push_agree_dialog_show_time_4.50"; // 쿠차 푸시 수신 동의창을 띄워준 시각
//    public static final String PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_COUNT = "pref_coocha_push_agree_dialog_show_count_4.50"; // 쿠차 푸시 수신 동의창을 띄워준 횟수
//
//    /* 스타일# 개편 추가 */
//    public static final String PREF_STYLE_SHOP_BOOK_MARK = "pref_style_shop_book_mark";
//    public static final String PREF_STYLE_SHOP_BOOK_MARK_MODIFY = "pref_style_shop_book_mark_modify";
//    public static final String PREF_STYLE_SHOP_CATEGORY_HOT = "pref_style_shop_category_hot";
//
//    public static SharedPreferences getInstance() {
//        if (prefs == null) {
//            prefs = PreferenceManager.getDefaultSharedPreferences(GlobalApplication.getContext());
//        }
//        return prefs;
//    }
//
//    public static String getPREV_KEYWORD() {
//        return getInstance().getString(PREF_PREV_KEYWORD, "");
//    }
//
//    public static void setPREV_KEYWORD(String prev_keyword) {
//        getInstance().edit().putString(PREF_PREV_KEYWORD, prev_keyword).commit();
//    }
//
//    public static int getNOTICE_LATEST_BCID() {
//        return getInstance().getInt(PREF_NOTICE_LATEST_BCID, 0);
//    }
//
//    public static void setNOTICE_LATEST_BCID(int notice_latest_bcid) {
//        getInstance().edit().putInt(PREF_NOTICE_LATEST_BCID, notice_latest_bcid).commit();
//    }
//
//    public static String getAPPLICATION_ID() {
//        return getInstance().getString(PREF_APPLICATION_ID, "");
//    }
//
//    public static void setAPPLICATION_ID(String application_id) {
//        getInstance().edit().putString(PREF_APPLICATION_ID, application_id).commit();
//    }
//
//    public static String getSETUP_GCM_REGISTRATION_ID() {
//        return getInstance().getString(PREF_SETUP_GCM_REGISTRATION_ID, "");
//    }
//
//    public static void setSETUP_GCM_REGISTRATION_ID(String setup_gcm_registration_id) {
//        getInstance().edit().putString(PREF_SETUP_GCM_REGISTRATION_ID, setup_gcm_registration_id).commit();
//    }
//
//    public static boolean getSETUP_GCM_ALLOW() {
//        return getInstance().getBoolean(PREF_SETUP_GCM_ALLOW, true);
//    }
//
//    public static void setSETUP_GCM_ALLOW(boolean setup_gcm_allow) {
//        getInstance().edit().putBoolean(PREF_SETUP_GCM_ALLOW, setup_gcm_allow).commit();
//    }
//
//    public static String getSEEN_PRODUCT_SORT() {
//        return getInstance().getString(PREF_SEEN_PRODUCT_SORT, "");
//    }
//
//    public static void setSEEN_PRODUCT_SORT(String seen_product_sort) {
//        getInstance().edit().putString(PREF_SEEN_PRODUCT_SORT, seen_product_sort).commit();
//    }
//
//    public static String getUSER_ENCODED_MID() {
//        return getInstance().getString(PREF_USER_ENCODED_MID, "");
//    }
//
//    public static void setUSER_ENCODED_MID(String user_encoded_mid) {
//        getInstance().edit().putString(PREF_USER_ENCODED_MID, user_encoded_mid).commit();
//    }
//
//    public static String getUSER_LOGIN_TYPE() {
//
//        String userType = getInstance().getString(PREF_USER_LOGIN_TYPE, CooChaConstants.USER_TYPE_COOCHA);
//
//        if(TextUtils.isEmpty(userType)){
//            userType = CooChaConstants.USER_TYPE_COOCHA;
//        }
//
//        return userType;
//    }
//
//    public static void setUSER_LOGIN_TYPE(String user_login_type) {
//        getInstance().edit().putString(PREF_USER_LOGIN_TYPE, user_login_type).commit();
//    }
//
//
//    public static String getUSER_MID() {
//        return getInstance().getString(PREF_USER_MID, "");
//    }
//
//    public static void setUSER_MID(String user_mid) {
//        getInstance().edit().putString(PREF_USER_MID, user_mid).commit();
//    }
//
//    public static boolean getAUTO_LOGIN() {
//        return getInstance().getBoolean(PREF_AUTO_LOGIN, false);
//    }
//
//    public static void setAUTO_LOGIN(boolean pref_auto_login) {
//        getInstance().edit().putBoolean(PREF_AUTO_LOGIN, pref_auto_login).commit();
//    }
//
//    public static String getUSER_TOKEN() {
//        return getInstance().getString(PREF_USER_TOKEN, "");
//    }
//
//    public static void setUSER_TOKEN(String user_token) {
//        getInstance().edit().putString(PREF_USER_TOKEN, user_token).commit();
//    }
//
//    public static String getCRASH_LOG() {
//        return getInstance().getString(PREF_CRASH_LOG, "");
//    }
//
//    public static void setCRASH_LOG(String crash_log) {
//        getInstance().edit().putString(PREF_CRASH_LOG, crash_log).commit();
//    }
//
//    public static boolean getAGREE_RUNNING_GPS() {
//        return getInstance().getBoolean(PREF_AGREE_RUNNING_GPS, false);
//    }
//
//    public static void setAGREE_RUNNING_GPS(boolean agree_running_gps) {
//        getInstance().edit().putBoolean(PREF_AGREE_RUNNING_GPS, agree_running_gps).commit();
//    }
//
//    public static double getCURRENT_LATITUDE() {
//        double value;
//        try {
//            value = Double.parseDouble(getInstance().getString(PREF_CURRENT_LATITUDE, ""));
//        } catch (NumberFormatException e) {
//            return -1;
//        }
//        return value;
//    }
//
//    public static void setCURRENT_LATITUDE(double current_latitude) {
//        getInstance().edit().putString(PREF_CURRENT_LATITUDE, current_latitude + "").commit();
//    }
//
//    public static double getCURRENT_LONGITUDE() {
//        double value;
//        try {
//            value = Double.parseDouble(getInstance().getString(PREF_CURRENT_LONGITUDE, ""));
//        } catch (NumberFormatException e) {
//            return -1;
//        }
//        return value;
//    }
//
//    public static void setCURRENT_LONGITUDE(double current_longitude) {
//        getInstance().edit().putString(PREF_CURRENT_LONGITUDE, current_longitude + "").commit();
//    }
//
//    public static void setMODIFY_JJIM_DATA(boolean b) {
//        getInstance().edit().putBoolean(PREF_MODIFY_JJIM_DATA, b).commit();
//    }
//
//    public static boolean getMODIFY_JJIM_DATA() {
//        return getInstance().getBoolean(PREF_MODIFY_JJIM_DATA, false);
//    }
//
//    /**
//     * 내동네 셋팅
//     **/
//
//    public static String getVILLAGE_DISTANCE() {
//        return getInstance().getString(PREF_VILLAGE_DISTANCE, FilterConstants.FILTER_DISTANCE_RADIUS_DEFAULT);
//    }
//
//    public static void setVILLAGE_DISTANCE(String village_distance) {
//        getInstance().edit().putString(PREF_VILLAGE_DISTANCE, village_distance).commit();
//    }
//
//    public static String getVILLAGE_DISTANCE_TEXT() {
//        return getInstance().getString(PREF_VILLAGE_DISTANCE_TEXT, "5Km");
//    }
//
//    public static void setVILLAGE_DISTANCE_TEXT(String village_distance_text) {
//        getInstance().edit().putString(PREF_VILLAGE_DISTANCE_TEXT, village_distance_text).commit();
//    }
//
//    public static int getVILLAGE_POSITION() {
//        return getInstance().getInt(PREF_VILLAGE_POSITION, 0);
//    }
//
//    public static void setVILLAGE_POSITION(Integer village_position) {
//        getInstance().edit().putInt(PREF_VILLAGE_POSITION, village_position).commit();
//    }
//
//    public static void setVILLAGE_CATEGORY_INFO(String category_info) {
//        getInstance().edit().putString(PREF_VILLAGE_CATEGORY_INFO, category_info).commit();
//    }
//
//    public static String getVILLAGE_CATEGORY_INFO() {
//        return getInstance().getString(PREF_VILLAGE_CATEGORY_INFO, ",전체");
//    }
//
//    public static void setVILLAGE_AREA_INFO(String area_info) {
//        getInstance().edit().putString(PREF_VILLAGE_AREA_INFO, area_info).commit();
//    }
//
//    public static String getVILLAGE_AREA_INFO() {
//        return getInstance().getString(PREF_VILLAGE_AREA_INFO, ",전체");
//    }
//
//    // jay.lee 추가 (내동네 정렬 필터)
//    public static void setVILLAGE_SORT_PARAM(String sort_param) {
//        getInstance().edit().putString(PREF_VILLAGE_SORT_PARAM, sort_param).commit();
//    }
//
//    public static String getVILLAGE_SORT_PARAM() {
//        return getInstance().getString(PREF_VILLAGE_SORT_PARAM, "");
//    }
//
//    public static void setVILLAGE_SORT_TEXT(String sort_text) {
//        getInstance().edit().putString(PREF_VILLAGE_SORT_TEXT, sort_text).commit();
//    }
//
//    public static String getVILLAGE_SORT_TEXT() {
//        return getInstance().getString(PREF_VILLAGE_SORT_TEXT, "거리순");
//    }
//
//    /**
//     * 내동네 셋팅 끝
//     **/
//
//    public static void setCATEGORY_UPDATE(String data) {
//        getInstance().edit().putString(PREF_CATEGORY_UPDATE, data).commit();
//    }
//
//    public static String getCATEGORY_UPDATE() {
//        return getInstance().getString(PREF_CATEGORY_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setBRAND_UPDATE(String data) {
//        getInstance().edit().putString(PREF_BRAND_UPDATE, data).commit();
//    }
//
//    public static String getBRAND_UPDATE() {
//        return getInstance().getString(PREF_BRAND_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setLOCAL_UPDATE(String data) {
//        getInstance().edit().putString(PREF_LOCAL_UPDATE, data).commit();
//    }
//
//    public static String getLOCAL_UPDATE() {
//        return getInstance().getString(PREF_LOCAL_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setSORT_UPDATE(String data) {
//        getInstance().edit().putString(PREF_SORT_UPDATE, data).commit();
//    }
//
//    public static String getSORT_UPDATE() {
//        return getInstance().getString(PREF_SORT_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setHOME_SHOP_UPDATE(String data) {
//        getInstance().edit().putString(PREF_HOME_SHOP_UPDATE, data).commit();
//    }
//
//    public static String getHOME_SHOP_UPDATE() {
//        return getInstance().getString(PREF_HOME_SHOP_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setSHOP_UPDATE(String data) {
//        getInstance().edit().putString(PREF_SHOP_UPDATE, data).commit();
//    }
//
//    public static String getSHOP_UPDATE() {
//        return getInstance().getString(PREF_SHOP_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setMALL_UPDATE(String data) {
//        getInstance().edit().putString(PREF_MALL_UPDATE, data).commit();
//    }
//
//    public static String getMALL_UPDATE() {
//        return getInstance().getString(PREF_MALL_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setFILTER_UPDATE(String data) {
//        getInstance().edit().putString(PREF_FILTER_UPDATE, data).commit();
//    }
//
//    public static String getFILTER_UPDATE() {
//        return getInstance().getString(PREF_FILTER_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setACCOUNT_LINK_INFO_UPDATE(String data) {
//        getInstance().edit().putString(PREF_ACCOUNT_LINK_INFO_UPDATE, data).commit();
//    }
//
//    public static String getACCOUNT_LINK_INFO_UPDATE() {
//        return getInstance().getString(PREF_ACCOUNT_LINK_INFO_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setCPS_STYPE_SHOP_UPDATE(String data) {
//        getInstance().edit().putString(PREF_CPS_STYLESHOP_UPDATE, data).commit();
//    }
//
//    public static String getCPS_STYPE_SHOP_UPDATE() {
//        return getInstance().getString(PREF_CPS_STYLESHOP_UPDATE, DEFAULT_DATE);
//    }
//
//    public static void setListSort(String data) {
//        getInstance().edit().putString(PREF_LIST_SORT, data).commit();
//    }
//
//    public static String getListSort() {
//        return getInstance().getString(PREF_LIST_SORT, "");
//    }
//
//    public static void setSortParam(String sortParam) {
//        getInstance().edit().putString(PREF_SORT_PARAM, sortParam).commit();
//    }
//
//    public static String getSortParam() {
//        return getInstance().getString(PREF_SORT_PARAM, "1"); // 수정 3(구매순) >
//        // 1(쿠차랭킹순)
//    }
//
//    public static void setOptionParam(String sortParam) {
//        getInstance().edit().putString(PREF_OPTION_PARAM, sortParam).commit();
//    }
//
//    public static String getOptionParam() {
//        return getInstance().getString(PREF_OPTION_PARAM, "");
//    }
//
//    public static void setOptionParamLabel(String optionLabel) {
//        getInstance().edit().putString(PREF_OPTION_PARAM_LABEL, optionLabel).commit();
//    }
//
//    public static String getOptionParamLabel() {
//        return getInstance().getString(PREF_OPTION_PARAM_LABEL, "");
//    }
//
//    public static void setOptionMinimumPrice(String minimumPrice) {
//        getInstance().edit().putString(PREF_OPTION_MINIMUM_PRICE, minimumPrice).commit();
//    }
//
//    public static String getOptionMinimumPrice() {
//        return getInstance().getString(PREF_OPTION_MINIMUM_PRICE, "");
//    }
//
//    public static void setOptionMaximumPrice(String maximumPrice) {
//        getInstance().edit().putString(PREF_OPTION_MAXIMUM_PRICE, maximumPrice).commit();
//    }
//
//    public static String getOptionMaximumPrice() {
//        return getInstance().getString(PREF_OPTION_MAXIMUM_PRICE, "");
//    }
//
//    public static void setSearchOptionMaximumPrice(String maximumPrice) {
//        getInstance().edit().putString(PREF_SEARCH_OPTION_MAXIMUM_PRICE, maximumPrice).commit();
//    }
//
//    public static String getSearchOptionMaximumPrice() {
//        return getInstance().getString(PREF_SEARCH_OPTION_MAXIMUM_PRICE, "");
//    }
//
//    public static void setOptionMinMaxPrice(String minMaxPrice) {
//        getInstance().edit().putString(PREF_OPTION_MIN_MAX_PRICE, minMaxPrice).commit();
//    }
//
//    public static String getOptionMinMaxPrice() {
//        return getInstance().getString(PREF_OPTION_MIN_MAX_PRICE, "0,100000");
//    }
//
//    public static void setSearchOptionMinMaxPrice(String minMaxPrice) {
//        getInstance().edit().putString(PREF_SEARCH_OPTION_MIN_MAX_PRICE, minMaxPrice).commit();
//    }
//
//    public static String getSearchOptionMinMaxPrice() {
//        return getInstance().getString(PREF_SEARCH_OPTION_MIN_MAX_PRICE, "0,100000");
//    }
//
//
//    /*
//        v4.61 이하 에서 저장되는 Shop filter preference
//        선택이 해제된 업체 필터가 저장됩니다
//     */
//
//    public static void setShopParam(String shopParam) {
//        getInstance().edit().putString(PREF_SHOP_PARAM, shopParam).commit();
//    }
//
//    public static String getShopParam() {
//        return getInstance().getString(PREF_SHOP_PARAM, "");
//    }
//
//    public static void setSearchShopParam(String shopParam) {
//        getInstance().edit().putString(PREF_SEARCH_SHOP_PARAM, shopParam).commit();
//    }
//
//    public static String getSearchShopParam() {
//        return getInstance().getString(PREF_SEARCH_SHOP_PARAM, "");
//    }
//
//
//    /*
//        v4.62 필터 개선시 사용되는 preference
//        하위 버전 호환을 위해 새로 추가되었습니다.
//        선택된 업체 필터가 저장됩니다
//     */
//
//    public static void setShopSelectedParam(String shopParam) {
//        getInstance().edit().putString(PREF_SHOP_SELECTED_PARAM, shopParam).commit();
//    }
//
//    public static String getShopSelectedParam() {
//        return getInstance().getString(PREF_SHOP_SELECTED_PARAM, "");
//    }
//
//    public static void setSearchShopSelectedParam(String shopParam) {
//        getInstance().edit().putString(PREF_SEARCH_SHOP_SELECTED_PARAM, shopParam).commit();
//    }
//
//    public static String getSearchShopSelectedParam() {
//        return getInstance().getString(PREF_SEARCH_SHOP_SELECTED_PARAM, "");
//    }
//
//
//    /*
//        filter 개편작업으로 추가된 preference
//     */
//
//    public static void setShopParamLabel(String shopParamLabel) {
//        getInstance().edit().putString(PREF_FILTER_SHOP_LABEL, shopParamLabel).commit();
//    }
//
//    public static String getShopParamLabel() {
//        return getInstance().getString(PREF_FILTER_SHOP_LABEL, "");
//    }
//
//    public static void setSearchShopParamLabel(String shopParamLabel) {
//        getInstance().edit().putString(PREF_FILTER_SHOP_SEARCH_LABEL, shopParamLabel).commit();
//    }
//
//    public static String getSearchShopParamLabel() {
//        return getInstance().getString(PREF_FILTER_SHOP_SEARCH_LABEL, "");
//    }
//
//    public static void setFilterDeliveryOptionParam(String deliveryOption) {
//        getInstance().edit().putString(PREF_FILTER_DELIVERY_OPTION, deliveryOption).commit();
//    }
//
//    public static String getFilterDeliveryOptionParam() {
//        return getInstance().getString(PREF_FILTER_DELIVERY_OPTION, "");
//    }
//
//    public static void setFilterDeliveryOptionParamLabel(String deliveryOptionLabel) {
//        getInstance().edit().putString(PREF_FILTER_DELIVERY_OPTION_LABEL, deliveryOptionLabel).commit();
//    }
//
//    public static String getFilterDeliveryOptionParamLabel() {
//        return getInstance().getString(PREF_FILTER_DELIVERY_OPTION_LABEL, "");
//    }
//
//    public static void setFilterSearchDeliveryOptionParam(String deliveryOption) {
//        getInstance().edit().putString(PREF_FILTER_SEARCH_DELIVERY_OPTION, deliveryOption).commit();
//    }
//
//    public static String getFilterSearchDeliveryOptionParam() {
//        return getInstance().getString(PREF_FILTER_SEARCH_DELIVERY_OPTION, "");
//    }
//
//    public static void setFilterSearchDeliveryOptionParamLabel(String deliveryOptionLabel) {
//        getInstance().edit().putString(PREF_FILTER_SEARCH_DELIVERY_OPTION_LABEL, deliveryOptionLabel).commit();
//    }
//
//    public static String getFilterSearchDeliveryOptionParamLabel() {
//        return getInstance().getString(PREF_FILTER_SEARCH_DELIVERY_OPTION_LABEL, "");
//    }
//
//    public static void setFilterPriceParamLabel(String priceParamLabel) {
//        getInstance().edit().putString(PREF_FILTER_PRICE_LABEL, priceParamLabel).commit();
//    }
//
//    public static String getFilterPriceParamLabel() {
//        return getInstance().getString(PREF_FILTER_PRICE_LABEL, "");
//    }
//
//    public static void setFilterSearchPriceParamLabel(String priceParamLabel) {
//        getInstance().edit().putString(PREF_FILTER_SEARCH_PRICE_LABEL, priceParamLabel).commit();
//    }
//
//    public static String getFilterSearchPriceParamLabel() {
//        return getInstance().getString(PREF_FILTER_SEARCH_PRICE_LABEL, "");
//    }
//
//    public static void setFilterKeywordParam(String keyword) {
//        getInstance().edit().putString(PREF_FILTER_KEYWORD, keyword).commit();
//    }
//
//    public static String getFilterKeywordParam() {
//        return getInstance().getString(PREF_FILTER_KEYWORD, "");
//    }
//
//    public static void setFilterKeywordParamLabel(String keywordLabel) {
//        getInstance().edit().putString(PREF_FILTER_KEYWORD_LABEL, keywordLabel).commit();
//    }
//
//    public static String getFilterKeywordParamLabel() {
//        return getInstance().getString(PREF_FILTER_KEYWORD_LABEL, "");
//    }
//
//    public static void setFilterSearchKeywordParam(String keyword) {
//        getInstance().edit().putString(PREF_FILTER_SEARCH_KEYWORD, keyword).commit();
//    }
//
//    public static String getFilterSearchKeywordParam() {
//        return getInstance().getString(PREF_FILTER_SEARCH_KEYWORD, "");
//    }
//
//    public static void setFilterSearchKeywordParamLabel(String keywordLabel) {
//        getInstance().edit().putString(PREF_FILTER_SEARCH_KEYWORD_LABEL, keywordLabel).commit();
//    }
//
//    public static String getFilterSearchKeywordParamLabel() {
//        return getInstance().getString(PREF_FILTER_SEARCH_KEYWORD_LABEL, "");
//    }
//
//
//
//
//    public static void setBabyAgeParam(String sortText) {
//        getInstance().edit().putString(PREF_BABY_AGE_PARAM, sortText).commit();
//    }
//
//    public static String getBabyAgeParam() {
//        return getInstance().getString(PREF_BABY_AGE_PARAM, "");
//    }
//
//    public static void setSortText(String sortText) {
//        getInstance().edit().putString(PREF_SORT_TEXT, sortText).commit();
//    }
//
//    public static String getSortText() {
//        return getInstance().getString(PREF_SORT_TEXT, FilterConstants.FILTER_FILTER_DEFAULT);
//    }
//
//    public static void setSearchSortParam(String sortParam) {
//        getInstance().edit().putString(PREF_SEARCH_SORT_PARAM, sortParam).commit();
//    }
//
//    public static String getSearchSortParam() {
//        return getInstance().getString(PREF_SEARCH_SORT_PARAM, "1");
//    }
//
//    public static void setSearchOptionParam(String sortParam) {
//        getInstance().edit().putString(PREF_SEARCH_OPTION_PARAM, sortParam).commit();
//    }
//
//    public static String getSearchOptionParam() {
//        return getInstance().getString(PREF_SEARCH_OPTION_PARAM, "");
//    }
//
//    public static void setSearchOptionParamLabel(String optionParamLabel) {
//        getInstance().edit().putString(PREF_SEARCH_OPTION_PARAM_LABEL, optionParamLabel).commit();
//    }
//
//    public static String getSearchOptionParamLabel() {
//        return getInstance().getString(PREF_SEARCH_OPTION_PARAM_LABEL, "");
//    }
//
//    public static void setSearchSortText(String sortText) {
//        getInstance().edit().putString(PREF_SEARCH_SORT_TEXT, sortText).commit();
//    }
//
//    public static String getSearchSortText() {
//        return getInstance().getString(PREF_SEARCH_SORT_TEXT, null);
//    }
//
//    /* 지역설정 관련 */
//    public static void setSelectedAname(String cid, String aname) {
//        getInstance().edit().putString(PREF_SELECTED_ANAME + "_" + cid, aname).commit();
//    }
//
//    public static String getSelectedAname(String cid) {
//        String aname = getInstance().getString(PREF_SELECTED_ANAME + "_" + cid, "모든 지역");
//        return aname;
//    }
//
//    public static void setSelectedAids(String cid, String aids) {
//        getInstance().edit().putString(PREF_SELECTED_AIDS + "_" + cid, aids).commit();
//    }
//
//    public static String getSelectedAids(String cid) {
//        return getInstance().getString(PREF_SELECTED_AIDS + "_" + cid, "");
//    }
//
//    public static void setSelectedLatLon(double lat, double lon) {
//        setSelectedLatLon(String.valueOf(lat), String.valueOf(lon));
//    }
//
//    public static void setSelectedLatLon(String lat, String lon) {
//        getInstance().edit().putString(PREF_SELECTED_LATITUDE, lat).commit();
//        getInstance().edit().putString(PREF_SELECTED_LONGITUDE, lon).commit();
//    }
//
//    public static String[] getSelectedLatLon() {
//        String[] latlon = new String[2];
//        latlon[0] = getInstance().getString(PREF_SELECTED_LATITUDE, "0.0");
//        latlon[1] = getInstance().getString(PREF_SELECTED_LONGITUDE, "0.0");
//        return latlon;
//    }
//
//    public static void setLocationType(String cid, int type) {
//        getInstance().edit().putInt(PREF_LOCATION_TYPE + "_" + cid, type).commit();
//    }
//
//    public static int getLocationType(String cid) {
//        return getInstance().getInt(PREF_LOCATION_TYPE + "_" + cid, FilterAreaFragment.TYPE_AID);
//    }
//	/* 지역설정 관련 끝*/
//
//    /* 카테고리 설정 */
//    public static void setSelectedCategoryId(String cid) {
//        getInstance().edit().putString(PREF_SELECTED_CATEGORY_ID, cid).commit();
//    }
//
//    public static String getSelectedCategoryId() {
//        return getInstance().getString(PREF_SELECTED_CATEGORY_ID, "");
//    }
//
//    public static void setSearchSelectedCategoryId(String cid) {
//        getInstance().edit().putString(PREF_SEARCH_SELECTED_CATEGORY_ID, cid).commit();
//    }
//
//    public static String getSearchSelectedCategoryId() {
//        return getInstance().getString(PREF_SEARCH_SELECTED_CATEGORY_ID, "");
//    }
//
//    public static void setMyTownSelectedCategoryId(String cid) {
//        getInstance().edit().putString(PREF_MYTOWN_SELECTED_CATEGORY_ID, cid).commit();
//    }
//
//    public static String getMyTownSelectedCategoryId() {
//        return getInstance().getString(PREF_MYTOWN_SELECTED_CATEGORY_ID, "");
//    }
//
//    public static void setSelectedCategoryName(String cname) {
//        getInstance().edit().putString(PREF_SELECTED_CATEGORY_NAME, cname).commit();
//    }
//
//    public static String getSelectedCategoryName() {
//        return getInstance().getString(PREF_SELECTED_CATEGORY_NAME, "");
//    }
//
//    public static void setSearchSelectedCategoryName(String cname) {
//        getInstance().edit().putString(PREF_SEARCH_SELECTED_CATEGORY_NAME, cname).commit();
//    }
//
//    public static String getSearchSelectedCategoryName() {
//        return getInstance().getString(PREF_SEARCH_SELECTED_CATEGORY_NAME, "");
//    }
//
//    public static void setMyTownSelectedCategoryName(String cname) {
//        getInstance().edit().putString(PREF_MYTOWN_SELECTED_CATEGORY_NAME, cname).commit();
//    }
//
//    public static String getMyTownSelectedCategoryName() {
//        return getInstance().getString(PREF_MYTOWN_SELECTED_CATEGORY_NAME, "");
//    }
//	/* 카테고리 설정 끝 */
//
//    /* 지역설정 홈 */
//    public static void setSelectedHomeAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_HOME_ANAME, aname).commit();
//    }
//
//    public static String getSelectedHomeAname() {
//        return getInstance().getString(PREF_SELECTED_HOME_ANAME, "");
//    }
//
//    public static void setSelectedHomeAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_HOME_AIDS, aids).commit();
//    }
//
//    public static String getSelectedHomeAids() {
//        return getInstance().getString(PREF_SELECTED_HOME_AIDS, "");
//    }
//
//    /* 지역설정 쇼핑 */
//    public static void setSelectedShoppingAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_SHOPPING_ANAME, aname).commit();
//    }
//
//    public static String getSelectedShoppingAname() {
//        return getInstance().getString(PREF_SELECTED_SHOPPING_ANAME, "");
//    }
//
//    public static void setSelectedShoppingAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_SHOPPING_AIDS, aids).commit();
//    }
//
//    public static String getSelecteShoppingdAids() {
//        return getInstance().getString(PREF_SELECTED_SHOPPING_AIDS, "");
//    }
//
//    /* 지역설정 맛집 */
//    public static void setSelectedRestaurantAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_RESTAURANT_ANAME, aname).commit();
//    }
//
//    public static String getSelectedRestaurantAname() {
//        return getInstance().getString(PREF_SELECTED_RESTAURANT_ANAME, "");
//    }
//
//    public static void setSelectedRestaurantAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_RESTAURANT_AIDS, aids).commit();
//    }
//
//    public static String getSelectedRestaurantAids() {
//        return getInstance().getString(PREF_SELECTED_RESTAURANT_AIDS, "");
//    }
//
//    /* 지역설정 뷰티 */
//    public static void setSelectedBeautyAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_BEAUTY_ANAME, aname).commit();
//    }
//
//    public static String getSelectedBeautyAname() {
//        return getInstance().getString(PREF_SELECTED_BEAUTY_ANAME, "");
//    }
//
//    public static void setSelectedBeautyAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_BEAUTY_AIDS, aids).commit();
//    }
//
//    public static String getSelectedBeautyAids() {
//        return getInstance().getString(PREF_SELECTED_BEAUTY_AIDS, "");
//    }
//
//    /* 지역설정 여행 */
//    public static void setSelectedTravalAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_TRAVAL_ANAME, aname).commit();
//    }
//
//    public static String getSelectedTravalAname() {
//        return getInstance().getString(PREF_SELECTED_TRAVAL_ANAME, "");
//    }
//
//    public static void setSelectedTravalAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_TRAVAL_AIDS, aids).commit();
//    }
//
//    public static String getSelectedTravalAids() {
//        return getInstance().getString(PREF_SELECTED_TRAVAL_AIDS, "");
//    }
//
//    /* 지역설정 문화 */
//    public static void setSelectedCultureAname(String aname) {
//        getInstance().edit().putString(PREF_SELECTED_CULTURE_ANAME, aname).commit();
//    }
//
//    public static String getSelectedCultureAname() {
//        return getInstance().getString(PREF_SELECTED_CULTURE_ANAME, "");
//    }
//
//    public static void setSelectedCultureAids(String aids) {
//        getInstance().edit().putString(PREF_SELECTED_CULTURE_AIDS, aids).commit();
//    }
//
//    public static String getSelectedCultureAids() {
//        return getInstance().getString(PREF_SELECTED_CULTURE_AIDS, "");
//    }
//
//	/* 메인에서 마지막 선택했던 탭 영역 저장 */
//
//    public static void setFirstAppstart(Boolean isFirstAppStart) {
//        getInstance().edit().putBoolean(PREF_FIRST_APP_START, isFirstAppStart).commit();
//    }
//
//    public static boolean isFirstAppStart() {
//        return getInstance().getBoolean(PREF_FIRST_APP_START, false);
//    }
//
//    // 해스오퍼스 SDK 관련
//    public static void setFirstUseHasOffers(boolean isFirst) {
//        getInstance().edit().putBoolean(PREF_FIRST_USE_HASOFFERS, isFirst).commit();
//    }
//
//    public static boolean getFirstUseHasOffers() {
//        return getInstance().getBoolean(PREF_FIRST_USE_HASOFFERS, true);
//    }
//
//    public static void setAcceptUseHasOffers(boolean isAccept) {
//        getInstance().edit().putBoolean(PREF_ACCEPT_USING_HASOFFERS, isAccept).commit();
//    }
//
//    public static boolean getAcceptUseHasOffers() {
//        return getInstance().getBoolean(PREF_ACCEPT_USING_HASOFFERS, false);
//    }
//
//    // 최근검색 관련
//    public static void setAddRecentSearchKeyowrd(boolean isAdded) {
//        getInstance().edit().putBoolean(PREF_ADD_RECENT_SEARCH_KEYWORD, isAdded).commit();
//    }
//
//    public static boolean getAddRecentSearchKeyowrd() {
//        return getInstance().getBoolean(PREF_ADD_RECENT_SEARCH_KEYWORD, false);
//    }
//
//    // 상단 이미지 경로(OLD)
//    public static void setImageTopOldPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_TOP_OLD_PATH, path).commit();
//    }
//
//    public static String getImageTopOldPath() {
//        return getInstance().getString(PREF_IMAGE_TOP_OLD_PATH, null);
//    }
//
//    // 중간 이미지 경로(OLD)
//    public static void setImageCenterOldPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_CENTER_OLD_PATH, path).commit();
//    }
//
//    public static String getImageCenterOldPath() {
//        return getInstance().getString(PREF_IMAGE_CENTER_OLD_PATH, null);
//    }
//
//    // 하단 이미지 경로(OLD)
//    public static void setImageBottomOldPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_BOTTOM_OLD_PATH, path).commit();
//    }
//
//    public static String getImageBottomOldPath() {
//        return getInstance().getString(PREF_IMAGE_BOTTOM_OLD_PATH, null);
//    }
//
//    // 상단 이미지 경로
//    public static void setImageTopPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_TOP_PATH, path).commit();
//    }
//
//    public static String getImageTopPath() {
//        return getInstance().getString(PREF_IMAGE_TOP_PATH, null);
//    }
//
//    // 중간 이미지 경로
//    public static void setImageCenterPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_CENTER_PATH, path).commit();
//    }
//
//    public static String getImageCenterPath() {
//        return getInstance().getString(PREF_IMAGE_CENTER_PATH, null);
//    }
//
//    // 하단 이미지 경로
//    public static void setImageBottomPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_BOTTOM_PATH, path).commit();
//    }
//
//    public static String getImageBottomPath() {
//        return getInstance().getString(PREF_IMAGE_BOTTOM_PATH, null);
//    }
//
//    // 이미지 버전
//    public static void setImageSplashVersion(int version) {
//        getInstance().edit().putInt(PREF_IMAGE_SPLASH_VERSION, version).commit();
//    }
//
//    public static int getImageSplashVersion() {
//        return getInstance().getInt(PREF_IMAGE_SPLASH_VERSION, -1);
//    }
//
//    // 랜딩이미지 경로
//    public static void setImageLandingPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_LANDING_PATH, path).commit();
//    }
//
//    public static String getImageLandingPath() {
//        return getInstance().getString(PREF_IMAGE_LANDING_PATH, null);
//    }
//
//    // 랜딩이미지 버전
//    public static void setImageLandingVersion(int version) {
//        getInstance().edit().putInt(PREF_IMAGE_LANDING_VERSION, version).commit();
//    }
//
//    public static int getImageLandingVersion() {
//        return getInstance().getInt(PREF_IMAGE_LANDING_VERSION, -1);
//    }
//
//    // 웹뷰 닫기버튼 설명 보기
//    public static void setHasBeenWebviewClose(boolean has) {
//        getInstance().edit().putBoolean(PREF_WEBVIEW_CLOSE_DESC, has).commit();
//    }
//
//    public static boolean getHasBeenWebviewClose() {
//        return getInstance().getBoolean(PREF_WEBVIEW_CLOSE_DESC, false);
//    }
//
//    // 웹뷰 활성화 여부
//    public static void setEnableWebView(boolean isEnable) {
//        getInstance().edit().putBoolean(PREF_WEBVIEW_ENABLE, isEnable).commit();
//    }
//
//    public static boolean getEnableWebView() {
//        return getInstance().getBoolean(PREF_WEBVIEW_ENABLE, true);
//    }
//
//    // 검색조건 여행 출발일 시작 Date
//    public static void setDepartureStartDate(String date) {
//        getInstance().edit().putString(PREF_DEPARTURE_START_DATE, date).commit();
//    }
//
//    public static String getDepartureStartDate() {
//        return getInstance().getString(PREF_DEPARTURE_START_DATE, null);
//    }
//
//    // 검색조건 여행 출발일 끝 Date
//    public static void setDepartureEndDate(String date) {
//        getInstance().edit().putString(PREF_DEPARTURE_END_DATE, date).commit();
//    }
//
//    public static String getDepartureEndDate() {
//        return getInstance().getString(PREF_DEPARTURE_END_DATE, null);
//    }
//
//    public static void setDepartureSelectDate(String date) {
//        getInstance().edit().putString(PREF_DEPARTURE_SELECT_DATE, date).commit();
//    }
//
//    public static String getDepartureSelectDate() {
//        return getInstance().getString(PREF_DEPARTURE_SELECT_DATE, "");
//    }
//
//    // 내동네 필터
//    public static void setMyTownFilter(String filter) {
//        getInstance().edit().putString(PREF_MYTOWN_FILTER, filter).commit();
//    }
//
//    public static String getMyTownFilter() {
//        return getInstance().getString(PREF_MYTOWN_FILTER, null);
//    }
//
//    public static void setMyTownMapFilter(String filter) {
//        getInstance().edit().putString(PREF_MYTOWN_MAP_FILTER, filter).commit();
//    }
//
//    public static String getMyTownMapFilter() {
//        return getInstance().getString(PREF_MYTOWN_MAP_FILTER, null);
//    }
//
//    // 검색결과 필터
//    public static void setSearchFilter(String filter) {
//        getInstance().edit().putString(PREF_SEARCH_FILTER, filter).commit();
//    }
//
//    public static String getSearchFilter() {
//        return getInstance().getString(PREF_SEARCH_FILTER, null);
//    }
//
//    // 하단 메세지
//    public static void setShowFooterMsg(int type, boolean isShow) {
//        getInstance().edit().putBoolean(PREF_FOOTER_MSG + "_" + type, isShow).commit();
//    }
//
//    public static boolean isShowFooterMsg(int type) {
//        return getInstance().getBoolean(PREF_FOOTER_MSG + "_" + type, true);
//    }
//
//    // 푸시 도달로그 적재 URL
//    public static void setPushReceiveLogUrl(String url) {
//        getInstance().edit().putString(PREF_PUSH_RECEIVE_LOG_URL, url).commit();
//    }
//
//    public static String getPushReceiveLogUrl() {
//        return getInstance().getString(PREF_PUSH_RECEIVE_LOG_URL, CooChaConstants.DEFAULT_PUSH_RECEIVE_LOG_URL);
//    }
//
//    // 푸시 오픈로그 적재 URL
//    public static void setPushOpenLogUrl(String url) {
//        getInstance().edit().putString(PREF_PUSH_OPEN_LOG_URL, url).commit();
//    }
//
//    public static String getPushOpenLogUrl() {
//        return getInstance().getString(PREF_PUSH_OPEN_LOG_URL, CooChaConstants.DEFAULT_PUSH_OPEN_LOG_URL);
//    }
//
//    // 푸시 쿠차푸시 모듈 사용 유무
//    public static void setCoochaPushLogYn(boolean used) {
//        getInstance().edit().putBoolean(PREF_COOCHA_PUSH_LOG_YN, used).commit();
//    }
//
//    public static boolean isCoochaPushLogYn() {
////        return getInstance().getBoolean(PREF_COOCHA_PUSH_LOG_YN, false);
//        return true;
//    }
//
//    // 푸시 도달로그 적재 URL
//    public static void setCoochaPushArrivalLogUrl(String url) {
//        getInstance().edit().putString(PREF_COOCHA_PUSH_ARRIVAL_LOG_URL, url).commit();
//    }
//
//    public static String getCoochaPushArrivalLogUrl() {
//        return getInstance().getString(PREF_COOCHA_PUSH_ARRIVAL_LOG_URL, CooChaConstants.DEFAULT_COOCHA_PUSH_RECEIVE_LOG_URL);
//    }
//
//    // 푸시 오픈로그 적재 URL
//    public static void setCoochaPushOpenLogUrl(String url) {
//        getInstance().edit().putString(PREF_COOCHA_PUSH_OPEN_LOG_URL, url).commit();
//    }
//
//    public static String getCoochaPushOpenLogUrl() {
//        return getInstance().getString(PREF_COOCHA_PUSH_OPEN_LOG_URL, CooChaConstants.DEFAULT_COOCHA_PUSH_OPEN_LOG_URL);
//    }
//
//	/*
//	* 가이드 관련 프리퍼런스 시작
//	*/
//
//    // 가이드 보여줬는지 여부
//    public static boolean isShowGuideActivity() {
//        return getInstance().getBoolean(PREF_IS_SHOW_GUIDE, false);
//    }
//
//    public static void setShowGuideActivity(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_GUIDE, isShow).commit();
//    }
//
//    // 배송조회가이드 보여줬는지 여부
//    public static boolean isShowGuidePurchaseActivity() {
//        return getInstance().getBoolean(PREF_IS_SHOW_GUIDE_PURCHASE, false);
//    }
//
//    public static void setShowGuidePurchaseActivity(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_GUIDE_PURCHASE, isShow).commit();
//    }
//
//    // 가격비교 가이드 보여줬는지 여부
//    public static boolean isShowGuideShowComparePrice() {
////		return getInstance().getBoolean(PREF_KEY_IS_SHOW_GUIDE_COMPARE_PRICE_GUIDE, false);
//        return true;
//    }
//
//    public static void setShowGuideComparePrice(boolean isShow) {
////		getInstance().edit().putBoolean(PREF_KEY_IS_SHOW_GUIDE_COMPARE_PRICE_GUIDE, isShow).commit();
//    }
//
//    // 가격비교 가이드 보여줬는지 여부
//    public static boolean isShowGuideStyleShop() {
//		return getInstance().getBoolean(PREF_IS_SHOW_GUIDE_STYLE_SHOP, false);
//    }
//
//    public static void setShowGuideStyleShop(boolean isShow) {
//		getInstance().edit().putBoolean(PREF_IS_SHOW_GUIDE_STYLE_SHOP, isShow).commit();
//    }
//
//	/*
//	* 가이드 관련 프리퍼런스 끝
//	*/
//
////    // 나만의 추천 핫딜 설정 가이드 보여줬는지 여부
////    public static final boolean isShowPersonalRecommendDialog() {
////        return getInstance().getBoolean(PREF_IS_SHOW_PERSONAL_RECOMMEND_DIALOG, false);
////    }
////
////    public static final void setShowPersonalRecommendDialog(boolean isShow) {
////        getInstance().edit().putBoolean(PREF_IS_SHOW_PERSONAL_RECOMMEND_DIALOG, isShow).commit();
////    }
//
//    // 나만의 추천 핫딜 설정 파라미터, 값
//    public static String getPersonalRecommendSetting(String param) {
//        return getInstance().getString(PREF_KEY_PERSONAL_RECOMMEND_SETTING + param, null);
//    }
//
//    public static void setPersonalRecommendSetting(String param, String prdid) {
//        // 유효하지 않은 값이 들어오는 경우 해당 값을 지움.
//        // @author uno.kim
//        if (prdid == null || prdid.trim().length() == 0 || prdid.equals("0")) {
//            getInstance().edit().remove(PREF_KEY_PERSONAL_RECOMMEND_SETTING + param).commit();
//        } else {
//            getInstance().edit().putString(PREF_KEY_PERSONAL_RECOMMEND_SETTING + param, prdid).commit();
//        }
//    }
//
//    // 나만의 추천 핫딜 가이드 다이얼로그 노출 일자
//    // 미설정 사용자의 경우 하루 간격으로 출력한다
//    public static void setPersonalRecommendDialogShowDate() {
//        getInstance().edit().putString(PREF_PERSONAL_RECOMMEND_DIALOG_SHOW_DATE, DateUtils.getNow("yyyyMMddHHmm")).commit();
//    }
//
//    // 마지막으로 가이드를 본지 24시간 이후 이거나 최초인 경우
//    public static boolean isPersonalRecommendDialogShowDateAgo24Hour() {
//        Date pre = DateUtils.parse(getInstance().getString(PREF_PERSONAL_RECOMMEND_DIALOG_SHOW_DATE, null), "yyyyMMddHHmm");
//        if (pre != null) {
//            long nowMillis = new Date().getTime();
//            long preMillis = pre.getTime();
//            long perDay = 24 * 60 * 60 * 1000;
//            return nowMillis - preMillis >= perDay;
//        }
//        return true;
//    }
//
//    // 상단 슬라이딩 메뉴 가이드 보여줬는지 여부
//    public static boolean isShowSlidingMsg() {
//        return getInstance().getBoolean(PREF_IS_SHOW_SLIDING_MSG_GUIDE, false);
//    }
//
//    public static void setShowSlidingMsg(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_SLIDING_MSG_GUIDE, isShow).commit();
//    }
//
//    // User Agent 셋팅
//    public static String getUserAgent() {
//        return getInstance().getString(PREF_USER_AGENT, null);
//    }
//
//    public static void setUserAgent(String userAgent) {
//        getInstance().edit().putString(PREF_USER_AGENT, userAgent).commit();
//    }
//
//    // Force Update 셋팅
//    public static String getForceUpdate() {
//        return getInstance().getString(PREF_FORCE_UPDATE, null);
//    }
//
//    public static void setForceUpdate(String forceUpdate) {
//        getInstance().edit().putString(PREF_FORCE_UPDATE, forceUpdate).commit();
//    }
//
//    public static boolean getIsExsitFilter() {
//        return getInstance().getBoolean(PREF_IS_EXSIT_FILTER, false);
//    }
//
//    public static void setIsExsitFilter(boolean exsitFilter) {
//        getInstance().edit().putBoolean(PREF_IS_EXSIT_FILTER, exsitFilter).commit();
//    }
//
//    public static boolean getIsExsitOtherFilter() {
//        return getInstance().getBoolean(PREF_IS_EXSIT_OTHER_FILTER, false);
//    }
//
//    public static void setIsExsitOtherFilter(boolean exsitOtherFilter) {
//        getInstance().edit().putBoolean(PREF_IS_EXSIT_OTHER_FILTER, exsitOtherFilter).commit();
//    }
//
//    public static boolean getIsExsitPopularCategory() {
//        return getInstance().getBoolean(PREF_IS_EXSIT_POPULAR_CATEGORY, false);
//    }
//
//    public static void setIsExsitPopularCategory(boolean popularCategory) {
//        getInstance().edit().putBoolean(PREF_IS_EXSIT_POPULAR_CATEGORY, popularCategory).commit();
//    }
//
//    public static boolean getIsShowSlideGuide1() {
//        return getInstance().getBoolean(PREF_IS_SHOW_SLIDE_GUIDE_1, false);
//    }
//
//    public static void setIsShowSlideGuide1(boolean isShowSlideGuide) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_SLIDE_GUIDE_1, isShowSlideGuide).commit();
//    }
//
//    public static boolean getIsShowSlideGuide2() {
//        return getInstance().getBoolean(PREF_IS_SHOW_SLIDE_GUIDE_2, false);
//    }
//
//    public static void setIsShowSlideGuide2(boolean isShowSlideGuide) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_SLIDE_GUIDE_2, isShowSlideGuide).commit();
//    }
//
//    public static boolean isFirstFilterAreaFragmentStart() {
//        return getInstance().getBoolean(PREF_IS_FIRST_FILTER_AREA_START, true);
//    }
//
//    public static void setFirstFilterAreaFragmentStart(boolean b) {
//        getInstance().edit().putBoolean(PREF_IS_FIRST_FILTER_AREA_START, b).commit();
//    }
//
//    public static void setFloatingCloseDate(long timeInMillis) {
//        getInstance().edit().putLong(PREF_FLOATING_CLOSE_TIME, timeInMillis).commit();
//    }
//
//    public static long getFloatingCloseDate() {
//        return getInstance().getLong(PREF_FLOATING_CLOSE_TIME, 0L);
//    }
//
//    public static void setStartingCloseDate(long timeInMillis) {
//        getInstance().edit().putLong(PREF_STARTING_CLOSE_TIME, timeInMillis).commit();
//    }
//
//    public static long getStartingCloseDate() {
//        return getInstance().getLong(PREF_STARTING_CLOSE_TIME, 0L);
//    }
//
////	public static void setRelationProductYn(String relation_product_yn){
////		getInstance().edit().putString(PREF_RELATION_PRODUCT_YN, relation_product_yn).commit();
////	}
//
////	public static String getRelationProductYn() {
////		// 4.0 미만은 사용하지 않기로 함.
////		return Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH? "N" : getInstance().getString(PREF_RELATION_PRODUCT_YN, CooChaConstants.IS_USE_RELATIVE_PRODUCT);
////	}
//
//    public static void setGoogleAid(String aid) {
//        getInstance().edit().putString(PREF_GOOGLE_AID, aid).commit();
//    }
//
//    public static String getGoogleAid() {
//        return getInstance().getString(PREF_GOOGLE_AID, null);
//    }
//
//    public static void setTestMdid(String mdid) {
//        getInstance().edit().putString(PREF_TEST_MDID, mdid).commit();
//    }
//
//    public static String getTestMdid() {
//        return getInstance().getString(PREF_TEST_MDID, null);
//    }
//
//    public static void setPerformCount(int count) {
//        getInstance().edit().putInt(PREF_PERFORM_COUNT, count).commit();
//    }
//
//    public static int getPerformCount() {
//        return getInstance().getInt(PREF_PERFORM_COUNT, 0);
//    }
//
//    public static void setFilterAreaGuide(boolean isShawn) {
//        getInstance().edit().putBoolean(PREF_FLITER_AREA_GUIDE, isShawn).commit();
//    }
//
//    public static boolean getFilterAreaGuide() {
//        return getInstance().getBoolean(PREF_FLITER_AREA_GUIDE, false);
//    }
//
//    public static void setShowConnectShopDialog(String sid, boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_CONNECT_SHOP_DIALOG + "_" + sid, isShow).commit();
//    }
//
//    public static boolean isShowConnectShopDialog(String sid) {
////        return getInstance().getBoolean(PREF_IS_SHOW_CONNECT_SHOP_DIALOG + "_" + sid, false);
//        long today = Long.parseLong(DateUtils.getToday("yyyyMMdd"));
//        if (today > getShopConnectShopDialogDate(sid)) {
//            int limit = getShopConnectShopDialogLimit();
//            int count  = getShopConnectShopDialogCount(sid);
//            if (count != CooChaConstants.SHOP_CONNECT_DIALOG_ESCAPE_COUNT
//                    && count < limit) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//    public static void incrementShopConnectShopDialogCount(String sid) {
//        setShopConnectShopDialogCount(sid, getShopConnectShopDialogCount(sid) + 1);
//    }
//
//    public static void setShopConnectShopDialogCount(String sid, int count) {
//        getInstance().edit().putInt(PREF_SHOW_CONNECT_SHOP_DIALOG_COUNT + "_" + sid, count).commit();
//    }
//
//    public static int getShopConnectShopDialogCount(String sid) {
//        return getInstance().getInt(PREF_SHOW_CONNECT_SHOP_DIALOG_COUNT + "_" + sid, 0);
//    }
//
//
//    public static void updateShopConnectShopDialogDate(String sid) {
//        getInstance().edit().putLong(PREF_SHOW_CONNECT_SHOP_DIALOG_DATE + "_" + sid, Long.parseLong(DateUtils.getToday("yyyyMMdd"))).commit();
//    }
//
//    public static long getShopConnectShopDialogDate(String sid) {
//        return getInstance().getLong(PREF_SHOW_CONNECT_SHOP_DIALOG_DATE + "_" + sid, 0);
//    }
//
//
//    public static void setShopConnectShopDialogLimit(int count) {
//        getInstance().edit().putInt(PREF_SHOW_CONNECT_SHOP_DIALOG_LIMIT, count).commit();
//    }
//
//    public static int getShopConnectShopDialogLimit() {
//        return getInstance().getInt(PREF_SHOW_CONNECT_SHOP_DIALOG_LIMIT, 10);
//    }
//
//
//    public static void setShowAutoLoginShopDialog(String sid, boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_AUTO_LOGIN_SHOP_DIALOG + "_" + sid, isShow).commit();
//    }
//
//    public static boolean isShowAutoLoginShopDialog(String sid) {
//        return getInstance().getBoolean(PREF_IS_SHOW_AUTO_LOGIN_SHOP_DIALOG + "_" + sid, false);
//    }
//
//    public static boolean isFirstShowPurchaseList() {
//        return getInstance().getBoolean(PREF_KEY_IS_SHOW_FIRST_PURCHASE_LIST, true);
//    }
//
//    public static void setIsFirstShowPurchaseList(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_KEY_IS_SHOW_FIRST_PURCHASE_LIST, isShow).commit();
//    }
//
//    // 상단 배송조회 메뉴 가이드 보여줬는지 여부 - (수정 : 자동로그인 메뉴 가이드 보여줬는지 여부)
//    public static boolean isShowPurchaseMsg() {
//        return getInstance().getBoolean(PREF_IS_SHOW_AUTO_LOGIN_MSG_GUIDE, false);
//    }
//
//    public static void setShowPurchaseMsg(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_AUTO_LOGIN_MSG_GUIDE, isShow).commit();
//    }
//
//    public static void setCaulyBannerPosition(int cauly_position) {
//        getInstance().edit().putInt(PREF_CAULY_BANNER_POSITION, cauly_position).commit();
//    }
//
//    public static int getCaulyBannerPosition() {
//        return getInstance().getInt(PREF_CAULY_BANNER_POSITION, CooChaConstants.CAULY_POSITION - 1);
//    }
//
//    public static void setCouponListPosition(int coupon_list_position) {
//        getInstance().edit().putInt(PREF_COUPON_LIST_POSITION, coupon_list_position).commit();
//    }
//
//    public static int getCouponPosition() {
//        return getInstance().getInt(PREF_COUPON_LIST_POSITION, CooChaConstants.COOCHA_COUPON_POSITION);
//    }
//
//    // 카울리 앱 코드 추가
//    public static void setCaulyBannerAppCode(String cauly_app_code) {
//        getInstance().edit().putString(PREF_CAULY_BANNER_APP_CODE, cauly_app_code).commit();
//    }
//
//    public static String getCaulyBannerAppcode() {
//        return getInstance().getString(PREF_CAULY_BANNER_APP_CODE, GlobalApplication.getContext().getString(R.string.app_code));
//    }
//
////    public static void setUse11st(String used) {
////        getInstance().edit().putString(PREF_USE_11ST, used).commit();
////    }
////
////    public static String getUse11st() {
////        return getInstance().getString(PREF_USE_11ST, CooChaConstants.USE_11ST);
////    }
//
//    public static void setUseBBProject(String used) {
//        getInstance().edit().putString(PREF_USE_BB_PROJECT, used).commit();
//    }
//
//    public static String getUseBBProject() {
//        return getInstance().getString(PREF_USE_BB_PROJECT, CooChaConstants.USE_BB_PROJECT);
////		return "Y";
//    }
//
//    public static void setUseSearchTab(String used) {
//        getInstance().edit().putString(PREF_USE_SEARCH_TAB, used).commit();
//    }
//
//    public static String getUseSearchTab() {
//        return getInstance().getString(PREF_USE_SEARCH_TAB, CooChaConstants.USE_SEARCH_TAB);
//    }
//
////    public static void setUseTwoArrayList(String used) {
////        getInstance().edit().putString(PREF_USE_TWO_ARRAY_LIST, used).commit();
////    }
////
////    public static String getUseTwoArrayList() {
////        return getInstance().getString(PREF_USE_TWO_ARRAY_LIST, CooChaConstants.USE_TWO_ARRAY_LIST);
////    }
//
////    public static void setUseMallLinkTwoArrayList(String used) {
////        getInstance().edit().putString(PREF_USE_MALL_LINK_TWO_ARRAY_LIST, used).commit();
////    }
////
////    public static String getUseMallLinkTwoArrayList() {
////        return getInstance().getString(PREF_USE_MALL_LINK_TWO_ARRAY_LIST, CooChaConstants.USE_MALL_LINK_TWO_ARRAY_LIST);
////    }
//
////    // 웹뷰 닫기버튼 사용 여부(서버 플래그)
////    public static void setUseWebviewCloseButton(String used) {
////        getInstance().edit().putString(PREF_USE_WEBVIEW_CLOSE_BUTTON, used).commit();
////    }
////
////    public static String getUseWebviewCloseButton() {
////        return getInstance().getString(PREF_USE_WEBVIEW_CLOSE_BUTTON, CooChaConstants.USE_WEBVIEW_CLOSE_BUTTON);
////    }
//
////    // 웹뷰 닫기버튼 설정 여부(사용자 설정)
////    public static void setEnableWebviewCloseButton(boolean enable) {
////        getInstance().edit().putBoolean(PREF_ENABLE_WEBVIEW_CLOSE_BUTTON, enable).commit();
////    }
////
////    public static boolean getEnableWebviewCloseButton() {
////        return getInstance().getBoolean(PREF_ENABLE_WEBVIEW_CLOSE_BUTTON, true);
////    }
//
////    public static void setIsShowWebviewCloseButtonGuide(boolean isShow) {
////        getInstance().edit().putBoolean(PREF_IS_SHOW_WEBVIEW_CLOSE_BUTTON_GUIDE, isShow).commit();
////    }
////
////    public static boolean getIsShowWebviewCloseButtonGuide() {
////        return getInstance().getBoolean(PREF_IS_SHOW_WEBVIEW_CLOSE_BUTTON_GUIDE, false);
////    }
//
//    // 웹뷰 닫기버튼의 포지션을 저장
//    public static void setWebviewCloseButtonPosition(int top, int left) {
//        getInstance().edit().putInt(PREF_WEBVIEW_CLOSE_BUTTON_TOP, top).commit();
//        getInstance().edit().putInt(PREF_WEBVIEW_CLOSE_BUTTON_LEFT, left).commit();
//    }
//
//    /**
//     * [0]: top. [1]: left
//     * @return not set is -1
//     */
//    public static int[] getWebviewCloseButtonPosition() {
//        int[] position = new int[2];
//        position[0] = getInstance().getInt(PREF_WEBVIEW_CLOSE_BUTTON_TOP, -1);
//        position[1] = getInstance().getInt(PREF_WEBVIEW_CLOSE_BUTTON_LEFT, -1);
//        return position;
//    }
//
//    public static void setLandingTime(String landingTime) {
//        int time = CooChaConstants.LANDING_TIME;
//        try {
//            time = Integer.parseInt(landingTime);
//        } catch (NumberFormatException nfe) {
//        }
//        getInstance().edit().putInt(PREF_LANDING_TIME, time).commit();
//    }
//
//    public static int getLandingTime() {
//        return getInstance().getInt(PREF_LANDING_TIME, CooChaConstants.LANDING_TIME);
//    }
//
//    // isp 팝업 이미지 경로
//    public static void setImageIspPath(String path) {
//        getInstance().edit().putString(PREF_IMAGE_ISP_PATH, path).commit();
//    }
//
//    public static String getImageIspPath() {
//        return getInstance().getString(PREF_IMAGE_ISP_PATH, null);
//    }
//
//    // isp 팝업 이미지 버전
//    public static void setIspNoticeVersion(int version) {
//        getInstance().edit().putInt(PREF_ISP_NOTICE_VERSION, version).commit();
//    }
//
//    public static int getIspNoticeVersion() {
//        return getInstance().getInt(PREF_ISP_NOTICE_VERSION, -1);
//    }
//
//    public static void setPersonalRecommendYn(String use_yn) {
//        getInstance().edit().putString(PREF_PERSONAL_RECOMMEND_USEYN, use_yn).commit();
//    }
//
//    public static String getPersonalRecommendYn() {
//        return getInstance().getString(PREF_PERSONAL_RECOMMEND_USEYN, CooChaConstants.IS_PERSONAL_RECOMMEND);
////		return "Y";
//    }
//
//    public static void setWebviewDealHistory(String history_yn) {
//        getInstance().edit().putString(PREF_WEBVIEW_DEAL_HISTORY, history_yn).commit();
//    }
//
//    public static String getWebviewDealHistory() {
//        return getInstance().getString(PREF_WEBVIEW_DEAL_HISTORY, CooChaConstants.IS_WEBVIEW_DEAL_HISTORY);
////		return "Y";
//    }
//
//    public static void setWebviewDealHistoryTooltipCount(int count) {
//        getInstance().edit().putInt(PREF_WEBVIEW_DEAL_HISTORY_TOOLTIP, count).commit();
//    }
//
//    public static int getWebviewDealHistoryTooltipCount() {
//        return getInstance().getInt(PREF_WEBVIEW_DEAL_HISTORY_TOOLTIP, 0);
////		return "Y";
//    }
//
//    public static void setSlindingMenuAll(String all) {
//        getInstance().edit().putString(PREF_SILDING_MENU_ALL, all).commit();
//    }
//
//    public static String getSlindingMenuAll() {
//        return getInstance().getString(PREF_SILDING_MENU_ALL, CooChaConstants.IS_SLIDING_MENU_ALL);
////		return "Y";
//    }
//
//    public static void setIsUsePurchase(boolean used) {
//        getInstance().edit().putBoolean(PREF_IS_USE_PURCHASE, used).commit();
//    }
//
//    public static boolean getIsUsePurchase() {
//        return getInstance().getBoolean(PREF_IS_USE_PURCHASE, "Y".equals(CooChaConstants.IS_USE_PURCHASE));
//    }
//
//    public static void setRecobellPushUuid(String uuid) {
//        getInstance().edit().putString(PREF_RECOBEL_PUSH_UUID, uuid).commit();
//    }
//
//    public static String getRecobellPushUuid() {
//        return getInstance().getString(PREF_RECOBEL_PUSH_UUID, null);
//    }
//
//    public static void setAgreePurchaseTerms(boolean b) {
//        getInstance().edit().putBoolean(PREF_IS_AGREE_PURCHASE_TERMS, b).commit();
//    }
//
//    public static boolean isAgreePurchaseTerms() {
//        return getInstance().getBoolean(PREF_IS_AGREE_PURCHASE_TERMS, false);
//    }
//
//    public static void setIsBrowserHistoryAppend(boolean used) {
//        getInstance().edit().putBoolean(PREF_IS_BROWSER_HISTORY_APPEND, used).commit();
//    }
//
//    public static boolean getIsBrowserHistoryAppend() {
//        return getInstance().getBoolean(PREF_IS_BROWSER_HISTORY_APPEND, true);
//    }
//
//    public static void setIsShippingErrorLogUseYn(boolean used) {
//        getInstance().edit().putBoolean(PREF_SHIPPING_ERROR_LOG_USE_YN, used).commit();
//    }
//
//    public static boolean getIsShippingErrorLogUseYn() {
//        return getInstance().getBoolean(PREF_SHIPPING_ERROR_LOG_USE_YN, true);
//    }
//
//    public static void setPrefWebviewPopupGuide(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_WEBVIEW_POPUP_GUIDE, isShow).commit();
//    }
//
//    public static boolean getPrefWebviewPopupGuide() {
//        return getInstance().getBoolean(PREF_WEBVIEW_POPUP_GUIDE, false);
//    }
//
//    public static void setPrefWebviewUseCount(int count) {
//        getInstance().edit().putInt(PREF_WEBVIEW_USE_COUNT, count).commit();
//    }
//
//    public static int getPrefWebviewUseCount() {
//        return getInstance().getInt(PREF_WEBVIEW_USE_COUNT, 0);
//    }
//
//    public static void setPrefSearchPopupGuide(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_SEARCH_POPUP_GUIDE, isShow).commit();
//    }
//
//    public static boolean getPrefSearchPopupGuide() {
//        return getInstance().getBoolean(PREF_SEARCH_POPUP_GUIDE, false);
//    }
//
//    public static void setPurchaseAlertMsg(String msg) {
//        getInstance().edit().putString(PREF_PURCHASE_ALERT_MSG, msg).commit();
//    }
//
//    public static String getPurchaseAlertMsg() {
//        return getInstance().getString(PREF_PURCHASE_ALERT_MSG, null);
//    }
//
//    public static void setAppVersion(String msg) {
//        getInstance().edit().putString(PREF_APP_VERSION, msg).commit();
//    }
//
//    public static String getAppVersion() {
//        return getInstance().getString(PREF_APP_VERSION, "");
//    }
//
//    public static void setUseWebFooter(String used) {
//        getInstance().edit().putString(PREF_USE_WEB_FOOTER, used).commit();
//    }
//
//    public static boolean getUseWebFooter() {
//        return "Y".equals(getInstance().getString(PREF_USE_WEB_FOOTER, CooChaConstants.USE_WEB_FOOTER));
////		return "Y";
//    }
//
//    public static void setPrefWebviewClosePopupGuide(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_WEBVIEW_CLOSE_POPUP_GUIDE, isShow).commit();
//    }
//
//    public static boolean getPrefWebviewClosePopupGuide() {
//        return getInstance().getBoolean(PREF_WEBVIEW_CLOSE_POPUP_GUIDE, false);
//    }
//
//    public static void setPrefAppFirstInstall(boolean isFirstInstall) {
//        getInstance().edit().putBoolean(PREF_APP_FIRST_INSTALL, isFirstInstall).commit();
//    }
//
//    public static boolean getPrefAppFirstInstall() {
//        return getInstance().getBoolean(PREF_APP_FIRST_INSTALL, false);
//    }
//
//    public static void setPrefIsShowSearchResultLowestN(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_IS_SHOW_SEARCH_RESULT_LOWEST_N, isShow).commit();
//    }
//
//    public static boolean getPrefIsShowSearchResultLowestN() {
//        return getInstance().getBoolean(PREF_IS_SHOW_SEARCH_RESULT_LOWEST_N, false);
//    }
//
//    public static void setUseNewSearchResult(boolean newSearchResult) {
//        getInstance().edit().putBoolean(PREF_NEW_SEARCH_RESULT, newSearchResult).commit();
//    }
//
//    public static boolean getUseNewSearchResult() {
//        return getInstance().getBoolean(PREF_NEW_SEARCH_RESULT, CooChaConstants.USE_NEW_SEARCH_RESULT);
//    }
//
//    public static void setUseNewSearchResultHistory(boolean newSearchResult) {
//        getInstance().edit().putBoolean(PREF_NEW_SEARCH_RESULT_HISTORY, newSearchResult).commit();
//    }
//
//    public static boolean getUseNewSearchResultHistory() {
//        return getInstance().getBoolean(PREF_NEW_SEARCH_RESULT_HISTORY, CooChaConstants.USE_NEW_SEARCH_RESULT);
//    }
//
//    public static void setUseNewSearchResultShopFilter(boolean useShopFilter) {
//        getInstance().edit().putBoolean(PREF_NEW_SEARCH_RESULT_SHOP_FILTER, useShopFilter).commit();
//    }
//
//    public static boolean getUseNewSearchResultShopFilter() {
//        return getInstance().getBoolean(PREF_NEW_SEARCH_RESULT_SHOP_FILTER, CooChaConstants.USE_NEW_SEARCH_RESULT);
//    }
//
//    // 검색 디폴트 키워드가 서버에서 내려오면 여기에 셋팅
//    public static void setNewSearchKeyword(String keyword) {
//        getInstance().edit().putString(PREF_NEW_SEARCH_KEYWORD, keyword).commit();
//    }
//
//    public static String getNewSearchKeyword() {
//        return getInstance().getString(PREF_NEW_SEARCH_KEYWORD, null);
//    }
//
//    // 검색 디폴트 키워드가 서버에서 내려오면 여기에 셋팅
//    public static void setNewSearchKeywordLabel(String keyword) {
//        getInstance().edit().putString(PREF_NEW_SEARCH_KEYWORD_LABEL, keyword).commit();
//    }
//
//    public static String getNewSearchKeywordLabel() {
//        return getInstance().getString(PREF_NEW_SEARCH_KEYWORD_LABEL, null);
//    }
//
//    // 검색 디폴트 키워드 키워드 id
//    public static void setNewSearchKeywordId(String id) {
//        getInstance().edit().putString(PREF_NEW_SEARCH_KEYWORD_ID, id).commit();
//    }
//
//    public static String getNewSearchKeywordId() {
//        return getInstance().getString(PREF_NEW_SEARCH_KEYWORD_ID, null);
//    }
//
////    public static void setUseWebviewBottomList(boolean used) {
////        getInstance().edit().putBoolean(PREF_WEBVIEW_BOTTOM_LIST_YN, used).commit();
////    }
////
////    public static boolean getUseWebviewBottomList() {
////        return getInstance().getBoolean(PREF_WEBVIEW_BOTTOM_LIST_YN, CooChaConstants.USE_WEBVIEW_BOTTOM_LIST_YN);
////    }
//
//    public static void setMoreMenuListVo(String vo) {
//        getInstance().edit().putString(PREF_MORE_MENU_LIST_VO, vo).commit();
//    }
//
//    public static String getMoreMenuListVo() {
//        return getInstance().getString(PREF_MORE_MENU_LIST_VO, null);
//    }
//
//    public static void setJjimDidList(String vo) {
//        getInstance().edit().putString(PREF_JJIM_DID_LIST, vo).commit();
//    }
//
//    public static String getJjimDidList() {
//        return getInstance().getString(PREF_JJIM_DID_LIST, null);
//    }
//
//    public static void setStatusBarHeight(int height) {
//        getInstance().edit().putInt(PREF_STATUS_BAR_HEIGHT, height).commit();
//    }
//
//    public static int getStatusBarHeight() {
//        return getInstance().getInt(PREF_STATUS_BAR_HEIGHT, 0);
//    }
//
//    public static void setDisplayWidth(int width) {
//        getInstance().edit().putInt(PREF_DISPLAY_WIDTH, width).commit();
//    }
//
//    public static int getDisplayWidth() {
//        return getInstance().getInt(PREF_DISPLAY_WIDTH, 0);
//    }
//
//    public static void setDisplayHeight(int height) {
//        getInstance().edit().putInt(PREF_DISPLAY_HEIGHT, height).commit();
//    }
//
//    public static int getDisplayHeight() {
//        return getInstance().getInt(PREF_DISPLAY_HEIGHT, 0);
//    }
//
//    public static void setStyleShopRowCount(int count) {
//        getInstance().edit().putInt(PREF_STYLE_SHOP_ROW_COUNT, count).commit();
//    }
//
//    public static int getStyleShopRowCount() {
//        return getInstance().getInt(PREF_STYLE_SHOP_ROW_COUNT, 0);
//    }
//
//    public static boolean setStyleShopRowSquareHeight(float height) {
//        return getInstance().edit().putFloat(PREF_STYLE_SHOP_ROW_SQUARE_HEIGHT, height).commit();
//    }
//
//    public static float getStyleShopRowSquareHeight() {
//        return getInstance().getFloat(PREF_STYLE_SHOP_ROW_SQUARE_HEIGHT, 0);
//    }
//
//    public static boolean setStyleShopRowRectangleWidth(float width) {
//        return getInstance().edit().putFloat(PREF_STYLE_SHOP_ROW_RECTANGLE_WIDTH, width).commit();
//    }
//
//    public static float getStyleShopRowRectangleWidth() {
//        return getInstance().getFloat(PREF_STYLE_SHOP_ROW_RECTANGLE_WIDTH, 0);
//    }
//
//    public static boolean setStyleShopRowRectangleHeight(float height) {
//        return getInstance().edit().putFloat(PREF_STYLE_SHOP_ROW_RECTANGLE_HEIGHT, height).commit();
//    }
//
//    public static float getStyleShopRowRectangleHeight() {
//        return getInstance().getFloat(PREF_STYLE_SHOP_ROW_RECTANGLE_HEIGHT, 0);
//    }
//
//    public static boolean setStyleShopRowFitHeight(float height) {
//        return getInstance().edit().putFloat(PREF_STYLE_SHOP_ROW_FIT_HEIGHT, height).commit();
//    }
//
//    public static float getStyleShopRowFitHeight() {
//        return getInstance().getFloat(PREF_STYLE_SHOP_ROW_FIT_HEIGHT, 0);
//    }
//
//    public static float getStyleShopRowHeight() {
//        return getStyleShopRowIsSquare() ? getStyleShopRowSquareHeight() :getStyleShopRowFitHeight();
//    }
//
//    public static void setStyleShopRowIsSquare(boolean isSquare) {
//        getInstance().edit().putBoolean(PREF_STYLE_SHOP_ROW_IS_SQUARE, isSquare).commit();
//    }
//
//    public static boolean getStyleShopRowIsSquare() {
//        if(CooChaConstants.USE_STYLE_SHOP_CATEGORY){
//            return getInstance().getBoolean(PREF_STYLE_SHOP_ROW_IS_SQUARE, false);
//        } else {
//            return getInstance().getBoolean(PREF_STYLE_SHOP_ROW_IS_SQUARE, true);
//        }
//
//    }
//
//    public static void setPushAlarmOpenYn(boolean isOpen) {
//        getInstance().edit().putBoolean(PREF_PUSH_ALARM_OPEN_YN, isOpen).commit();
//    }
//
//    public static boolean getPushAlarmOpenYn() {
//        return getInstance().getBoolean(PREF_PUSH_ALARM_OPEN_YN, true);
//    }
//
//    public static void setStyleShopOffset(float offset) {
//        getInstance().edit().putInt(PREF_STYLE_SHOP_OFFSET, (int) offset).commit();
//    }
//
//    public static int getStyleShopOffset() {
//        return getInstance().getInt(PREF_STYLE_SHOP_OFFSET, 0);
//    }
//
//    public static void setPrefMallLinkNewGeneration(boolean isNew) {
//        getInstance().edit().putBoolean(PREF_MALL_LINK_NEW_GENERATION, isNew).commit();
//    }
//
//    public static boolean getPrefMallLinkNewGeneration() {
//        return getInstance().getBoolean(PREF_MALL_LINK_NEW_GENERATION, false);
//    }
//
//    /**
//     * Tae-hwan 2016.06.10
//     * 스타일# 할인 상품 최신 목록을 확인하기 위한 Pref
//     */
//    public static void setPrefStyleShopSaleNewItemUpdateDate() {
//        getInstance().edit().putString(PREF_STYLE_SHOP_SALE_NEW_ITEM_UPDATE_DATE, DateUtils.getNow("yyyyMMdd0000")).commit();
//    }
//
//    /**
//     * Tae-hwan 2016.06.10
//     * 스타일# 할인 상품 최신 목록을 확인하기 위한 Pref
//     */
//    public static String getPrefStyleShopSaleNewItemUpdateDate() {
//        return getInstance().getString(PREF_STYLE_SHOP_SALE_NEW_ITEM_UPDATE_DATE, DEFAULT_DATE);
//    }
//
//    public static void setNeedPushAgree(boolean need){
//        getInstance().edit().putBoolean(PREF_COOCHA_NEED_PUSH_AGREE, need).commit();
//    }
//
//    public static boolean getNeedPushAgree() {
//        return getInstance().getBoolean(PREF_COOCHA_NEED_PUSH_AGREE, true);
//    }
//
//    public static void setPushAgreeDialogShowTime(long time){
//        getInstance().edit().putLong(PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_TIME, time).commit();
//    }
//
//    public static long getPushAgreeDialogShowTime() {
//        return getInstance().getLong(PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_TIME, 0);
//    }
//
//    public static void setPushAgreeDialogShowCount(int count){
//        getInstance().edit().putInt(PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_COUNT, count).commit();
//    }
//
//    public static int getPushAgreeDialogShowCount() {
//        return getInstance().getInt(PREF_COOCHA_PUSH_AGREE_DIALOG_SHOW_COUNT, 0);
//    }
//
//    public static void setShowBestTutorial(boolean isShow) {
//        getInstance().edit().putBoolean(PREF_BEST_TUTORIAL, isShow).commit();
//    }
//
//    public static boolean isShowBestTutorial() {
//        return getInstance().getBoolean(PREF_BEST_TUTORIAL, false);
//    }
//
//
//    /**
//     * 스타일# 'SHOP'에서 즐겨찾기 추가하는 로직
//     * 동일한 sid가 없을 경우에만 추가한다
//     * @param sid
//     * @return
//     */
//    public static boolean setStyleShopBookMark(String sid){
//
//        if(TextUtils.isEmpty(sid)){
//            return false;
//        }
//
//        String bookMark = getStyleShopBookMark();
//
//        boolean isNotPutCheck = false;
//
//        if(TextUtils.isEmpty(bookMark)){
//            getInstance().edit().putString(PREF_STYLE_SHOP_BOOK_MARK, sid + ",").commit();
//        } else {
//            String[] arrBookMark = bookMark.split(",");
//
//            for (int i = 0; i < arrBookMark.length; i++){
//                if(sid.equals(arrBookMark[i])){
//                    isNotPutCheck = true;
//                    break;
//                }
//            }
//
//            if(!isNotPutCheck){
//                getInstance().edit().putString(PREF_STYLE_SHOP_BOOK_MARK, sid + "," + bookMark).commit();
//            }
//        }
//
//        return !(isNotPutCheck);
//
//    }
//
//    /**
//     * 스타일# 'SHOP'에서 즐겨찾기 목록 조회
//     * 동일한 sid가 있을것을 대비해 삭제시 sid + ","로 조회하여 삭제한다
//     * 그래서 빈 문자가 아닐 경우 마지막 ","를 지우고 사용한다
//     * @return
//     */
//    public static String getStyleShopBookMark(){
//
//        return getInstance().getString(PREF_STYLE_SHOP_BOOK_MARK, "");
//    }
//
//    /**
//     * 저장된 북마크중 동일한 sid가 있을 경우 삭제한다
//     * 삭제 조건은 동일한 sid가 있을시 삭제하고
//     * 삭제시 sid + ","로 삭제한다
//     * @param sid
//     */
//    public static void removeStyleShopBookMark(String sid){
//
//        if(TextUtils.isEmpty(sid)){
//            return;
//        }
//
//        String bookMark = getStyleShopBookMark();
//
//        String[] arrBookMark = bookMark.split(",");
//
//        for (int i = 0; i < arrBookMark.length; i++){
//            if(sid.equals(arrBookMark[i])){
//                bookMark =  bookMark.replace((sid + ",") , "");
//                break;
//            }
//        }
//
//        getInstance().edit().putString(PREF_STYLE_SHOP_BOOK_MARK, bookMark).commit();
//
//    }
//
//    /**
//     * 스타일# 'SHOP'에서 즐겨찾기 체크
//     * @param sid
//     * @return
//     */
//    public static boolean isStyleShopBookMark(String sid) {
//
//        if (TextUtils.isEmpty(sid)) {
//            return false;
//        }
//
//        String bookMark = getStyleShopBookMark();
//
//        boolean isBookMark = false;
//
//        if (!(TextUtils.isEmpty(bookMark)) && bookMark.contains(sid + ",")) {
//            isBookMark = true;
//        }
//
//        return isBookMark;
//
//    }
//
//    /**
//     * 스타일# 엑션바에서 쿠폰 추가 뱃지 표시를 위한 값
//     * @param isModify
//     */
//    public static void setStyleShopBookMarkModify(boolean isModify){
//        getInstance().edit().putBoolean(PREF_STYLE_SHOP_BOOK_MARK_MODIFY, isModify).commit();
//    }
//
//    /**
//     * 스타일# 엑션바에서 쿠폰 추가 뱃지 표시를 위한 값
//     * @return
//     */
//    public static boolean getStyleShopBookMarkModify(){
//        return getInstance().getBoolean(PREF_STYLE_SHOP_BOOK_MARK_MODIFY, false);
//    }
//
//    /**
//     * 오늘의 싸다구 / MD Pick 알림 on / off
//     */
//    public static void setMDPickPushAlarm(boolean pushEnable){
//        getInstance().edit().putBoolean(PREF_SSADAGU_MDPICK_PUSH_ENABLE, pushEnable).commit();
//    }
//
//    /**
//     * 오늘의 싸다구 / MD Pick 알림 on / off
//     *
//     * @return
//     */
//    public static boolean getMDPickPushAlarm(){
//        return getInstance().getBoolean(PREF_SSADAGU_MDPICK_PUSH_ENABLE, false);
//    }
//
//    public static void setPrefSsadaguBubbleShow(boolean show){
//        getInstance().edit().putBoolean(PREF_SSADAGU_BUBBLE_SHOW, show).commit();
//    }
//
//    public static boolean getPrefSsadaguBubbleShow(){
//        return getInstance().getBoolean(PREF_SSADAGU_BUBBLE_SHOW, true);
//    }
//
////    public static void setPrefHotBubbleShow(boolean show){
////        getInstance().edit().putBoolean(PREF_HOT_BUBBLE_SHOW, show).commit();
////    }
//
////    public static boolean getPrefHotBubbleShow(){
////        return getInstance().getBoolean(PREF_HOT_BUBBLE_SHOW, true);
////    }
//
//    /**
//     * 스타일# 카테고리 리스트 중 'hot'버튼 표시를 위한 값
//     */
//    public static void setStyleShopCategoryHot(String viewType){
//
//        if(TextUtils.isEmpty(viewType)){
//            return;
//        }
//
//
//        boolean isNotPutCheck = getStyleShopCategoryHot(viewType);
//
//        if(!isNotPutCheck){
//
//            String categoryHot = getInstance().getString(PREF_STYLE_SHOP_CATEGORY_HOT, "");
//
//            getInstance().edit().putString(PREF_STYLE_SHOP_CATEGORY_HOT, categoryHot + viewType + ",").commit();
//        }
//
//    }
//
//    /**
//     * 스타일# 카테고리 리스트 중 'hot'버튼 표시를 위한 값
//     * 한번이라도 선택을 했는지 체크
//     */
//    public static boolean getStyleShopCategoryHot(String viewType){
//
//        if(TextUtils.isEmpty(viewType)){
//            return false;
//        }
//
//        String categoryHot = getInstance().getString(PREF_STYLE_SHOP_CATEGORY_HOT, "");
//
//        boolean isNotPutCheck = false;
//
//        if(TextUtils.isEmpty(categoryHot)){
////            getInstance().edit().putString(PREF_STYLE_SHOP_CATEGORY_HOT, cid + ",").commit();
//        } else {
//            // 크게 중요한 데이터가 아니라서 정확성 보단 속도로 변경
//
//            if(categoryHot.contains(viewType + ",")){
//                isNotPutCheck = true;
//            }
//
//        }
//
//        return isNotPutCheck;
//
//    }
//
//
//    /**
//     * 임시 닉네임 설정
//     * MD Pick 에서 댓글달기시 닉네임
//     */
//    public static void setUSER_NICKNAME(String nick){
//        getInstance().edit().putString(PREF_NICK_NAME, nick).commit();
//    }
//    public static String getUSER_NICKNAME(){
//        return getInstance().getString(PREF_NICK_NAME, null);
//    }
//
//    /**
//     * 베스트 카테고리 클릭로그 삭제를 위한 날짜체크 값
//     */
//    public static void setBestCateClickLogDeleteDay(long day){
//        getInstance().edit().putLong(PREF_BEST_CATE_CLICK_LOG_DELETE_DAY, day).commit();
//    }
//
//    /**
//     * 베스트 카테고리 클릭로그 삭제를 위한 날짜체크 값
//     */
//    public static long getBestCateClickLogDeleteDay(){
//        return getInstance().getLong(PREF_BEST_CATE_CLICK_LOG_DELETE_DAY, -1);
//    }
//
//    public static void setBottomBarMarketOpenYn(boolean enable) {
//        getInstance().edit().putBoolean(PREF_IS_BOTTOMBAR_MARKET_OPEN, enable).commit();
//    }
//
//    public static boolean isBottomBarMarketOpenYn() {
//        return getInstance().getBoolean(PREF_IS_BOTTOMBAR_MARKET_OPEN, false);
//    }
//
//    /**
//     * 필터 메뉴 아이템 높이
//     * @param height
//     * @return
//     */
//    public static boolean setFilterMenuHeight(int height) {
//        return getInstance().edit().putInt(PREF_FILTER_MENU_HEIGHT, height).commit();
//    }
//
//    /**
//     * 필터 메뉴 아이템 높이
//     * @return
//     */
//    public static int getFilterMenuHeight() {
//        return getInstance().getInt(PREF_FILTER_MENU_HEIGHT, 0);
//    }
//
//    public static void setFilterChangedDelay(String delayString) {
//        long delay = -1;
//        try {
//            delay = Long.parseLong(delayString);
//        } catch (Exception e) { }
//        getInstance().edit().putLong(PREF_FILTER_CHANGED_DELAY, delay).commit();
//    }
//
//    public static long getFilterChangedDelay() {
//        long delay = getInstance().getLong(PREF_FILTER_CHANGED_DELAY, OnFilterChangedListener.DEFAULT_DELAY);
//        return delay != -1 ? delay : OnFilterChangedListener.DEFAULT_DELAY;
//    }
//
//    /**
//     * 자동로그인 백업데이터 버전 저장
//     */
//    public static void completeAccountsBackupMigration() {
//        getInstance().edit().putInt(PREF_ACCOUNTS_BACKUP_VERSION, CooChaConstants.ACCOUNTS_BACKUP_VERSION).commit();
//    }
//
//    public static int isAccountsBackupVersion() {
//        return getInstance().getInt(PREF_ACCOUNTS_BACKUP_VERSION, 0);
//    }
//
//    /**
//     * 내가 본 상품 리스트가 업데이트 된 시간을 저장
//     */
//    public static void setSeenProductUpdateDate(long date) {
//        getInstance().edit().putLong(PREF_SEEN_PRODUCT_UPDATE_DATE, date).commit();
//    }
//
//    public static long getSeenProductUpdateDate() {
//        return getInstance().getLong(PREF_SEEN_PRODUCT_UPDATE_DATE, 0);
//    }
//
//    /**
//     * 내가 본 상품 리스트 response 를 저장합니다
//     */
//    public static void setSeenProductList(String jsonObject) {
//        getInstance().edit().putString(PREF_SEEN_PRODUCT_LIST, jsonObject).commit();
//    }
//
//    public static String getSeenProductList() {
//        return getInstance().getString(PREF_SEEN_PRODUCT_LIST, "{}");
//    }
//
//    /**
//     * 스타일샵 상품 네이버페이 공통 url
//     */
//    public static void setStyleShopCpsNpayUrl(String data) {
//        getInstance().edit().putString(PREF_STYLE_SHOP_CPS_NPAY_URL, data).commit();
//    }
//
//    public static String getStyleShopCpsNpayUrl() {
//        return getInstance().getString(PREF_STYLE_SHOP_CPS_NPAY_URL, "");
//    }
//
//    /**
//     * 내가본 상품을 재호출하지 않는 최대 시간
//     */
//    public static void setSeenProductRefreshTime(long time) {
//        getInstance().edit().putLong(PREF_SEEN_PRODUCT_REFRESH_TIME, time).commit();
//    }
//
//    public static long getSeenProductRefreshTime() {
//        return getInstance().getLong(PREF_SEEN_PRODUCT_REFRESH_TIME, 60 * 60 * 1000);
//    }
//
//    /**
//     * 검색부분 내가본 상품을 보여줄지 여부
//     */
//    public static void setSearchSeenProductYn(boolean yn) {
//        getInstance().edit().putBoolean(PREF_SEARCH_SEEN_PRODUCT_YN, yn).commit();
//    }
//
//    public static boolean getSearchSeenProductYn() {
//        return getInstance().getBoolean(PREF_SEARCH_SEEN_PRODUCT_YN, true);
//    }
//
//    /**
//     * 홈 메뉴관리 기능 활성화 여부
//     */
//    public static void setMenuManagementYn(boolean yn) {
//        getInstance().edit().putBoolean(PREF_MENU_MANAGEMENT_YN, yn).commit();
//    }
//
//    public static boolean getMenuManagementYn() {
//        return getInstance().getBoolean(PREF_MENU_MANAGEMENT_YN, true);
//    }
//
//    /**
//     * 홈 메뉴관리 기능 최종 적용 시간
//     */
//    public static void setMenuManagementTime(long time) {
//        getInstance().edit().putLong(PREF_MENU_MANAGEMENT_TIME, time).commit();
//    }
//
//    public static long getMenuManagementTime() {
//        return getInstance().getLong(PREF_MENU_MANAGEMENT_TIME, 0);
//    }
//
//    /**
//     * 설정화면 홈 메뉴 관리기능 메뉴 New 아이콘 노출여부
//     */
//    public static void checkMenuManagementIsNew() {
//        getInstance().edit().putBoolean(PREF_MENU_MANAGEMENT_IS_NEW, false).commit();
//    }
//
//    public static boolean getMenuManagementIsNew() {
//        return getInstance().getBoolean(PREF_MENU_MANAGEMENT_IS_NEW, true);
//    }
//
//    /**
//     * 홈 메뉴관리 페이지 메뉴명 클릭시 랜딩을 위한 MID 임시 저장
//     */
//    public static void setMenuManagementHomeMid(String mid) {
//        if (TextUtils.isEmpty(mid)) {
//            getInstance().edit().remove(PREF_MENU_MANAGEMENT_HOME_MID).commit();
//        } else {
//            getInstance().edit().putString(PREF_MENU_MANAGEMENT_HOME_MID, mid).commit();
//        }
//    }
//
//    public static String getMenuManagementHomeMid(Context context) {
//        String mid = getInstance().getString(PREF_MENU_MANAGEMENT_HOME_MID, CommonDataManager.instance().getMainListData(context).get(0).mid);
//        setMenuManagementHomeMid(null);
//        return mid;
//    }
//
////    /**
////     * 가격비교상세 랜딩 AB 테스트 플래그
////     */
////    public static void setComparePriceViewYn(boolean yn) {
////        getInstance().edit().putBoolean(PREF_COMPARE_PRICE_VIEW_YN, yn).commit();
////    }
////
////    public static boolean getComparePriceViewYn() {
////        return getInstance().getBoolean(PREF_COMPARE_PRICE_VIEW_YN, false);
////    }
//
//}
