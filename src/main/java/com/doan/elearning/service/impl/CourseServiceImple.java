package com.doan.elearning.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.repository.CourseRepository;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.utils.ImageUpload;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImple implements CourseService {
    private final CourseRepository courseRepository;
    private final ImageUpload imageUpload;

    @Override
    public List<Course> findAll() {
        // TODO Auto-generated method stub
        return courseRepository.findAll();
    }

    @Override
    public Course save(MultipartFile imageProduct, CourseDto coursedto) {
        Course cou = new Course();
        try {
            if (imageProduct == null) {
                cou.setImage(null);
            } else {
                imageUpload.uploadFile(imageProduct);
                cou.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            cou.setName(coursedto.getName());

            return courseRepository.save(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Optional<Course> findById(Long id) {
        // TODO Auto-generated method stub
        return courseRepository.findById(id);
    }

    @Override
    public Course update(MultipartFile imgCourse, CourseDto coursedto) {

        try {
            Course courseUpdate = courseRepository.getReferenceById(coursedto.getId());
            if (imgCourse.getBytes().length > 0) {
                if (imageUpload.checkExist(imgCourse)) {
                    courseUpdate.setImage(courseUpdate.getImage());
                } else {
                    imageUpload.uploadFile(imgCourse);
                    courseUpdate.setImage(Base64.getEncoder().encodeToString(imgCourse.getBytes()));
                }
            }

            courseUpdate.setId(courseUpdate.getId());
            courseUpdate.setName(coursedto.getName());

            return courseRepository.save(courseUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.getReferenceById(id);
        courseRepository.delete(course);
    }

    @Override
    public List<Course> findCourses(String keyword) {
        return courseRepository.findByName(keyword);
    }

    @Override
    public void exportToExcel(List<Course> objectList, HttpServletResponse response) throws IOException {
        // Tạo một workbook mới
        Workbook workbook = new XSSFWorkbook();

        // Tạo một sheet trong workbook
        Sheet sheet = workbook.createSheet("Object Data");

        // Tạo hàng đầu tiên (header) trong sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Field 1");
        headerRow.createCell(1).setCellValue("Field 2");
        // Thêm các tiêu đề cột khác (Field 3, Field 4, ...)
//kê
        // Ghi dữ liệu của các đối tượng vào sheet
        int rowNum = 1;
        for (Course object : objectList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(object.getId());
            row.createCell(1).setCellValue(object.getName());
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

}
