package moc.oreh.service;

import moc.oreh.dao.PhysicalPayConfigDao;
import moc.oreh.dao.imp.PhysicalPayConfigDaoImp;
import moc.oreh.domain.PhisicalPayConfig;

import java.util.LinkedList;

/**
 * Created by hero on 17-7-15.
 */
public class PhysicalPayConfigService {
    public static void main(String... args){
        PhysicalPayConfigDao dao = new PhysicalPayConfigDaoImp();
        LinkedList<PhisicalPayConfig> list = dao.listByProductCode("44332211");
    }
}
