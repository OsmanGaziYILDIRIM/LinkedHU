package com.powerrangers.linkedhu.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.powerrangers.linkedhu.entity.Graduate;
import com.powerrangers.linkedhu.entity.Student;
import com.powerrangers.linkedhu.entity.common.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
public class UserExcelExporterService {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<User> listUsers;
    private UserService userService;

    public UserExcelExporterService(List<User> listUsers, UserService userService) {
        this.listUsers = listUsers;
        workbook = new XSSFWorkbook();
        this.userService = userService;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "E-mail", style);
        createCell(row, 2, "Full Name", style);
        createCell(row, 3, "Roles", style);
        createCell(row, 4, "Birth Date", style);
        createCell(row, 5, "Phone Number", style);
        createCell(row, 6, "Gender", style);
        createCell(row, 7, "Biography", style);
        createCell(row, 8, "Degree", style);
        createCell(row, 9, "Department", style);
        createCell(row, 10, "Graduate Year", style);
        createCell(row, 11, "Enable", style);
        createCell(row, 12, "Banned", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Byte) {
            cell.setCellValue((Byte) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (User user : listUsers) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, user.getId(), style);
            createCell(row, columnCount++, user.getUsername(), style);
            createCell(row, columnCount++, user.getName() + " " + user.getSurname(), style);
            createCell(row, columnCount++, user.getAuthorities().toString(), style);
            createCell(row, columnCount++, user.getBirthdate(), style);
            createCell(row, columnCount++, user.getPhoneNumber(), style);
            createCell(row, columnCount++, user.getGender(), style);
            createCell(row, columnCount++, user.getText(), style);
            createCell(row, columnCount++, user.getDegree(), style);
            createCell(row, columnCount++, user.getDepartment(), style);
            createCell(row, columnCount++, user.getGraduate_year(), style);
            createCell(row, columnCount++, user.getEnable(), style);
            createCell(row, columnCount++, userService.userIsBlockedBySystem(user.getUsername()), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}