package com.sda.eshop.config;


public class DbConfig {

    private String driver;
    private String url;
    private String dialect;
    private String hbm2DdlAuto;
    private String currentSessionContextClass;
    private String user;
    private String pass;
    private String showSql;

    public DbConfig() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getHbm2DdlAuto() {
        return hbm2DdlAuto;
    }

    public void setHbm2DdlAuto(String hbm2DdlAuto) {
        this.hbm2DdlAuto = hbm2DdlAuto;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    public void setCurrentSessionContextClass(String currentSessionContextClass) {
        this.currentSessionContextClass = currentSessionContextClass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

}
