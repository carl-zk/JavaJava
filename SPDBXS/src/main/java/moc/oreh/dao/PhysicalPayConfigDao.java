package moc.oreh.dao;

import moc.oreh.domain.PhisicalPayConfig;

import java.util.LinkedList;

/**
 * Created by hero on 17-7-15.
 */
public interface PhysicalPayConfigDao {
    void save(PhisicalPayConfig config);
    LinkedList<PhisicalPayConfig> listByProductCode(String productCode);
}
