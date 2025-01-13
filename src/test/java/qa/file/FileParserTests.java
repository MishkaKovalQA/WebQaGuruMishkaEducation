package qa.file;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import qa.demoqa.dto.ComputerStoreModel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

class FileParserTests {

    private ClassLoader cl = FileParserTests.class.getClassLoader();

    @Test
    @SneakyThrows
    void selenideDownloadTest() {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        var downloadedFile = $("[data-testid=raw-button]").download();
        try (InputStream is = new FileInputStream(downloadedFile)) {
            var bytes = is.readAllBytes();
            var textContent = new String(bytes, UTF_8);
            assertThat(textContent).contains("This repository is the home of");
        }
    }

    @Test
    void selenideUploadTest() {
        open("https://tus.io/demo");
        $("input[type=file]").uploadFromClasspath("kitten.jpg");
        $(".page").shouldHave(text("The upload is complete!"));
    }

    @Test
    @SneakyThrows
    void pdfParserTest() {
        open("https://junit.org/junit5/docs/current/user-guide/");
        var file = $("a[href='junit-user-guide-5.11.4.pdf']").download();
        var pdfContent = new PDF(file);
        assertThat(pdfContent.title).contains("JUnit 5 User Guide");
    }

    @Test
    @SneakyThrows
    void xlsParserTest() {
        try (InputStream resource = cl.getResourceAsStream("test_file.xlsx")) {
            XLS xlsContent = new XLS(resource);
            var thirdCell = xlsContent.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();
            assertThat(thirdCell).isEqualTo("New York");
        }
    }

    @Test
    @SneakyThrows
    void csvParserTest() {
        try (
                InputStream resource = cl.getResourceAsStream("qa_guru.csv");
                Reader reader = new InputStreamReader(resource)
        ) {
            CSVReader csvContent = new CSVReader(reader);
            List<String[]> content = csvContent.readAll();
            assertThat(content.get(0)[0]).isEqualTo("teacher");
        }
    }

    @Test
    @SneakyThrows
    void zipParserTxtTest() {
        try (
                InputStream resource = cl.getResourceAsStream("sample.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry zipEntry;
            String targetFileName = "sample.txt";
            String fileContent = null;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals(targetFileName)) {
                    fileContent = new Scanner(zis, UTF_8).next();
                    ;
                    break;
                }
            }
            assertThat(fileContent).contains("mya");
        }
    }

    @Test
    @SneakyThrows
    void zipParserCsvTest() {
        try (
                InputStream resource = cl.getResourceAsStream("qa_guru.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry zipEntry;
            String targetFileName = "qa_guru.csv";
            List<String[]> fileContent = null;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals(targetFileName)) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis, UTF_8));
                    fileContent = csvReader.readAll();
                    break;
                }
            }

            assertThat(fileContent).isNotNull();
            assertThat(fileContent.get(0)[0]).isEqualTo("teacher");
            assertThat(fileContent.get(1)[1]).isEqualTo("Files");
        }
    }

    @Test
    @SneakyThrows
    void zipParserXlsxTest() {
        try (
                InputStream resource = cl.getResourceAsStream("test_file.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry zipEntry;
            String targetFileName = "test_file.xlsx";
            XLS xlsContent = null;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals(targetFileName)) {
                    xlsContent = new XLS(zis);
                    break;
                }
            }

            assertThat(xlsContent).isNotNull();
            var thirdCell = xlsContent.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue();
            assertThat(thirdCell).isEqualTo("New York");
        }
    }

    @Test
    @SneakyThrows
    void zipParserPdfTest() {
        try (
                InputStream resource = cl.getResourceAsStream("test_pdf_file.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry zipEntry;
            String targetFileName = "test_pdf_file.pdf";
            PDF pdfContent = null;

            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals(targetFileName)) {
                    pdfContent = new PDF(zis);
                    break;
                }
            }

            assertThat(pdfContent.text).contains("Test PDF");
        }
    }

    @Test
    @SneakyThrows
    void jsonParserWithGSONTest() {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("computerStore.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            String storeName = jsonObject.get("Store").getAsJsonObject().get("name").getAsString();
            assertThat(storeName).isEqualTo("Tech Haven");
        }
    }

    @Test
    @SneakyThrows
    void jsonParserWithGSONByPojoTest() {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("computerStore.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            ComputerStoreModel computerStore = gson.fromJson(reader, ComputerStoreModel.class);
            assertThat(computerStore.getStore().getName()).isEqualTo("Tech Haven");
        }
    }

    @Test
    @SneakyThrows
    void jsonParserWithJacksonByPojoTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream resource = cl.getResourceAsStream("computerStore.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            ComputerStoreModel computerStore = objectMapper.readValue(reader, ComputerStoreModel.class);
            assertThat(computerStore.getStore().getName()).isEqualTo("Tech Haven");
            assertThat(computerStore.getStore().getProducts().size()).isEqualTo(3);
            assertThat(computerStore.getStore().getProducts()
                    .stream()
                    .filter(productsItem -> productsItem.getId() == 1)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("There is no such product with id = 1"))
                    .getName())
                    .isEqualTo("Smartphone");
        }
    }
}
