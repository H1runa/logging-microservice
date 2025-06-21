package com.hiruna.logging.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {
    public static void log(String msg) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))){
            writer.write(msg);
//            writer.newLine();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
