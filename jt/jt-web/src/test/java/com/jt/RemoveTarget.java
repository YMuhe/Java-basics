package com.jt;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class RemoveTarget {

    @Test
    public void remove(){
        File fileDir = new File("D:\\JT-SOFT\\课堂代码\\2010\\day19");
        fileDir(fileDir);
    }

    public void fileDir(File fileDir){
        File[] files = fileDir.listFiles();
        if(files !=null && files.length>0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if ("target".equals(file.getName())) {
                        deleteFile(file);
                    }
                    fileDir(file);
                }
            }
        }
    }

    public void deleteFile(File file){
            File[] files = file.listFiles();
            if(files !=null && files.length > 0){
                for (File f : files){
                    System.out.println("删除文件:"+f.getPath());
                    deleteFile(f);
                }
            }
        file.delete();
    }

}
