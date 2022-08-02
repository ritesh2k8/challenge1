package com.db.awmd.challenge.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Transfer {
	
	@NotNull
	private final String accountFrom ;
	
	@NotNull
	private final String accountTo ;
	
	@NotNull
	@Min(value = 0, message = "Transfer amount must be positive.")
    private BigDecimal amountToTransfer;
	
	public Transfer (@JsonProperty("accountFrom") String accountFrom,
			@JsonProperty("accountTo") String accountTo,
			@JsonProperty("amountToTransfer") BigDecimal amountToTransfer)
	{
		this.accountFrom = accountFrom ;
		this.accountTo = accountTo ;
		this.amountToTransfer = amountToTransfer ;
	}

}
