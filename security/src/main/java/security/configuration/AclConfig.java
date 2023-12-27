package security.configuration;

//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class AclConfig extends GlobalMethodSecurityConfiguration {
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public AclConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public PermissionGrantingStrategy permissionGrantingStrategy() {
//        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
//    }
//
//    @Bean
//    public AclAuthorizationStrategy aclAuthorizationStrategy() {
//        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
//
//    @Bean
//    public EhCacheManagerFactoryBean aclCacheManager() {
//        return new EhCacheManagerFactoryBean();
//    }
//
//    @Bean
//    public EhCacheFactoryBean aclEhcacheFactoryBean() {
//        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
//        ehCacheFactoryBean.setCacheManager(Objects.requireNonNull(aclCacheManager().getObject()));
//        ehCacheFactoryBean.setCacheName("aclCache");
//        return ehCacheFactoryBean;
//    }
//
//    @Bean
//    public EhCacheBasedAclCache aclCache() {
//        return new EhCacheBasedAclCache(aclEhcacheFactoryBean().getObject(),
//                permissionGrantingStrategy(), aclAuthorizationStrategy());
//    }
//
//    @Bean
//    public LookupStrategy lookupStrategy() {
//        return new BasicLookupStrategy(dataSource, aclCache(),
//                aclAuthorizationStrategy(), new ConsoleAuditLogger());
//    }
//
//    @Bean
//    public JdbcMutableAclService aclService() {
//        return new JdbcMutableAclService(dataSource, lookupStrategy(), aclCache());
//    }
//
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());
//        expressionHandler.setPermissionEvaluator(permissionEvaluator);
//        expressionHandler.setPermissionCacheOptimizer(new AclPermissionCacheOptimizer(aclService()));
//        return expressionHandler;
//    }
//}
