package dev.intermediatebox.kafka.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PurchaseResponse {

	private String purchaseNumber;
}
