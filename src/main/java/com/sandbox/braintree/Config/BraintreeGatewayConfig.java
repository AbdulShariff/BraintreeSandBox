package com.sandbox.braintree.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.braintreegateway.BraintreeGateway;

@Configuration
public class BraintreeGatewayConfig {
		
		private static final Logger LOG = LoggerFactory.getLogger(BraintreeGatewayConfig.class);
		
		@Autowired
		private BraintreeGatewayProperties braintreeGatewayProperties;
		
		public BraintreeGateway getBraintreeGatewayInstance() {
			BraintreeGateway braintreeGateway = new BraintreeGateway(
						braintreeGatewayProperties.getEnvironment(),
						braintreeGatewayProperties.getMerchantId(),
						braintreeGatewayProperties.getPublicKey(),
						braintreeGatewayProperties.getPrivateKey()
				);
				
				LOG.info("Braintree Gateway Instance : {}", braintreeGateway);
				return braintreeGateway;
		}
}
