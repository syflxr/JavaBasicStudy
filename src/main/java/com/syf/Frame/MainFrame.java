package com.syf.Frame;

import com.syf.Dao.UserDataDao;
import com.syf.Entity.UserDataEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainFrame {
    public static UserDataDao userDataDao;
    final static int WIDTH=1280;
    final static int HEIGHT=900;

    public static void main(String[] args) throws Exception {
        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setBounds((1920-WIDTH)/2,(1080-HEIGHT)/2,WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container=frame.getContentPane();
        container.setLayout(null);
        //第一行
        final JList list=new JList(new String[]{""});
        final DefaultListModel dim1=new DefaultListModel();

        final JList list2=new JList(new String[]{""});
        final DefaultListModel dim2=new DefaultListModel();

        list.setVisibleRowCount(15);
        list2.setVisibleRowCount(15);
        Font f = new Font("宋体",Font.PLAIN,24);
        list.setFont(f);   //设置字体
        list2.setFont(f);
        JScrollPane jsp=new JScrollPane(list);
        jsp.setBounds(50,50,1180,400);
        JScrollPane jsp2=new JScrollPane(list2);
        jsp2.setBounds(400,571,800,250);
        //第二行

        JButton button=new JButton("添加");
        JButton button2=new JButton("完成");
        button.setFont(f);
        button2.setFont(f);

        button.setBounds(50,500,100,50);
        button2.setBounds(50,650,300,150);
        final JTextField field=new JTextField(30);
        field.setBounds(200,500,880,50);
        field.setFont(f);




        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text=field.getText();
                UserDataEntity entity=new UserDataEntity();
                entity.setName(text);
                entity.setCount(1);
                entity.setLastTime(new Date());
                entity.setTime(new Date());

                MainFrame.userDataDao.insert(entity);
                StringBuilder sb=new StringBuilder();
                sb.append("成功将").append("\"").append(text).append("\"").append("存入数据库");
                dim2.addElement(sb.toString());
                list2.setModel(dim2);
                //dim1.addElement(text);
                //list.setModel(dim1);
            }
        });
        container.add(jsp);
        container.add(jsp2);
        container.add(button);
        container.add(button2);
        container.add(field);
        ApplicationContext context= new ClassPathXmlApplicationContext("bean.xml");
        UserDataDao dao=(UserDataDao) context.getBean("userDataDao");
        Map<Integer, UserDataEntity> map = dao.getMap();
        Map<String,Integer> itemMap=new HashMap<>();
        for(Map.Entry<Integer,UserDataEntity> entry:map.entrySet()){
            itemMap.put(entry.getValue().toString(),entry.getKey());
        }
        for(Map.Entry<String,Integer> entry:itemMap.entrySet()){
            dim1.addElement(entry.getKey());
            list.setModel(dim1);
        }



        frame.setVisible(true);

    }
}
