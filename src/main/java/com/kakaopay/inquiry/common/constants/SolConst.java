package com.kakaopay.inquiry.common.constants;

public class SolConst {
    /* AUTH */
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final int EXPIRE_TIME = 864000000;

    /* ROLE */
    public static final String ROLE_USER = "USER";
    public static final String ROLE_MANAGER = "MANAGER";

    /* INQUIRY STATUS */
    public static final String NOT_ASSIGNED = "NOT_ASSIGNED";
    public static final String ASSIGNED = "ASSIGNED";
    public static final String REPLY_COMPLETE = "REPLY_COMPLETE";
}
