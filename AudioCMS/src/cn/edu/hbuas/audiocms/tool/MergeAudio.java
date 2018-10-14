package cn.edu.hbuas.audiocms.tool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class MergeAudio {

    //�ϲ�
    public static void mergeFile() throws IOException{
        //�ҵ�Ŀ���ļ�
    	String u=System.getProperty("webapp.root");
        File dir = new File(u+"\\Audios\\TMP");
        //ͨ��Ŀ���ļ����ҵ����е�MP3�ļ���Ȼ������е�MP3�ļ���ӵ�vector�С�
        Vector<FileInputStream> vector = new Vector<FileInputStream>();
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.getName().endsWith(".mp3")){
                vector.add(new FileInputStream(file));
            }
        }
        //ͨ��Vector��ȡ������
        Enumeration<FileInputStream> e = vector.elements();
        //����������
        SequenceInputStream inputStream = new SequenceInputStream(e);
        //�����ļ������ͨ��
        FileOutputStream fileOutputStream = new FileOutputStream(u+"\\Audios\\"+System.getProperty("currentNewsId")+".mp3");
        //�������������ȡ�ļ�
        byte[] buf = new byte[1024];
        int length = 0 ; 
        while((length =  inputStream.read(buf))!=-1){
            fileOutputStream.write(buf,0,length);
        }
        //�ر���Դ
        fileOutputStream.close();
        inputStream.close();
        DeleteAllFiles.delAllFile(u+"\\Audios\\TMP");
    }


}