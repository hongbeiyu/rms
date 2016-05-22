
package rms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class rms {
	// 添加房产
	public void add(Room room) {
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "insert into t_room(code,address,size,price) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, room.getCode());
				pstmt.setString(2, room.getAddress());
				pstmt.setDouble(3, room.getSize());
				pstmt.setDouble(4, room.getPrice());
				pstmt.execute();
			}finally{
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	// 通过id删除房产 1002 1T "HELLO WORLD"
	public void deleteById(long id) {
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "delete from t_room where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				pstmt.executeUpdate();
			}finally{
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 通过id查询房产 ddl dml
	public Room queryById(long id) {
		Room room = null;
		try {
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql= "select * from t_room where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					String code = rs.getString("code");
					String address = rs.getString("address");
					double price = rs.getDouble("price");
					double size = rs.getDouble("size");
					room = new Room(id, code, address, size, price);
				}
			}finally{
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return room;
	}

	// 查看所有房产信息
	public List<Room> queryAll() {
		List<Room> list = new ArrayList<Room>();
		try {
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				conn = ConnectionFactory.getConn();
				String sql= "select * from t_room";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					long id = rs.getLong("id");
					String code = rs.getString("code");
					String address = rs.getString("address");
					double price = rs.getDouble("price");
					double size = rs.getDouble("size");
					Room room = new Room(id, code, address, size, price);
					list.add(room);
				}
			}finally{
				ConnectionFactory.close(rs, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 更新
	 */
	public void update(Room room) {
		try {
			Connection conn = null;
			PreparedStatement pstmt= null;
			try{
				conn = ConnectionFactory.getConn();
				String sql = "update t_room set code=?,address=?,size=?,price=? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, room.getCode());
				pstmt.setString(2, room.getAddress());
				pstmt.setDouble(3, room.getSize());
				pstmt.setDouble(4, room.getPrice());
				pstmt.setLong(5, room.getId());
				pstmt.executeUpdate();		
			}finally{
				ConnectionFactory.close(null, pstmt, conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 菜单
	public void menu() {
		System.out.println("********房产管理系统*******");
		System.out.println("*1，查看所有房产信息*");
		System.out.println("*2，添加房产信息*");
		System.out.println("*3，删除房产信息*");
		System.out.println("*4，查询房产信息*");
		System.out.println("*5，修改房产信息*");
		System.out.println("*exit，退出*");
		System.out.println("*help，帮助*");
		System.out.println("***************************");
	}
	
	public static void main(String[] args) {
		// 创建sms对象
		rms sms = new rms();
		sms.menu(); // 显示主页面
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print("请输入功能编号：");
				// 等待用户输入功能编号，等用户输入回车的时候获取回车前输入的内容
				String option = scanner.nextLine();
				switch (option) {
				case "1":// 查询所有
					System.out.println("以下是所有房产的信息：");
					List<Room> stus = sms.queryAll();
					for (Room stu : stus) {
						System.out.println(stu);
					}
					System.out.println("总计：" + stus.size() + " 套");
					break;
				case "2":// 添加
					while (true) {
						System.out.println("请输入房产信息【code#address#size#price】或者输入break回到上一级目录");
						String stuStr = scanner.nextLine();
						if (stuStr.equals("break")) {
							break;
						}
						String[] stuArr = stuStr.split("#");
						String code = stuArr[0];
						String address = stuArr[1];
						double size = Double.parseDouble(stuArr[2]);
						double price = Double.parseDouble(stuArr[3]);
						// 封装对象
						Room stu = new Room(null, code, address, size, price);
						sms.add(stu);
						System.out.println("添加成功！");
					}

					break;
				case "3":// 删除
					while (true) {
						System.out.print("请输入您要删除房产的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if (id.equals("break")) {
							break;
						}
						sms.deleteById(Long.parseLong(id));
						System.out.println("删除成功！");
					}
					break;
				case "4":// 查询
					while (true) {
						System.out.print("请输入您要查询房产的id或break返回上一级目录:");
						String id = scanner.nextLine();
						if (id.equals("break")) {
							break;
						}
						Room stu = sms.queryById(Long.parseLong(id));
						System.out.println("以下是您要查找的房产的信息：");
						System.out.println(stu != null ? stu : "not found!");
					}
					break;
				case "5":// 修改
					while (true) {
						System.out.print("请输入您要修改房产的id或break返回上一级目录:");
						String idStr = scanner.nextLine();
						if (idStr.equals("break")) {
							break;
						}
						//将字符串ID转换为Long
						long id = Long.parseLong(idStr);
						Room stu = sms.queryById(id);
						if (stu == null) {
							System.out.println("该房产不存在！");
							continue;
						}
						System.out.println("原信息为：" + stu);
						System.out.println("请输入您要修改的信息【code#address#size#price】");
						String stuStr = scanner.nextLine();
						String[] stuArr = stuStr.split("#");

						String code = stuArr[0];
						String address = stuArr[1];
						double size = Double.parseDouble(stuArr[2]);
						double price = Double.parseDouble(stuArr[3]);

						Room newStu = new Room(id, code, address, size, price);
						sms.update(newStu);
						System.out.println("修改成功！");
					}
					break;
				case "help":
					sms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					scanner.close();
					System.exit(0);
				default:
					System.out.println("输入出错，请重新输入！");
				}
				
			} catch (Exception e) {
				System.out.println("出错了！"+e.getMessage());
				continue;
			}
		}
	}
}
