package com.sandbox.braintree.Config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "braintree.gateway")
public class BraintreeGatewayProperties {
		
		private static final Logger LOG = LoggerFactory.getLogger(BraintreeGatewayProperties.class);
		
		private String environment;
		private String publicKey;
		private String privateKey;
		private String merchantId;
		
		public String getEnvironment() {
				return environment;
		}
		
		public void setEnvironment(String environment) {
				this.environment = environment;
		}
		
		public String getPublicKey() {
				return publicKey;
		}
		
		public void setPublicKey(String publicKey) {
				this.publicKey = publicKey;
		}
		
		public String getPrivateKey() {
				return privateKey;
		}
		
		public void setPrivateKey(String privateKey) {
				this.privateKey = privateKey;
		}
		
		public String getMerchantId() {
				return merchantId;
		}
		
		public void setMerchantId(String merchantId) {
				this.merchantId = merchantId;
		}
		
		@Override
		public String toString() {
				return "BraintreeGatewayConfig{" +
						"environment='" + environment + '\'' +
						", publicKey='" + publicKey + '\'' +
						", privateKey='" + privateKey + '\'' +
						", merchantId='" + merchantId + '\'' +
						'}';
		}
		
		@PostConstruct
		public void braintreeGatewayConfigValues(){
				LOG.info("Braintree Gateway Config : {}", this);
		}
}
