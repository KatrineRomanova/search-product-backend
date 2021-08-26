package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;


@Entity
public class Product {

    @Id
    @GeneratedValue
    private BigInteger id;

    private BigInteger vendorCode;

    private String productName;

    private BigInteger amountRemain;

    private String typeRemain;

    private String photo;

    private BigInteger price;

}
