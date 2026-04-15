package com.geely.lib.oneosapi.navi.startapp;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class ExeCommand {
    private boolean bRunning;
    private boolean bSynchronous;
    private BufferedReader errorResult;
    ReadWriteLock lock;
    private DataOutputStream os;
    private Process process;
    private StringBuffer result;
    private BufferedReader successResult;

    public ExeCommand(boolean synchronous) {
        this.bRunning = false;
        this.lock = new ReentrantReadWriteLock();
        this.result = new StringBuffer();
        this.bSynchronous = synchronous;
    }

    public ExeCommand() {
        this.bRunning = false;
        this.lock = new ReentrantReadWriteLock();
        this.result = new StringBuffer();
        this.bSynchronous = true;
    }

    public boolean isRunning() {
        return this.bRunning;
    }

    public String getResult() {
        Lock readLock = this.lock.readLock();
        readLock.lock();
        try {
            Log.i("auto", "getResult");
            return new String(this.result);
        } finally {
            readLock.unlock();
        }
    }

    public ExeCommand run(String command, final int maxTime) {
        Log.i("auto", "run command:" + command + ",maxtime:" + maxTime);
        if (command != null && command.length() != 0) {
            try {
                this.process = Runtime.getRuntime().exec("sh");
                this.bRunning = true;
                this.successResult = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
                this.errorResult = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));
                DataOutputStream dataOutputStream = new DataOutputStream(this.process.getOutputStream());
                this.os = dataOutputStream;
                try {
                    dataOutputStream.write(command.getBytes());
                    this.os.writeBytes("\n");
                    this.os.flush();
                    this.os.writeBytes("exit\n");
                    this.os.flush();
                    this.os.close();
                    if (maxTime > 0) {
                        new Thread(new Runnable() { // from class: com.geely.lib.oneosapi.navi.startapp.ExeCommand.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Thread.sleep(maxTime);
                                } catch (Exception unused) {
                                }
                                try {
                                    Log.i("auto", "exitValue Stream over" + ExeCommand.this.process.exitValue());
                                } catch (IllegalThreadStateException unused2) {
                                    Log.i("auto", "take maxTime,forced to destroy process");
                                    ExeCommand.this.process.destroy();
                                }
                            }
                        }).start();
                    }
                    final Thread thread = new Thread(new Runnable() { // from class: com.geely.lib.oneosapi.navi.startapp.ExeCommand.2
                        @Override // java.lang.Runnable
                        public void run() {
                            StringBuilder sb;
                            Lock writeLock = ExeCommand.this.lock.writeLock();
                            while (true) {
                                try {
                                    try {
                                        String readLine = ExeCommand.this.successResult.readLine();
                                        if (readLine == null) {
                                            try {
                                                ExeCommand.this.successResult.close();
                                                Log.i("auto", "read InputStream over");
                                                return;
                                            } catch (Exception e) {
                                                e = e;
                                                sb = new StringBuilder();
                                                sb.append("close InputStream exception:");
                                                sb.append(e.toString());
                                                Log.i("auto", sb.toString());
                                                return;
                                            }
                                        }
                                        writeLock.lock();
                                        ExeCommand.this.result.append(readLine + "\n");
                                        writeLock.unlock();
                                    } catch (Exception e2) {
                                        Log.i("auto", "read InputStream exception:" + e2.toString());
                                        try {
                                            ExeCommand.this.successResult.close();
                                            Log.i("auto", "read InputStream over");
                                            return;
                                        } catch (Exception e3) {
                                            sb = new StringBuilder();
                                            sb.append("close InputStream exception:");
//                                            sb.append(e.toString());
                                            Log.i("auto", sb.toString());
                                            return;
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        ExeCommand.this.successResult.close();
                                        Log.i("auto", "read InputStream over");
                                    } catch (Exception e4) {
                                        Log.i("auto", "close InputStream exception:" + e4.toString());
                                    }
                                    throw th;
                                }
                            }
                        }
                    });
                    thread.start();
                    final Thread thread2 = new Thread(new Runnable() { // from class: com.geely.lib.oneosapi.navi.startapp.ExeCommand.3
                        @Override // java.lang.Runnable
                        public void run() {
                            StringBuilder sb;
                            Lock writeLock = ExeCommand.this.lock.writeLock();
                            while (true) {
                                try {
                                    try {
                                        String readLine = ExeCommand.this.errorResult.readLine();
                                        if (readLine == null) {
                                            try {
                                                ExeCommand.this.errorResult.close();
                                                Log.i("auto", "read ErrorStream over");
                                                return;
                                            } catch (Exception e) {
                                                e = e;
                                                sb = new StringBuilder();
                                                sb.append("read ErrorStream exception:");
                                                sb.append(e.toString());
                                                Log.i("auto", sb.toString());
                                                return;
                                            }
                                        }
                                        writeLock.lock();
                                        ExeCommand.this.result.append(readLine + "\n");
                                        writeLock.unlock();
                                    } catch (Exception e2) {
                                        Log.i("auto", "read ErrorStream exception:" + e2.toString());
                                        try {
                                            ExeCommand.this.errorResult.close();
                                            Log.i("auto", "read ErrorStream over");
                                            return;
                                        } catch (Exception e3) {
                                            sb = new StringBuilder();
                                            sb.append("read ErrorStream exception:");
//                                            sb.append(e.toString());
                                            Log.i("auto", sb.toString());
                                            return;
                                        }
                                    }
                                } catch (Throwable th) {
                                    try {
                                        ExeCommand.this.errorResult.close();
                                        Log.i("auto", "read ErrorStream over");
                                    } catch (Exception e4) {
                                        Log.i("auto", "read ErrorStream exception:" + e4.toString());
                                    }
                                    throw th;
                                }
                            }
                        }
                    });
                    thread2.start();
                    Thread thread3 = new Thread(new Runnable() { // from class: com.geely.lib.oneosapi.navi.startapp.ExeCommand.4
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                thread.join();
                                thread2.join();
                                ExeCommand.this.process.waitFor();
                            } catch (Exception unused) {
                            } catch (Throwable th) {
                                ExeCommand.this.bRunning = false;
                                Log.i("auto", "run command process end");
                                throw th;
                            }
                            ExeCommand.this.bRunning = false;
                            Log.i("auto", "run command process end");
                        }
                    });
                    thread3.start();
                    if (this.bSynchronous) {
                        Log.i("auto", "run is go to end");
                        thread3.join();
                        Log.i("auto", "run is end");
                    }
                } catch (Exception e) {
                    Log.i("auto", "run command process exception:" + e.toString());
                }
            } catch (Exception unused) {
            }
        }
        return this;
    }
}