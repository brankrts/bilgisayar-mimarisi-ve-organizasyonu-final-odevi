package registers;

import java.util.*;

import circuitelements.Decoder3X8;
import circuitelements.Signal;
import enums.Commands;
import utils.Util;

public class IR {

    private ArrayList<Integer> address;
    private ArrayList<Integer> commandBytes;
    private int I;
    private Util util;
    private Decoder3X8 decoder;
    private Commands finalCommand;

    private Signal selectedSignal;

    public IR(String ramInput) {
        this.finalCommand = Commands.NOTFOUND;
        this.util = Util.instance();
        this.decoder = new Decoder3X8();
        this.selectedSignal = new Signal("NONE", 0);
        this.setByteArray(ramInput);
    }

    public int getI() {
        return this.I;
    }

    private void setByteArray(String input) {
        ArrayList<Integer> bytesArray = new ArrayList<Integer>();
        for (char c : input.toCharArray()) {
            bytesArray.add(Integer.parseInt(String.valueOf(c)));
        }
        this.I = bytesArray.get(0);
        this.commandBytes = new ArrayList<Integer>(bytesArray.subList(1, 4));
        this.address = new ArrayList<Integer>(bytesArray.subList(4, bytesArray.size()));
    }

    public Signal getSelectedSignal() {
        return this.selectedSignal;
    }

    public String getAddress() {
        String result = "";
        for (int bit : this.address) {
            result += Integer.toString(bit);
        }
        return result;
    }

    public Commands getFinalCommand() {
        return this.finalCommand;
    }

    public void executeCommand() {
        Commands result = Commands.NOTFOUND;
        this.selectedSignal = this.decoder.decode(this.commandBytes);

        for (Commands command : Commands.values()) {
            ArrayList<Integer> commandBinaryValues = this.util.hexToBinary(command.getCommandHex());

            if (this.I == 1 && commandBinaryValues.get(0) == this.I) {
                if (commandBinaryValues.size() <= 4) {
                    int expectedValue = Integer.parseInt(selectedSignal.getName().substring(1)) + 8;
                    int currentValue = this.util.bitToInteger(this.util.hexToBinary(command.getCommandHex()));
                    if (expectedValue == currentValue) {
                        result = command;
                        break;
                    }
                    continue;
                }
                int expectedValue = Integer.parseInt(selectedSignal.getName().substring(1)) + 8;
                int currentValue = this.util
                        .bitToInteger(
                                new ArrayList<Integer>(this.util.hexToBinary(command.getCommandHex()).subList(0, 4)));
                int index = commandBinaryValues.subList(4, commandBinaryValues.size()).indexOf(1);
                if (expectedValue == currentValue && this.address.get(index) == 1) {
                    result = command;
                    break;
                }
                continue;

            }

            if (this.I == 0 && commandBinaryValues.get(0) == this.I) {
                if (commandBinaryValues.size() <= 4) {
                    int expectedValue = Integer.parseInt(selectedSignal.getName().substring(1));
                    int currentValue = this.util.bitToInteger(this.util.hexToBinary(command.getCommandHex()));
                    if (expectedValue == currentValue) {
                        result = command;
                        break;
                    }
                    continue;
                }
                int expectedValue = Integer.parseInt(selectedSignal.getName().substring(1));
                int currentValue = this.util
                        .bitToInteger(
                                new ArrayList<Integer>(this.util.hexToBinary(command.getCommandHex()).subList(0, 4)));
                int index = commandBinaryValues.subList(4, commandBinaryValues.size()).indexOf(1);
                if (expectedValue == currentValue && this.address.get(index) == 1) {
                    result = command;
                    break;
                }

            }
        }
        this.finalCommand = result;
    }

}
