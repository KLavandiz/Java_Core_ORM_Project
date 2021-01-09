package com.Jorm.Dal.Mysql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Field;

public class MysqlDatabaseEngine {
	// connection string for mysql database connection
	private String connectionString = "jdbc:mysql://localhost:3306/chat?serverTimezone=UTC";
	private String username = "root";
	private String password = "56835683";
	private int result = 0;
	private ResultSet resultQuery;
	private IEntity JormObject;

	public MysqlDatabaseEngine(IEntity obj) {
		this.JormObject = obj;
	}

	public int runUpdateQuery(String query) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DriverManager.getConnection(this.connectionString, this.username, this.password);
			ps = conn.prepareStatement(query);
			// System.out.println(query);
			this.result = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		return this.result;
	}

	public ArrayList<IEntity> runExecuteQuery(String query) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<IEntity> lime = new ArrayList<IEntity>();
		System.out.println(query);

		try {
			conn = DriverManager.getConnection(this.connectionString, this.username, this.password);
			// System.out.println(query);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();

			ResultSetMetaData rsmd = rs.getMetaData();

			IEntity clas = null;

			try {
				int j = 1;
				while (rs.next()) {

					try {
						clas = (IEntity) this.JormObject.getClass().getDeclaredConstructor().newInstance();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (int i = 0; i <= columnCount - 1; i++) {

						try {
							Field fld = clas.getClass().getDeclaredField(rsmd.getColumnName(i + 1));
							fld.setAccessible(true);

							if (fld.getType().getSimpleName() == "int") {
								try {

									fld.setInt(clas, rs.getInt(i + 1));
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else if (fld.getType().getSimpleName().toLowerCase() == "string") {
								try {
									fld.set(clas, rs.getString(i + 1));
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else if (fld.getType().getSimpleName().toLowerCase() == "long") {

								try {
									fld.setLong(clas, rs.getLong(i + 1));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}

							} else if (fld.getType().getSimpleName().toLowerCase() == "double") {

								try {
									fld.setDouble(clas, rs.getDouble(i + 1));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}

							} else if (fld.getType().getSimpleName().toLowerCase() == "float") {

								try {
									fld.setFloat(clas, rs.getFloat(i + 1));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}

							} else if (fld.getType().getSimpleName().toLowerCase() == "float") {

								try {
									fld.setFloat(clas, rs.getFloat(i + 1));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}

							} else {

								try {
									fld.set(clas, String.class.cast(rs.getString(i + 1)));
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}

							}

						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					lime.add(clas);
					clas = null;
					j++;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println( this.resultQuery.getString(5));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				ps.close();
				rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		System.gc();
		return lime;
	}

	public ArrayList<Object[]> runExecuteCount(String query) {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		ArrayList<Object[]> hotels = new ArrayList<Object[]>();

		try {
			conn = DriverManager.getConnection(this.connectionString, this.username, this.password);
			// System.out.println(query);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();

			ResultSetMetaData rsmd = rs.getMetaData();

			try {
				while (rs.next()) {
					String[] metastr = new String[columnCount];
					for (int i = 0; i <= columnCount - 1; i++) {
						metastr[i] = rs.getString(i + 1);
					}
					hotels.add(metastr);
					metastr = null;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println( this.resultQuery.getString(5));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				ps.close();
				rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		return hotels;
	}

	public ArrayList<IQuery> runQuery(String query, IQuery iq, IEntity[] entityArray) {

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<IQuery> lime = new ArrayList<IQuery>();

		try {
			conn = DriverManager.getConnection(this.connectionString, this.username, this.password);

			ps = conn.prepareStatement(query);

			rs = ps.executeQuery();

			int columnCount = rs.getMetaData().getColumnCount();
			// rs.getMetaData().getColumnLabel(columnCount)

			ResultSetMetaData rsmd = rs.getMetaData();

			IQuery dynamicQuery = null;

			while (rs.next()) {

				try {
					dynamicQuery = (IQuery) iq.getClass().getDeclaredConstructor().newInstance();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				IEntity dynamicIEntity = null;

				for (IEntity forEntity : entityArray) {

					try {

						dynamicIEntity = (IEntity) forEntity.getClass().getDeclaredConstructor().newInstance();

					} catch (Exception e) {
						e.printStackTrace();
					}

					for (Field forField : dynamicIEntity.getClass().getDeclaredFields()) {
						forField.setAccessible(true);

						for (int i = 0; i <= columnCount - 1; i++)

						{

							if (((forField.getName().endsWith("_cn")) || (forField.getName().endsWith("_pm")))
									&& (!checkEntity(forField))) {

								String tablePrefix = dynamicIEntity.getClass().getSimpleName().toLowerCase() + "_"
										+ forField.getName();

								String columnName = rsmd.getColumnLabel(i + 1);

								if (columnName.equals(tablePrefix)) {

									// System.out.println(tablePrefix);
									// System.out.println(dynamicIEntity.getClass().getSimpleName());

									if (forField.getType().getSimpleName() == "int") {
										try {

											forField.setInt(dynamicIEntity, rs.getInt(i + 1));
										} catch (Exception e) {
										}

									} else if (forField.getType().getSimpleName() == "long") {
										try {

											forField.setLong(dynamicIEntity, rs.getLong(i + 1));

										} catch (Exception e) {
										}

									} else if (forField.getType().getSimpleName() == "float") {
										try {

											forField.setFloat(dynamicIEntity, rs.getFloat(i + 1));

										} catch (Exception e) {
										}

									} else if (forField.getType().getSimpleName() == "double") {
										try {

											forField.setDouble(dynamicIEntity, rs.getDouble(i + 1));

										} catch (Exception e) {
										}

									} else if (forField.getType().getSimpleName().toLowerCase().equals("string")) {
										try {
											forField.set(dynamicIEntity, rs.getString(i + 1));

										} catch (Exception e) {
										}

									}

								}

							}

						}

					}

					// System.out.println(+"="+rs.getString(i+1));

					Field[] qryf = dynamicQuery.getClass().getDeclaredFields();

					for (Field frw : qryf) {

						frw.setAccessible(true);

						if (frw.getType().getSimpleName().equals(dynamicIEntity.getClass().getSimpleName())) {
							try {
								frw.set(dynamicQuery, dynamicIEntity);
							} catch (Exception e) {
							}
						}

					}

					dynamicIEntity = null;
				}

				lime.add(dynamicQuery);

				dynamicQuery = null;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				ps.close();
				rs.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}

		System.gc();

		return lime;
	}

	private boolean checkEntity(Field field) { // Check a field has an IEntity implements

		boolean result = false;

		for (int i = 0; i < field.getType().getInterfaces().length; i++) {

			if (field.getType().getInterfaces()[i].getTypeName().contains("IEntity")) {
				result = true;
			}
		}

		return result;
	}

}
