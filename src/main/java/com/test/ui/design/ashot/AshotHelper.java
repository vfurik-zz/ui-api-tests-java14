package com.test.ui.design.ashot;

import com.codeborne.selenide.WebDriverRunner;
import com.test.ui.design.components.DesignComponent;
import com.test.ui.utils.AllureUtils;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AshotHelper {

    public static void savePageScreenshot(DesignComponent page) {
        Screenshot screenshot = takePageScreenshot();
        try {
            File file = new File(page.getPath());
            ImageIO.write(screenshot.getImage(), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can not take screenshot");
        }
    }

    public static Screenshot takePageScreenshot() {
        return new AShot()
                //  .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(WebDriverRunner.getWebDriver());
    }


    @Step
    public static void compareScreenshot(DesignComponent component) {
        Screenshot actualScreenshot = takePageScreenshot();
        AllureUtils.saveScreenshot(actualScreenshot.getImage(), "actual screenshot");

        File expectedFile = new File(component.getPath());
        if (!expectedFile.exists()) {
            throw new RuntimeException("Can not find expected screenshot for: " + component);
        }

        try {
            BufferedImage image = ImageIO.read(expectedFile);
            Screenshot expectedScreenshot = new Screenshot(image);
            AllureUtils.saveScreenshot(expectedScreenshot.getImage(), "expected screenshot");

            ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);

            AllureUtils.saveScreenshot(diff.getMarkedImage(), "diff screenshot");

            //generate gif
            File gifFIle = new File(String.format("actual_screenshot/testGif_%s.gif", component.name()));
            ImageOutputStream output = new FileImageOutputStream(gifFIle);
            GifSequenceWriter writer = new GifSequenceWriter(output, actualScreenshot.getImage().getType(), 500, true);
            writer.writeToSequence(actualScreenshot.getImage());
            writer.writeToSequence(expectedScreenshot.getImage());
            writer.writeToSequence(diff.getMarkedImage());
            writer.close();
            output.close();
            AllureUtils.attachGif(gifFIle);
            Assertions.assertEquals(0, diff.getDiffSize(), String.format("Expected and actual screenshots for component:%s are not same", component));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error during comparing screenshots");
        }
    }
}
