package com.spy13.financemanager.dao;

public class DaoException extends Exception {
    private static final String ROW_NUMBER_IS_NOT_1 = "The number of rows updated in the database is not 1";

    public DaoException() {
    }

    public DaoException(String detailMessage) {
        super(detailMessage);
    }

    public DaoException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }

    public static DaoException rowNumberIsNot1() {
        return new DaoException(ROW_NUMBER_IS_NOT_1);
    }
}
