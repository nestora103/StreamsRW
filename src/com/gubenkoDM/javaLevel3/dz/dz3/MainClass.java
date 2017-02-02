package com.gubenkoDM.javaLevel3.dz.dz3;

import java.io.*;
import java.util.*;


/**
 * Created by Nestor on 29.01.2017.
 */
public class MainClass {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        long t1;
        long t2;
        byte[] buf;
        String fileName;

        System.out.println("Задание 1");
        System.out.println("Введите имя файла для его разбора");
        fileName=sc.next();
        if (new File(fileName).exists()) {
            System.out.println("Введите количество считываемых байт из файла");
            int readByteNumber=sc.nextInt();
            //byte[] readBuf=new byte[readByteNumber];
            buf=new byte[readByteNumber];
            BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(fileName));

            t1=System.currentTimeMillis();

            int readedByte=inputStream.read(buf);
            System.out.println("Фактически считано байт "+readedByte);
            /*for (int i = 0; i < readedByte; i++) {
                System.out.print((char)readBuf[i]);
            }*/
            System.out.print(new String(buf));
            t2=System.currentTimeMillis();
            System.out.println("\nВремя выполнения(мс):"+(t2-t1));

            inputStream.close();
            System.out.println("\nЗадание 2");
            FileOutputStream outS=new FileOutputStream("file2to6");
            ArrayList<InputStream> al=new ArrayList<>();
            al.add(new FileInputStream("file2"));
            al.add(new FileInputStream("file3"));
            al.add(new FileInputStream("file4"));
            al.add(new FileInputStream("file5"));
            al.add(new FileInputStream("file6"));
            Enumeration<InputStream> en= Collections.enumeration(al);
            SequenceInputStream seq=new SequenceInputStream(en);

            t1=System.currentTimeMillis();

            for (int i = 0; i <al.size() ; i++) {
                int byteInfile=al.get(i).available();
                buf=new byte[byteInfile];
                long bfr=al.get(i).read(buf);
                System.out.println("Фактически считано байт "+bfr);
                outS.write(buf);
                System.out.println("Записано байт "+byteInfile);
                buf=null;
                al.get(i).close();
            }
            seq.close();
            outS.close();
            FileInputStream inS=new FileInputStream("file2to6");
            System.out.println("В результирующем файле байт "+inS.available());
            buf=new byte[inS.available()];
            long bfr=inS.read(buf);
            System.out.println("Фактически считано байт "+bfr);
            System.out.println("Содержимое склееного результирующего файла:");
            /*for (int i = 0; i < buf.length; i++) {
                System.out.print((char)buf[i]);
            }*/
            System.out.print(new String(buf));

            t2=System.currentTimeMillis();
            System.out.println("\nВремя выполнения(мс):"+(t2-t1));

            inS.close();
        }else{
            System.out.println("Нет такого файла!");
        }
        System.out.println("\nЗадание 3");
        System.out.println("Введите имя файла для его разбора");
        fileName=sc.next();
        if (new File(fileName).exists()) {
            System.out.println("Введите количество байт в 1 странице");
            int byteInPage=sc.nextInt();
            System.out.println("Введите номер страницы для отображения");
            int pageNum=sc.nextInt();
            RandomAccessFile raf=new RandomAccessFile(fileName,"r");
            if (raf.length()>=pageNum*byteInPage){
                if (pageNum>=0){
                    if (pageNum>0) {
                        raf.seek(pageNum * byteInPage);
                    }
                    buf=new byte[byteInPage];
                    t1=System.currentTimeMillis();
                    raf.read(buf);
                    System.out.println("Файл:"+fileName+" "+"Номер страницы:"+pageNum+" "+"Количество байт в странице:"+byteInPage);
                    System.out.println("Содержимое страницы:");
                    /*for (int i = 0; i < buf.length; i++) {
                        System.out.print((char)buf[i]);
                    }*/
                    System.out.print(new String(buf));

                    t2=System.currentTimeMillis();
                    System.out.println("\nВремя выполнения(мс):"+(t2-t1));
                }else {
                    System.out.println("Так дело не пойдет!");
                }
                raf.close();
            }else{
                System.out.println("Нет такой страницы!");
            }
        }else{
            System.out.println("Нет такого файла!");
        }
    }
}
