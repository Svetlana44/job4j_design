/*
parameterized tests
<dependency>
     <groupId>org.junit.jupiter</groupId>
     <artifactId>junit-jupiter-params</artifactId>
     <version>5.8.2</version>
     <scope>test</scope>
</dependency>
 */

package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    void whenOnePeriod(@TempDir Path tempDir) {
        Analysis analysis = new Analysis();
        File sourse = tempDir.resolve("serverTemp.log").toFile();
        File target = tempDir.resolve("targetTemp.csv").toFile();
        String rslExpected = "10:57:01;11:02:02;";
        StringBuilder rslPeriod = new StringBuilder();
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(sourse))) {
            outFile.write("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "500 10:59:01" + System.lineSeparator()
                    + "400 11:01:02" + System.lineSeparator()
                    + "300 11:02:02" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        analysis.unavailable(sourse.getAbsolutePath(), target.getAbsolutePath());

        try (BufferedReader rslFile = new BufferedReader(new FileReader(target))) {
            /* rslFile.lines().forEach(line -> rslPeriod.append(line));  */
            rslFile.lines().forEach(rslPeriod::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(rslPeriod.toString()).isEqualTo(rslExpected);
    }

    @Test
    void whenTwoPeriods(@TempDir Path tempDir) {
        Analysis analysis = new Analysis();
        File sourse = tempDir.resolve("serverTemp.log").toFile();
        File target = tempDir.resolve("targetTemp.csv").toFile();
        String rslExpected = "10:57:01;10:59:01;"
                + System.lineSeparator()
                + "11:01:02;11:02:02;"
                + System.lineSeparator();
        StringBuilder rslPeriod = new StringBuilder();
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(sourse))) {
            outFile.write("200 10:56:01" + System.lineSeparator()
                    + "500 10:57:01" + System.lineSeparator()
                    + "400 10:58:01" + System.lineSeparator()
                    + "300 10:59:01" + System.lineSeparator()
                    + "500 11:01:02" + System.lineSeparator()
                    + "200 11:02:02" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        analysis.unavailable(sourse.getAbsolutePath(), target.getAbsolutePath());

        try (BufferedReader rslFile = new BufferedReader(new FileReader(target))) {
            rslFile.lines().forEach(line -> rslPeriod.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(rslPeriod.toString()).isEqualTo(rslExpected);
    }
}