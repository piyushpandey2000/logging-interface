package com.project.logginginterface.log;

import jakarta.websocket.Session;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class LogFetcher implements Runnable {
    boolean canRead;
    WatchService watchService;
    Path logFilePath;
    Long lastReadPos;
    Session session;

    public void init(String filepath, Session session) {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            logFilePath = Paths.get(filepath);
            logFilePath.getParent().register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            lastReadPos = Math.max(Files.size(logFilePath) - 500, 0);
            canRead = true;
            this.session = session;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (canRead) {
            try {
                WatchKey take = watchService.take();

                for (WatchEvent<?> event : take.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        readLogFile(logFilePath);
                    }
                }

                boolean valid = take.reset();
                if (!valid) {
                    session.getBasicRemote().sendText("Watch key is invalid. Aborting ...");
                    canRead = false;
                    break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readLogFile(Path logFilePath) {
        try (RandomAccessFile raf = new RandomAccessFile(logFilePath.toFile(), "r")) {
            raf.seek(lastReadPos);

            byte[] data = new byte[20];
            try {
                raf.readFully(data);
            } catch (EOFException e) {
                //ignore
            }
            session.getBasicRemote().sendText(new String(data));

            lastReadPos = raf.getFilePointer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
