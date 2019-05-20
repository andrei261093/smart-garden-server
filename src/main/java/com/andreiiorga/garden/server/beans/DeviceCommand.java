package com.andreiiorga.garden.server.beans;

public class DeviceCommand {
    private int pinId;
    private Command command;

    public int getPinId() {
        return pinId;
    }

    public void setPinId(int pinId) {
        this.pinId = pinId;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
