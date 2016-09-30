package com.rongyi.diamond.baselibiary.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/30 下午6:03
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/30      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class FileHelper {
    /**
     * 读取文件内容 只适合读取小文件
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return 文件字符串
     */
    public static String readFileToString(Context context, String filePath) {
        return readFileToString(context, filePath, false);
    }

    /**
     * 读取文件内容 只适合读取小文件
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @param isAssets 是否Assets目录文件
     * @return 文件字符串
     */
    public static String readFileToString(Context context, String filePath, boolean isAssets) {
        String res = "";
        InputStream in = null;
        FileInputStream inFile = null;
        int length;
        try {
            if (isAssets) {
                in = context.getResources().getAssets().open(filePath);
                length = in.available();
            } else {
                inFile = new FileInputStream(filePath);
                length = inFile.available();
            }
            byte[] buffer = new byte[length];
            int len;
            if (inFile != null) {
                len = inFile.read(buffer);
                inFile.close();
            } else {
                len = in.read(buffer);
                in.close();
            }
            if (len > 0) {
                final byte[] bom = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
                int start = 0;
                if (buffer[0] == bom[0] && buffer[1] == bom[1] && buffer[2] == bom[2]) {
                    start = 3;
                }
                res = new String(buffer, start, len, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 读取文件内容 只适合读取小文件
     *
     * @param file 文件对象
     * @return 文件字符串
     */
    public static String readFileToString(File file) {
        String res = "";
        FileInputStream inFile;
        int length;
        try {
            inFile = new FileInputStream(file);
            length = inFile.available();
            byte[] buffer = new byte[length];
            int len;
            len = inFile.read(buffer);
            inFile.close();
            if (len > 0) {
                res = new String(buffer, 0, len, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 将字符串写入文件 适合写小文件
     *
     * @param filePath 文件全路径
     * @param message  需要写入文件的字符串
     */
    public static void writeInfoToFile(String filePath, String message) {
        writeInfoToFile(filePath, message, false);
    }

    /**
     * 将字符串写入文件 适合写小文件
     *
     * @param filePath 文件全路径
     * @param message  需要写入文件的字符串
     * @param isAppend 是否追加
     */
    public static void writeInfoToFile(String filePath, String message, boolean isAppend) {
        try {
            FileOutputStream out = new FileOutputStream(filePath, isAppend);
            byte[] bytes = message.getBytes();
            out.write(bytes);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将字符串写入文件 适合写小文件
     *
     * @param file    文件句柄
     * @param message 需要写入文件的字符串
     * @param append  是否追加
     */
    public static void writeInfoToFile(File file, String message, boolean append) {
        try {
            FileOutputStream out = new FileOutputStream(file, append);
            byte[] bytes = message.getBytes();
            out.write(bytes);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件是否存在
     *
     * @param filePath 文件全路径
     * @return true:存在 false:不存在
     */
    public static boolean isFileExit(String filePath) {
        return filePath != null && !filePath.isEmpty() && new File(filePath).exists();
    }

    /**
     * 删除文件接口
     *
     * @param filePath 需要删除的文件路径
     * @return true:成功 false:失败
     */
    public static boolean deleteFile(String filePath) {
        return isFileExit(filePath) && deleteFile(new File(filePath));
    }

    /**
     * 删除文件接口
     *
     * @param file 需要删除的文件
     * @return true:成功 false:失败
     */
    public static boolean deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                return file.delete();
            } else if (file.isDirectory()) {
                return deleteDirectoryFile(file, true);
            }
        }
        return false;
    }

    /**
     * 删除文件夹及子文件接口
     *
     * @param file 需要删除的文件
     * @return true:成功 false:失败
     */
    public static boolean deleteDirectoryFile(File file, boolean isDeleteDirectory) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    deleteFile(f);
                }
                return !isDeleteDirectory || file.delete();
            }
        }
        return false;
    }

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     *
     * @param context 上下文
     */
    public static void cleanInternalCache(Context context) {
        deleteDirectoryFile(context.getCacheDir(), false);
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     *
     * @param context 上下文
     */
    public static void cleanDatabases(Context context) {
        deleteDirectoryFile(new File("/data/data/"
                + context.getPackageName() + "/databases"), false);
    }

    /**
     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     *
     * @param context 上下文
     */
    public static void cleanSharedPreference(Context context) {
        deleteDirectoryFile(new File("/data/data/"
                + context.getPackageName() + "/shared_prefs"), false);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     *
     * @param context 上下文
     */
    public static void cleanFiles(Context context) {
        deleteDirectoryFile(context.getFilesDir(), false);
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context 上下文
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteDirectoryFile(context.getExternalCacheDir(), false);
        }
    }


    /**
     * 清除本应用所有的数据
     *
     * @param context 上下文
     */
    public static void cleanApplicationData(Context context) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
    }

    /**
     * 是否有SD卡
     *
     * @return true 有SD卡
     */
    public static boolean haveSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}

