package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.Operation;
import core.basesyntax.service.operations.OperationHandler;

public class ShopServiceImpl implements ShopService {
    private final OperationStrategy operationStrategy;

    public ShopServiceImpl(OperationStrategy operationStrategy) {

        this.operationStrategy = operationStrategy;
    }

    @Override
    public void transfer(FruitTransaction transaction) {
        Operation operation = transaction.getOperation();
        OperationHandler handler = operationStrategy.get(operation);
        if (handler == null) {
            throw new RuntimeException("No handler found for operation: " + operation);
        }
        if (transaction.getFruit() == null) {
            throw new IllegalArgumentException("Fruit cannot be null");
        }
        if (transaction.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        handler.apply(transaction);
    }
}
