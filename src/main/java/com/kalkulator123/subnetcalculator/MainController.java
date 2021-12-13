package com.kalkulator123.subnetcalculator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class MainController {
    Scene scene = SubnetCalculator.scene;
    @FXML
    private TextField IPAddress;
    @FXML
    private ComboBox SubnetMask;
    @FXML
    private ComboBox SubnetBits;
    @FXML
    private ComboBox MaximumSubnets;
    @FXML
    private TextField FirstOctetRange;
    @FXML
    private TextField WildcardMask;
    @FXML
    private ComboBox MaskBits;
    @FXML
    private ComboBox HostsPerSubnet;
    @FXML
    private TextField HostAdressRange;
    @FXML
    private TextField SubnetID;
    @FXML
    private TextField BroadcastAdress;
    @FXML
    private TextField SubnetBitmap;
    @FXML
    private TextField HexIPAdress;
    @FXML
    private RadioButton ClassA;
    @FXML
    private RadioButton ClassB;
    @FXML
    private RadioButton ClassC;
    @FXML
    private Button SubmitButton;
    Calculator calculator;
    @FXML
    public void initialize() {
        final ToggleGroup group = new ToggleGroup();
        ClassA.setToggleGroup(group);
        ClassB.setToggleGroup(group);
        ClassC.setToggleGroup(group);
        ClassC.setSelected(true);
        calculator = new Calculator(scene, "C", "192.168.0.1", 0);
        SubnetMask.setValue("255.255.255.0");
        setValues(calculator);
        calculator.ended = true;
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            calculator.ended = false;
            calculator = new Calculator(scene,
                    ClassA.isSelected() ? "A" : (ClassB.isSelected() ? "B" : "C"),
                    IPAddress.getText(), SubnetMask.getSelectionModel().getSelectedIndex());
            setValues(calculator);
            calculator.ended = true;
        });
        SubnetMask.valueProperty().addListener((observableValue, o, t1) -> {
            if(calculator.ended)
            calculator.setSubnetByIndex(SubnetMask.getSelectionModel().getSelectedIndex());
        });
        SubnetBits.valueProperty().addListener((observableValue, o, t1) -> {
            if(calculator.ended)
            calculator.setSubnetByIndex(SubnetBits.getSelectionModel().getSelectedIndex());
        });
        MaskBits.valueProperty().addListener((observableValue, o, t1) -> {
            if(calculator.ended)
            calculator.setSubnetByIndex(MaskBits.getSelectionModel().getSelectedIndex());
        });
        MaximumSubnets.valueProperty().addListener((observableValue, o, t1) -> {
            if(calculator.ended)
            calculator.setSubnetByIndex(MaximumSubnets.getSelectionModel().getSelectedIndex());
        });
        HostsPerSubnet.valueProperty().addListener((observableValue, o, t1) -> {
            if(calculator.ended)
            calculator.setSubnetByIndex(HostsPerSubnet.getSelectionModel().getSelectedIndex());
        });
        SubmitButton.setOnAction(event -> {
            calculator.ended = false;
            calculator = new Calculator(scene,
                    ClassA.isSelected() ? "A" : (ClassB.isSelected() ? "B" : "C"),
                    IPAddress.getText(), calculator.id);
            setValues(calculator);
            calculator.ended = true;
        });
    }

    private void setValues(Calculator calculator){
        SubnetMask.setItems(FXCollections.observableList(calculator.getSubnetMaskList()));
        SubnetBits.setItems(FXCollections.observableList(calculator.getSubnetBitsList()));
        SubnetBits.setValue(calculator.getValue(CalculatorValues.SubnetBits));
        MaskBits.setItems(FXCollections.observableList(calculator.getMaskBitsList()));
        MaskBits.setValue(calculator.getValue(CalculatorValues.MaskBits));
        MaximumSubnets.setItems(FXCollections.observableList(calculator.getMaximumSubnetsList()));
        MaximumSubnets.setValue(calculator.getValue(CalculatorValues.MaximumSubnets));
        HostsPerSubnet.setItems(FXCollections.observableList(calculator.getHostsPerSubnetList()));
        HostsPerSubnet.setValue(calculator.getValue(CalculatorValues.HostsPerSubnet));
        IPAddress.setText(calculator.getValue(CalculatorValues.IPAddress));
        FirstOctetRange.setText(calculator.getValue(CalculatorValues.FirstOctetRange));
        HexIPAdress.setText(calculator.getValue(CalculatorValues.HexIPAddress));
        WildcardMask.setText(calculator.getValue(CalculatorValues.WildCardMask));
        HostAdressRange.setText(calculator.getValue(CalculatorValues.HostAddressRange));
        SubnetID.setText(calculator.getValue(CalculatorValues.SubnetID));
        BroadcastAdress.setText(calculator.getValue(CalculatorValues.BroadcastAddress));
        SubnetBitmap.setText(calculator.getValue(CalculatorValues.SubnetBitmap));
    }

}