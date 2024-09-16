package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.config.AppConfig;
import org.example.models.fileInfo;

import java.io.File;
import java.text.DecimalFormat;

public class DownloadManager {

    @FXML
    private TableView<fileInfo> tableView;

    @FXML
    private TextField urlTextField;

    public int index = 0;
    @FXML
    void downloadButtonClicked(ActionEvent event) {

        String url = urlTextField.getText().trim();
        String filename = url.substring(url.lastIndexOf("/") + 1);
        String status = "STARTING";
        String action = "OPEN";
        String path = AppConfig.DOWNLOAD_PATH+ File.separator+filename;
        fileInfo file = new fileInfo((index+1)+"", filename, url, status, action, path, "0");
        this.index = this.index+1;
        DownloadThread thread = new DownloadThread(file, this);
        this.tableView.getItems().add(Integer.parseInt(file.getIndex())-1, file);
        thread.start();
    }

    public void updateUI(fileInfo metaFile) {
        System.out.println(metaFile);
        fileInfo fileinfo = this.tableView.getItems().get(Integer.parseInt(metaFile.getIndex())-1);
        fileinfo.setStatus(metaFile.getStatus());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        fileinfo.setPer(decimalFormat.format(Double.parseDouble(metaFile.getPer())));
        this.tableView.refresh();
        System.out.println("------------------------");
    }

    @FXML
    public void initialize() {
        System.out.println("View initialized");


        TableColumn<fileInfo, String> sn = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(0);
        sn.setCellValueFactory(p->{
            return p.getValue().indexProperty();
        });

        TableColumn<fileInfo, String> filename = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(1);
        filename.setCellValueFactory(p->{
            return p.getValue().nameProperty();
        });

        TableColumn<fileInfo, String> url = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(2);
        url.setCellValueFactory(p->{
            return p.getValue().urlProperty();
        });

        TableColumn<fileInfo, String> status = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(3);
        status.setCellValueFactory(p->{
            return p.getValue().statusProperty();
        });

        TableColumn<fileInfo, String> per = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(4);
        per.setCellValueFactory(p->{
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.set(p.getValue().getPer()+" %");
            return simpleStringProperty;
        });

        TableColumn<fileInfo, String> action = (TableColumn<fileInfo, String>) this.tableView.getColumns().get(5);
        action.setCellValueFactory(p->{
            return p.getValue().actionProperty();
        });
    }
}
