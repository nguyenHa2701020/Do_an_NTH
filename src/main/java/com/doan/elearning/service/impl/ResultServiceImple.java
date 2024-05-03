package com.doan.elearning.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ResultDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Result;

import com.doan.elearning.repository.ResultRepository;
import com.doan.elearning.service.ResultService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultServiceImple implements ResultService {
    private final ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }
    @Override
     public void exportToExcel(List<Result> objectList, HttpServletResponse response) throws IOException {
        // Tạo một workbook mới
        Workbook workbook = new XSSFWorkbook();

        // Tạo một sheet trong workbook
        Sheet sheet = workbook.createSheet("Object Data");

        // Tạo hàng đầu tiên (header) trong sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("User Name");
        headerRow.createCell(1).setCellValue("Listen Point");
        headerRow.createCell(2).setCellValue("Speak Point");
        headerRow.createCell(3).setCellValue("Read Point");
        headerRow.createCell(4).setCellValue("Write Point");
   
        // Thêm các tiêu đề cột khác (Field 3, Field 4, ...)
//kê
        // Ghi dữ liệu của các đối tượng vào sheet
        int rowNum = 1;
        for (Result object : objectList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(object.getUserss().getUsername());
            row.createCell(1).setCellValue(object.getListenPoint());
            row.createCell(2).setCellValue(object.getSpeakPoint());
            row.createCell(3).setCellValue(object.getReadPoint());
            row.createCell(4).setCellValue(object.getWritePoint());
           
            // Ghi các giá trị của các trường khác (Field 3, Field 4, ...)
        }

        // Thiết lập các thuộc tính của phản hồi HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=object_data.xlsx");

        // Ghi workbook vào OutputStream của phản hồi
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Đảm bảo rằng dữ liệu được ghi vào phản hồi
        outputStream.flush();
        outputStream.close();

    }

    @Override
    public Result findResultByUser(Long idUser, Long idExam) {
        return resultRepository.findResultByUser(idUser, idExam);
    }

    @Override
    public Result save(ResultDto resultDto) {
        Result result = new Result();
        result.setListenPoint(resultDto.getListenPoint());
        result.setReadPoint(resultDto.getReadPoint());
        result.setSpeakPoint(resultDto.getSpeakPoint());
        result.setUserss(resultDto.getUsers());
        result.setWritePoint(resultDto.getWritePoint());
        result.setExam(resultDto.getExam());

        return resultRepository.save(result);
    }

    @Override
    public Result update(Result result) {
        Result resultUpdate = resultRepository.getReferenceById(result.getId());
        resultUpdate.setListenPoint(result.getListenPoint());
        resultUpdate.setWritePoint(result.getWritePoint());
        resultUpdate.setSubmisTime(result.getSubmisTime());
        resultUpdate.setReadPoint(result.getReadPoint());
        resultUpdate.setSpeakPoint(result.getSpeakPoint());
        resultUpdate.setUserss(result.getUserss());
        resultUpdate.setExam(result.getExam());

        return resultRepository.save(resultUpdate);
    }

    @Override
    public List<Result> findResultByExam(Long idExam) {
        return resultRepository.findResultByExam(idExam);
    }

    @Override
    public Result findResult(Long idResult) {
        return resultRepository.findResult(idResult);
    }

}
