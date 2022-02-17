package com.geyy.config;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;*/

/**
 * @author geyaowei
 * @title ResouceServerConfig
 * 需要继承ResourceServerConfigurerAdapter类并需要使用@EnableResourceServer注解注释
 * @description: TODO
 * @since 2022/2/16 14:20
 */
//@Configuration
//@EnableResourceServer
public class ResouceServerConfig{ //extends ResourceServerConfigurerAdapter {
/*

    private static final String RESOURCE_ID= "r1";

    //@Autowired
    //private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private TokenStore tokenStore;*/
/**引入jwt**//*


*/
/*    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:30000/oauth/check_token");
        remoteTokenServices.setClientId("c1");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }*//*


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(RESOURCE_ID)//resourceId方法标志了该服务的id，需要和在auth-center服务中配置的id一致
                .tokenStore(tokenStore)*/
/**引入jwt**//*

                //.tokenServices(resourceServerTokenServices)//令牌服务
                .stateless(true);//指定了当前资源是否仅仅允许token验证的方法进行校验，默认为true
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")//表示所有的请求携带的令牌都必须拥有all的授权范围，其中all授权范围必须和认证服务中的配置相一致。
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

*/

}
