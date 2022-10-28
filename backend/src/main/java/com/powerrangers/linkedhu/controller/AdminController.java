package com.powerrangers.linkedhu.controller;

import com.powerrangers.linkedhu.dto.UserDTO;
import com.powerrangers.linkedhu.entity.common.User;
import com.powerrangers.linkedhu.service.AdminService;
import com.powerrangers.linkedhu.service.UserExcelExporterService;
import com.powerrangers.linkedhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    //BLOCK A SPESIFIC USER FROM THE SYSTEM FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/banspecific/{userId}")
    public ResponseEntity<String> applyBlockUserFromSystem(@PathVariable Long userId){
        return adminService.blockUserFromSystem(userId);
    }

    //UNBLOCK A SPESIFIC USER FROM THE SYSTEM FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(value = "/unbanspecific/{userId}")
    public ResponseEntity<String> applyUnBlockUserFromSystem(@PathVariable Long userId){
        return adminService.unBlockUserFromSystem(userId);
    }

    //EXPORT A SPESIFIC USER DATA TO EXCEL
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userService.listAll();

        UserExcelExporterService excelExporter = new UserExcelExporterService(listUsers, userService);
        excelExporter.export(response);
    }

    //ENABLE A SPESIFIC USER'S ACCOUNT METHOD FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/enablespecific/{userId}")
    public ResponseEntity<String> applyEnableUser(@PathVariable Long userId){
        return adminService.enableSpesificUser(userId);
    }

    //GET ALL USERS METHOD FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/return/allusers")
    public List<UserDTO> getAllUsers() {
        return adminService.getAllUsers();
    }

    //GET ALL STUDENT REPRESENTATIVE USERS EACH DEGREE METHOD FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/return/student-representativess")
    public List<UserDTO> getAllStudentRepresentatives() {
        return adminService.getAllStudentRepresentatives();
    }

    //SET SPESIFIC USER, STUDENT REPRESENTATION  METHOD  FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/set/student-representative/{userId}")
    public ResponseEntity<String> applyRepresentativeRole(@PathVariable Long userId){
        return adminService.setStudentRepresentative(userId);
    }

    //DELETE STUDENT REPRESENTATION FROM SPESIFIC USER METHOD FOR ADMIN
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/delete/student-representative/{userId}")
    public ResponseEntity<String> deleteRepresentativeRole(@PathVariable Long userId){
        return adminService.deleteStudentRepresentative(userId);
    }
}
