package org.ocupatucalle.resource.config;

import static org.ocupatucalle.resource.config.SecurityConfig.SIGNING_KEY;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIENT_ID = "otc";
	static final String GRANT_TYPE = "password";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String RESOURCES_IDS = "otcresourceid";

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManagerBean;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(SIGNING_KEY)
				.authorizedGrantTypes(GRANT_TYPE, 
				"authorization_code", "refresh_token", "implicit", "client_credentials")
				.scopes(SCOPE_READ, SCOPE_WRITE, "trust", "openid")
				.resourceIds(RESOURCES_IDS)
				.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).tokenEnhancer(enhancerChain)
				.authenticationManager(authenticationManagerBean);
	}

}
