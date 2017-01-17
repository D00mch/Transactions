package com.liverm0r.test.business.transactions;

import com.liverm0r.test.business.currency.CurrencyData;
import com.liverm0r.test.business.transactions.products_fabric.IProductsFabric;
import com.liverm0r.test.data.model.Product;
import com.liverm0r.test.data.repositories.currency.ICurrencyQueryRepo;
import com.liverm0r.test.data.repositories.detail_transactions.IProductHolderRepo;
import com.liverm0r.test.ui.transactions.TransactionsModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TransactionsInteractorTest {

    TransactionsInteractor mTransactionsInteractor;
    ICurrencyQueryRepo mCurrRepo;
    IProductHolderRepo mHolderRepo;
    IProductsFabric mFabric;

    @Before
    public void setUp() throws Exception {

        mCurrRepo = Mockito.mock(ICurrencyQueryRepo.class);
        mHolderRepo = Mockito.mock(IProductHolderRepo.class);
        mFabric = Mockito.mock(IProductsFabric.class);

        mTransactionsInteractor = new TransactionsInteractor(mCurrRepo, mHolderRepo, mFabric);

        when(mCurrRepo.getRates()).thenReturn(Single.just(CurrencyData.sRates));
        when(mCurrRepo.getTransactions()).thenReturn(Single.just(CurrencyData.sTransactions));
    }

    @Test public void caching_sku() throws Exception {
        String sku = "some sku";
        mTransactionsInteractor.skuChosen(sku);
        verify(mHolderRepo).setCurrentSku(sku);
    }

    @Test
    public void get_transactions_returning_expected_result() throws Exception {

        Product product = new Product();
        product.setSku("fqewg");
        product.setTransactions(new ArrayList<>());
        ArrayList<Product> products = new ArrayList<Product>() {
            {
                add(product);
            }
        };
        when(mFabric.get(CurrencyData.sTransactions, CurrencyData.sRates)).thenReturn(products);

        TestObserver<List<TransactionsModel>> listTestObserver = new TestObserver<>();
        mTransactionsInteractor.getTransactions().subscribe(listTestObserver);
        listTestObserver.assertValue(models -> {
            if (!models.get(0).getSku().equals(product.getSku())) {
                throw new AssertionError("Unexpected results");
            }
            return true;
        });
        listTestObserver.assertNoErrors();
        listTestObserver.assertComplete();

        verify(mHolderRepo).setProducts(products);
    }
}