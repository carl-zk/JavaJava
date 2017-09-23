package moc.oreh.domain;

import java.io.Serializable;

/**
 * Created by hero on 17-7-15.
 */
public class PhisicalPayConfig implements Serializable {
    private static final long serialVersionUID = -3801207497637661445L;
    private String id;
    private String productCode;
    private String fieldCode;
    private String fieldName;
    private int order;
    private char flag;

    protected PhisicalPayConfig() {
    }

    public PhisicalPayConfig(String productCode, String fieldCode, String fieldName) {
        this.productCode = productCode;
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public char getFlag() {
        return flag;
    }

    public void setFlag(char flag) {
        this.flag = flag;
    }
}
