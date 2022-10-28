import Button from "@mui/material/Button";
import {AdminService} from "../../service/AdminService";
import React from "react";

export default function AdminExcelButton() {
    const handleClick = () => {
        AdminService.adminExportExcel().then(
            response => {
                const type = response.headers['content-type']
                const blob = new Blob([response.data], { type: type, encoding: 'UTF-8' })
                const link = document.createElement('a')
                link.href = window.URL.createObjectURL(blob)
                link.download = 'file.xlsx'
                link.click()
            }
        );
    }

    return (
      <Button variant="contained" onClick={handleClick} download>Get Spreadsheet of All Users</Button>
  )
}