package com.utils;

import java.io.BufferedReader;
import java.io.FileReader;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.text.SimpleDateFormat;


public class ReadCsv {

    public static void main(String[] args) {
        /*
         * ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据 //
         * 生成CsvReader对象，以，为分隔符，GBK编码方式 CsvReader reader = new CsvReader(csvFilePath,
         * ',', Charset.forName("gbk")); // 一般用这编码读就可以了 reader.readHeaders(); // 跳过表头
         * 如果需要表头的话，不要写这句。 // 逐条读取记录，直至读完 while (reader.readRecord()) {
         * csvList.add(reader.getValues()); } reader.close();
         */

        // ------------
        Connection conn = JdbcUtil.getconn();
        PreparedStatement ps;
        String sql = "insert into  BRCH_QRY_DTL (acc,tran_date,amt,dr_cr_flag,rpt_sum,timestampl) values(?,?,?,?,?,?)";
        // "VALUES
        // ("+acc+","+tran_date2+","+amt2+","+Integer.parseInt(dr_cr_flag)+","+rpt_sum+","+timestampl+")";
        System.out.println(sql);
        try {
            ps = conn.prepareStatement(sql);
            BufferedReader readerc = new BufferedReader(new FileReader("e://myapp//data.csv"));// 换成你的文件名
            // readerc.readLine();// 第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while ((line = readerc.readLine()) != null) {
                String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String tran_date = item[0];// 第一行
                String timestampl = item[1];// 第二行
                String acc = item[2];// 第三行
                String amt = item[3];// 第四行
                String dr_cr_flag = item[4];// 第五行
                String rpt_sum = item[item.length - 1];// 最后一行
                // int value =
                // Integer.parseInt(last);//如果是数值，可以转化为数636f70797a686964616f31333339663964值
                System.out.println(tran_date);
                System.out.println(timestampl);
                System.out.println(acc);
                System.out.println(amt);
                System.out.println(dr_cr_flag);
                System.out.println(rpt_sum);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date tran_date2 = formatter.parse(tran_date);
                Date tran_date3 = new java.sql.Date(tran_date2.getTime());
                // Date date = new Date();
                BigDecimal amt2 = new BigDecimal(amt);
                ps.setString(1, acc);
                ps.setDate(2, tran_date3);
                ps.setBigDecimal(3, amt2);
                ps.setInt(4, Integer.parseInt(dr_cr_flag));
                ps.setString(5, rpt_sum);
                ps.setString(6, timestampl);
                int sucCount = ps.executeUpdate();
                if (sucCount > 0) {
                    System.out.println("insert success!");
                }

                System.out.println("sucCount");

            }
            // 关闭连接
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}