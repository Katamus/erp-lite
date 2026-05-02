package com.criscahub.erp_lite;

import com.criscahub.erp_lite.domain.order.OrderId;
import com.criscahub.erp_lite.domain.shared.Email;
import com.criscahub.erp_lite.domain.shared.Money;
import com.criscahub.erp_lite.persistence.mail.adapters.GmailAdapter;
import com.criscahub.erp_lite.persistence.rest.adapters.JsonPlaceholderCustomerProviderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Currency;

@SpringBootApplication
public class ErpLiteApplication implements CommandLineRunner{

	@Autowired
	private GmailAdapter gmailAdapter;

	public static void main(String[] args) {
		SpringApplication.run(ErpLiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Email email = Email.of("christian.catamuscay.h@gmail.com");
		OrderId orderId = OrderId.generate();
		String orderNumber = "ASD-QWEQW-567";
		Money money = Money.of(new BigDecimal("2999.98"), Currency.getInstance("USD"));
		String customerName = "Popi Hurtado";
		int itemsCount = 10;
		this.gmailAdapter.sendMail(
				email,orderId,orderNumber,money,customerName, itemsCount
		);
	}
}
