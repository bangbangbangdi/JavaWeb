package com.bangdi.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class Demo01 {

    public static class FruitDAO {
        private Connection conn;
        private PreparedStatement psmt;

        public FruitDAO() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigudb", "root", "abc123");
        }

        public int insert(Fruit fruit) throws SQLException, ClassNotFoundException {
            String sql = "insert into t_fruit values(?,?,?,?,?)";
            PreparedStatement psmt = this.conn.prepareStatement(sql);
            psmt.setInt(1, fruit.getFid());
            psmt.setString(2, fruit.getFname());
            psmt.setInt(3, fruit.getPrice());
            psmt.setInt(4, fruit.getFcount());
            psmt.setString(5, fruit.getRemark());
            int count = psmt.executeUpdate();
            System.out.println("插入影响行数 : " + count);
            psmt.close();
            conn.close();
            return count;
        }

        public int update(Fruit fruit) throws SQLException {
            String sql = "update t_fruit set fname = ?,price = ?,fcount = ?,remark = ? where fid = ?";
            PreparedStatement psmt = this.conn.prepareStatement(sql);
            psmt.setString(1, fruit.getFname());
            psmt.setInt(2, fruit.getPrice());
            psmt.setInt(3, fruit.getFcount());
            psmt.setString(4, fruit.getRemark());
            psmt.setInt(5, fruit.getFid());
            int count = psmt.executeUpdate();
            psmt.close();
            conn.close();
            return count;
        }

        public int delete(int fid) throws SQLException {
            String sql = "delete from t_fruit where fid = ?";
            PreparedStatement psmt = this.conn.prepareStatement(sql);
            psmt.setInt(1, fid);
            int delCount = psmt.executeUpdate();
            psmt.close();
            conn.close();
            return delCount;
        }

        public ArrayList<Fruit> selectAll() throws SQLException {
            String sql = "select * from t_fruit";
            PreparedStatement psmt = this.conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            ArrayList<Fruit> ans = new ArrayList<>();
            while (rs.next()) {
                int fId = rs.getInt(1);
                String fName = rs.getString(2);
                int price = rs.getInt(3);
                int fCount = rs.getInt(4);
                String remark = rs.getString(5);
                Fruit fruit = new Fruit(fId, fName, price, fCount, remark);
                ans.add(fruit);
            }
            rs.close();
            psmt.close();
            conn.close();
            return ans;
        }

    }


    public static void test() throws SQLException, ClassNotFoundException {
        Fruit fruit = new Fruit(11, "葡萄", 15, 333, "葡萄葡萄噗噗噗");
        FruitDAO dao = new FruitDAO();
//        dao.insert(fruit);
//        fruit.setFname("龙眼");
//        fruit.setRemark("龙之还乡");
//        int upCount = dao.update(fruit);
//        System.out.println("更新行数 : " + upCount);
//        int delete = dao.delete(333);
//        System.out.println("删除行数 : " + delete);
        ArrayList<Fruit> fruits = dao.selectAll();
        fruits.forEach(System.out::println);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        test();
    }

}
