package co.edu.unisabana.parcialarquitectura.service;

import co.edu.unisabana.parcialarquitectura.repository.IDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BuyTest {

  @InjectMocks
  private Buy service;

  @Mock
  private IDatabase database;

  @Test
  public void Given_buyerCode_equals_vendorCode_When_makePurchase_Then_throw_IllegalSaleException(){
    int vendorCode = 4;
    int buyerCode = vendorCode;
    String item = "Producto x";

    assertThrows(IllegalSaleException.class, ()->{
      service.makePurchase(vendorCode, buyerCode, item);
    });

  }

  @Test
  public void Given_buyerCode_not_equals_vendorCode_and_When_makePurchase_return_1_Then_return_product_sold_message(){
    int vendorCode = 4;
    int buyerCode = 2;
    String item = "Producto y";
    Mockito.when(database.savePurchase(buyerCode, item)).thenReturn(1);
    String result = service.makePurchase(vendorCode, buyerCode, item);
    assertEquals("Product sold", result);

  }

  @Test
  public void Given_buyerCode_not_equals_vendorCode_and_When_makePurchase_return_0_Then_return_sale_not_possible_message(){
    int vendorCode = 7;
    int buyerCode = 9;
    String item = "Producto z";
    Mockito.when(database.savePurchase(buyerCode, item)).thenReturn(0);
    String result = service.makePurchase(vendorCode, buyerCode, item);
    assertEquals("The sale was not possible", result);

  }
}