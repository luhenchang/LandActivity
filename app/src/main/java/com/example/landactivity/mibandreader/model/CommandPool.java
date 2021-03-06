package com.example.landactivity.mibandreader.model;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.RunnableFuture;
/**
 * Created by Jiesean on 2017/3/1.
 */

public class CommandPool implements Runnable {

    public enum Type {
        setNotification, read, write
    }

    private Context context;
    private BluetoothGatt gatt;
    private LinkedList<Command> pool;
    private BluetoothGattCharacteristic characteristic;
    private int index = 0;
    private boolean isCompleted = false;
    private boolean isDone = false;
    private Command commandToExc;

    public CommandPool(Context context, BluetoothGatt gatt) {
        this.gatt = gatt;
        this.context = context;
        pool = new LinkedList<>();
    }

    public void addCommand(Type type, byte[] value, BluetoothGattCharacteristic target) {
        Command command = new Command(type, value, target);
        pool.offer(command);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (pool.peek() == null) {
                commandToExc = null;
                continue;
            } else if (!isDone) {
                commandToExc = pool.peek();
                isDone = execute(commandToExc.getType(), commandToExc.getValue(), commandToExc.getTarget());
                System.out.println(commandToExc.getId() + "命令结果" + isDone);
            } else if (isCompleted && isDone) {
                System.out.println(commandToExc.getId() + "命令执行完成");

                pool.poll();
                isCompleted = false;
                isDone = false;
            }
        }


    }

    private boolean execute(Type type, byte[] value, BluetoothGattCharacteristic target) {
        boolean result = false;
        switch (type) {
            case setNotification:
                result = enableNotification(true, target);
                break;
            case read:
                result = readCharacteristic(target);
                break;
            case write:
                result = writeCharacteristic(target, value);
                break;
        }
        return result;
    }

    private boolean writeCharacteristic(BluetoothGattCharacteristic characteristic, byte[] command) {
        characteristic.setValue(command);
        boolean result = gatt.writeCharacteristic(characteristic);
        return result;
    }

    private boolean enableNotification(boolean enable, BluetoothGattCharacteristic characteristic) {

        if (gatt == null || characteristic == null)
            return false;
        if (!gatt.setCharacteristicNotification(characteristic, enable))
            return false;
        BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(Profile.notificationDesUUID);
        if (clientConfig == null)
            return false;
        if (enable) {
            clientConfig.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            clientConfig.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        }
        return gatt.writeDescriptor(clientConfig);
    }

    private boolean readCharacteristic(BluetoothGattCharacteristic characteristic) {
        boolean result = gatt.readCharacteristic(characteristic);
        return result;
    }

    public void onCommandCallbackComplete() {
        isCompleted = true;
    }

    private class Command {
        private int id;
        private boolean state = false;
        private byte[] value;
        private Type type;
        private BluetoothGattCharacteristic target;

        Command(Type type, byte[] value, BluetoothGattCharacteristic target) {
            this.value = value;
            this.target = target;
            this.type = type;
            id = index;
            System.out.println(index + "命令创建，UUID: " + target.getUuid().toString());

            index++;

        }

        int getId() {
            return id;
        }

        void setSsate(boolean state) {
            this.state = state;
        }

        boolean getState() {
            return state;
        }

        BluetoothGattCharacteristic getTarget() {
            return target;
        }

        byte[] getValue() {
            return value;
        }

        Type getType() {
            return type;
        }
    }

}
