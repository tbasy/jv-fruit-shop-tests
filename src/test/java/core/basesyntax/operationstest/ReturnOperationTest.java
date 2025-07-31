package core.basesyntax.operationstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.dao.FruitStorage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;
import core.basesyntax.service.operations.OperationHandler;
import core.basesyntax.service.operations.ReturnOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnOperationTest {
    private static OperationHandler operationHandler;

    @BeforeEach
    void setUp() {
        operationHandler = new ReturnOperation();
    }

    @AfterEach
    void clearStorage() {
        FruitStorage.storage.clear();
    }

    @Test
    public void apply_validTransaction_addsFruitToStorage_Ok() {
        FruitStorage.storage.put("apple", 10);
        FruitTransaction transaction = new FruitTransaction(Operation.RETURN, "apple", 5);
        operationHandler.apply(transaction);
        assertEquals(Integer.valueOf(15), FruitStorage.storage.get("apple"));
    }

    @Test
    public void apply_NullOperation_NotOk() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                operationHandler.apply(new FruitTransaction(null, "apple", 50))
        );
    }

    @Test
    public void apply_NullQuantity_CanNotAddQuantityToStorage_NotOk() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                operationHandler.apply(new FruitTransaction(Operation.BALANCE, "apple", null))
        );
    }

    @Test
    public void apply_NullFruit_CanNotAddFruitToStorage_NotOk() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                operationHandler.apply(new FruitTransaction(Operation.BALANCE, null, 50))
        );
    }

    @Test
    public void apply_NullTransaction_NotOk() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                operationHandler.apply(null)
        );
    }
}
