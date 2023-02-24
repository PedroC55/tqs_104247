package com.example.project;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTests {
    

    @Mock
    IStockmarketService stockmarket= mock(IStockmarketService.class);

    @InjectMocks
    StocksPortfolio portfolio;


    @AfterEach
    public void clear() {
        portfolio.clear();
    }

    @Test
    void getTotalValueTest(){
        portfolio.addStock(new Stock("EBAY", 10));
        portfolio.addStock(new Stock("MCST", 5));
        portfolio.addStock(new Stock("APPLE", 20));


        when(stockmarket.lookUpPrice("EBAY")).thenReturn(4.0);
        when(stockmarket.lookUpPrice("MCST")).thenReturn(2.5);
        when(stockmarket.lookUpPrice("APPLE")).thenReturn(7.0);

        double result = 4*10 + 2.5*5 + 7*20;

        assertThat(portfolio.getTotalValue(), is(result));

        verify(stockmarket, times(3)).lookUpPrice(anyString());


    
    }
}
