package controller;

public interface ApiPath {

    //Syntax
    String SLASH = "/";

    //Prefixes
    String API = "api";
    String SEARCH_PRODUCT_SERVICE = "search-product";

    //Data nodes
    String PRODUCT = SLASH + API + SLASH + SEARCH_PRODUCT_SERVICE + SLASH + "product";


}
