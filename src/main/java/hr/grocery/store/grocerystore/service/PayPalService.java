package hr.grocery.store.grocerystore.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PayPalService {
    Payment createPayment(
            Double total,
            String currency,
            String description,
            String cancelUrl,
            String successUrl
    ) throws PayPalRESTException;

    Payment doPayment
            (String paymentId, String payerId)
            throws PayPalRESTException;
}
