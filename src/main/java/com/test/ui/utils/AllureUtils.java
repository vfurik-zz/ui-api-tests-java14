package com.test.ui.utils;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.test.core.utils.properties.PropertyController.propController;

public class AllureUtils {

    @Attachment(value = "Video link", type = "text/html")
    public static String attachVideoLink(String sessionId, String name) {
        String videoUrl = String.format(propController.getSelenoidVideoPath(), sessionId);
        return String.format("<html><p><a href=\"%s\">%s</a></p></html>", videoUrl, name);
    }

    @Attachment(value = "{name}", type = "image/png")
    public static byte[] saveScreenshot(BufferedImage file, String name) {
        return toByteArrayAutoClosable(file);
    }

    @Attachment(value = "gif", type = "image/gif")
    public static byte[] attachGif(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            return null;
        }
    }

    @Attachment(value = "Log for test", type = "text/html")
    public static String attachLog(String text) {
        return text;
    }

    @Attachment(value = "Browser log", type = "text/html")
    public static String attachBrowserLog(String sessionId, String name) {
        String logUrl = String.format(propController.getSelenoidLogPath(), sessionId);
        return String.format("<html><p><a href=\"%s\">%s</a></p></html>", logUrl, name);
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
