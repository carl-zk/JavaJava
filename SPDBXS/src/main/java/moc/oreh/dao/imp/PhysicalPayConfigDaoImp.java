package moc.oreh.dao.imp;

import com.hero.test.common.DBUtils;
import com.hero.test.common.StringUtils;
import moc.oreh.dao.PhysicalPayConfigDao;
import moc.oreh.domain.PhisicalPayConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 * Created by hero on 17-7-15.
 */
public class PhysicalPayConfigDaoImp implements PhysicalPayConfigDao {
    public void save(PhisicalPayConfig config) {
        if (StringUtils.isNullOrEmpty(config.getId())) {      // 新建，插入

        } else {     // 更新

        }
    }

    public LinkedList<PhisicalPayConfig> listByProductCode(String productCode) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement("select * from physical_pay_config where productCode=?");
            stmt.setString(1, productCode);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            System.out.println("done");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtils.closeAll(resultSet, stmt, conn);
        }
        return null;
    }
}
