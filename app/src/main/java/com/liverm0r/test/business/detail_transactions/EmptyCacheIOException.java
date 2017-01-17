package com.liverm0r.test.business.detail_transactions;


import java.io.IOException;

/**
 * In casual cases it shouldn't happen
 * cause Transactions ans DetailTransactions share common Dagger Singleton scope.
 *
 * It may happen, when app is stopped and relaunched after some time;
 * but it this case it may be reasonable to update data.
 */
public class EmptyCacheIOException extends IOException {
}
