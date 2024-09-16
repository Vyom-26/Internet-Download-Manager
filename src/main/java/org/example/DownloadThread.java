package org.example;

import org.example.models.fileInfo;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadThread extends Thread {

    private fileInfo file;

    DownloadManager manager;

    public DownloadThread(fileInfo file, DownloadManager manager) {
        this.file = file;
        this.manager = manager;
    }

    @Override
    public void run() {
        this.file.setStatus("DOWNLOADING");
        this.manager.updateUI(this.file);


        try {
            //download logic
            //Files.copy(new URL(this.file.getUrl()).openStream(), Paths.get(this.file.getPath()));

            URL url = new URL(this.file.getUrl());
            URLConnection urlConnection = url.openConnection();
            int fileSize = urlConnection.getContentLength();
            System.out.println("File size is: "+fileSize);

            int countByte = 0;
            double per = 0.0;
            double byteSum = 0.0;

            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());

            FileOutputStream fos = new FileOutputStream(this.file.getPath());

            byte data[] = new byte[1024];
            while(true){
                countByte = bufferedInputStream.read(data, 0, 1024);
                if(countByte==-1){
                    break;
                }
                fos.write(data, 0, countByte);
                byteSum += countByte;

                if(fileSize>0){
                    per = (byteSum/fileSize * 100);
                    System.out.println(per);
                    this.file.setPer(per + "");
                    this.manager.updateUI(file);
                }
            }

            fos.close();
            bufferedInputStream.close();

            this.file.setStatus("DONE");

        } catch (IOException e) {
            this.file.setStatus("FAILED");
            System.out.println("Downloading Erorr!");
            e.printStackTrace();
        }

        this.manager.updateUI(this.file);
    }
}
