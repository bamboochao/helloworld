package com.study.gitfirst.hbase;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;

public class HBaseTest {
	private static HConnection conn = null;
	/*
	 * static { Configuration conf = new Configuration();
	 * conf.set("hbase.zookeeper.quorum", "hadooptest:2181"); try { conn =
	 * HConnectionManager.createConnection(conf); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */
	
	public static void createTable(String tableName, String[] familys) {
		
		HBaseAdmin admin = null;
		try {
			admin = new HBaseAdmin(conn);
			if(admin.tableExists(tableName)) {
				System.out.println("table already exists!");
			}else {
				TableName name = TableName.valueOf(tableName);
				HTableDescriptor desc = new HTableDescriptor(name);
				HTableDescriptor tableDesc = new HTableDescriptor(desc);    
                for(int i=0; i<familys.length; i++){    
                    tableDesc.addFamily(new HColumnDescriptor(familys[i]));    
                }    
                admin.createTable(tableDesc);    
                System.out.println("create table " + tableName + " ok.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(admin!=null) {
				try {
					admin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void deleteOneRecordColumn(String tableName, String rowKey, String family, String qualifier) {
		try {
			HTableInterface table = conn.getTable(tableName);
			table.setAutoFlushTo(false);
			Delete delete = new Delete(rowKey.getBytes());
			delete.deleteColumn(family.getBytes(), qualifier.getBytes());
			table.delete(delete);
			table.flushCommits();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteOneRecord(String tableName, String rowKey, String family) {
		try {
			HTableInterface table = conn.getTable(tableName);
			table.setAutoFlushTo(false);
			Delete delete = new Delete(rowKey.getBytes());
			delete.deleteFamily(family.getBytes());
			table.delete(delete);
			table.flushCommits();
			table.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putOneRecord(String tableName, String rowKey, String family, String qualifier, String value) {
		try {
			HTableInterface table = conn.getTable(tableName);
			table.setAutoFlushTo(false);
			Put put = new Put(rowKey.getBytes());
			put.add(family.getBytes(), qualifier.getBytes(), value.getBytes());
			table.put(put);
			table.flushCommits();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void getOneRecordByQualifier(String tableName, String rowKey, String family, String qualifier) {
		try {
			HTableInterface table = conn.getTable(tableName);
			Get get = new Get(rowKey.getBytes());
			Result result = table.get(get);
			List<Cell> list = result.getColumnCells(family.getBytes(), qualifier.getBytes());
			for(Cell cell : list) {
       		 System.out.print(new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + " ");    
                System.out.print(new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) + ":");    
                System.out.print(new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + " ");    
                System.out.print(cell.getTimestamp() + " ");    
                System.out.println(new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())); 
       	 }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getOneRecord(String tableName, String rowKey) {
		try {
			HTableInterface table = conn.getTable(tableName);
			Get get = new Get(rowKey.getBytes());
			Result result = table.get(get);
			for(Cell cell :result.rawCells()) {
       		 System.out.print(new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + " ");    
                System.out.print(new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) + ":");    
                System.out.print(new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + " ");    
                System.out.print(cell.getTimestamp() + " ");    
                System.out.println(new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())); 
       	 }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getSpecialValueRecord(String tableName, String family, String qualifier, CompareOp op, String value) {
		HTableInterface table = null;
		try {
			table = conn.getTable(tableName);
			Scan scan = new Scan();
			SingleColumnValueFilter filter = new SingleColumnValueFilter(family.getBytes(), qualifier.getBytes(), op, value.getBytes());
			filter.setFilterIfMissing(false);
			scan.setFilter(filter);
			
			ResultScanner scanner = table.getScanner(scan);
			printResult(scanner);
			/*
			 * for(Result result:scanner) {
			 * System.out.println(Bytes.toString(result.getValue(family.getBytes(),
			 * qualifier.getBytes()))); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(table!=null) {
				try {
					table.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**  
     * 显示所有数据  
     */    
     public static void getAllRecord (String tableName) {    
         try{    
        	 System.out.println("get a table!");
        	 //通过连接获取一个表
        	 HTableInterface table = conn.getTable(tableName);
             Scan s = new Scan();
             ResultScanner ss = table.getScanner(s);    
             
             printResult(ss); 
         } catch (IOException e){    
             e.printStackTrace();    
         }    
     }
     
     private static void printResult(ResultScanner scanner) {
    	 for(Result r:scanner){
    		 
        	 // new API
        	 for(Cell cell :r.rawCells()) {
        		 System.out.print(new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + " ");    
                 System.out.print(new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) + ":");    
                 System.out.print(new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + " ");    
                 System.out.print(cell.getTimestamp() + " ");    
                 System.out.println(new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength())); 
        	 }
			/* // old API
			 * for(KeyValue kv : r.raw()){ System.out.print(new String(kv.getRow()) + " ");
			 * System.out.print(new String(kv.getFamily()) + ":"); System.out.print(new
			 * String(kv.getQualifier()) + " "); System.out.print(kv.getTimestamp() + " ");
			 * System.out.println(new String(kv.getValue())); }
			 */   
         }  
     }
	
	public static void main(String[] args) {
		String tableName = "stu";
		getAllRecord(tableName);
		
		String rowKey = "rowkey002";
		getOneRecord(tableName, rowKey);
		
		String family = "info";
		String qualifier = "name";
		getOneRecordByQualifier(tableName, rowKey, family, qualifier);
		
		String value = "wangwu";
		putOneRecord(tableName, rowKey, family, qualifier, value);
		
		deleteOneRecord(tableName, rowKey, family);
		tableName = "life";
		String[] qualifierArr = new String[]{"work","learn"}; 
		createTable(tableName, qualifierArr);
		rowKey = "rowkey001";
		family = "work";
		qualifier = "hbase";
		value = "crud";
		putOneRecord(tableName, rowKey, family, qualifier, value);
		getOneRecordByQualifier(tableName, rowKey, family, qualifier);
		
		tableName = "life:work";
		putOneRecord(tableName, "rowKey001", "techonology", "HBase", "easy");
		putOneRecord(tableName, "rowKey001", "communicate", "talk", "fluently");
		putOneRecord(tableName, "rowKey002", "techonology", "HBase", "code");
		putOneRecord(tableName, "rowKey002", "communicate", "talk", "speaker");
		getAllRecord(tableName);
		getSpecialValueRecord(tableName, "techonology", "HBase", CompareOp.GREATER_OR_EQUAL, "easy");
	}
}
